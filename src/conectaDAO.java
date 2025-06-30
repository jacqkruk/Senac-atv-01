
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
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=8kb,sd");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de conexão com banco de dados");
            System.out.println("Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
