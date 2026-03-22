/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
        
    conn = new conectaDAO().connectDB();
    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
    
    try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            return true;
           
    } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto:  " + e.getMessage()); 
            return false;
            
            } finally {
    try { if (conn != null) conn.close(); } catch (Exception e) {}
    }

 }
    
    public void venderProduto(int id) {
        
    conn = new conectaDAO().connectDB();
    String sql = "UPDATE produtos SET status = 'vendido' WHERE id = ?";
    
    try {
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
    } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());   
            
            } finally {
    try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
           
        }
     
    
    //Commit #5: Implementação da listagem na tabela
    public ArrayList<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM Produtos";
        
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

    // Commit #6: Correção de bug na conexão com banco de dados        
            listagem.clear();

            while(resultset.next()) {
            ProdutosDTO p = new ProdutosDTO();    
            p.setId(resultset.getInt("id"));
            p.setNome(resultset.getString("nome"));
            p.setValor(resultset.getInt("valor"));
            p.setStatus(resultset.getString("status"));
            listagem.add(p);
        }
        } catch (Exception e) { // Commit #4: Adição de mensagens de sucesso e erro
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        
        return listagem;
    

    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        listagem.clear();

    try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

    // Commit #6: Correção de bug na conexão com banco de dados        
            listagem.clear();

            while(resultset.next()) {
            ProdutosDTO p = new ProdutosDTO();    
            p.setId(resultset.getInt("id"));
            p.setNome(resultset.getString("nome"));
            p.setValor(resultset.getInt("valor"));
            p.setStatus(resultset.getString("status"));
            listagem.add(p);
        }
        } catch (Exception e) { // Commit #4: Adição de mensagens de sucesso e erro
            System.out.println("Erro ao listar vendidos: " + e.getMessage());
        }
        
        return listagem;
    

    }
    
    
    
    
        
}

