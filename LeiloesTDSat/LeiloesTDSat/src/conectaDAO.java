
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */ 
    public class conectaDAO {
    
    public Connection connectDB(){
        Connection conn = null;
         
    try {
       
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        
        
        String url = "jdbc:mysql://127.0.0.1:3306/leiloes?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "Digitals0741#"; 
        conn = DriverManager.getConnection(url, user, password);
        
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "Erro: Driver JDBC não encontrado!");
    } catch (SQLException erro){
        JOptionPane.showMessageDialog(null, "Erro de Conexão: " + erro.getMessage());
    }
    return conn;
}
    }