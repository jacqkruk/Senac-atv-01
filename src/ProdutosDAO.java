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
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        int status;
       
        try {
            conn = new conectaDAO().connectDB();
            prep  = conn.prepareStatement("INSERT INTO produtos(nome, valor) VALUES(?,?)");
        
            prep.setString(1, produto.getNome());
            prep.setString(2, produto.getValor().toString());
            
            status = prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex.getMessage());
        }
       
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
            conn = new conectaDAO().connectDB();
            
            String sql = "SELECT id, nome, valor, status FROM produtos";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            // Loop pelos resultados
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(String.valueOf(resultset.getDouble("valor")));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
            // Fechando recursos
            resultset.close();
            prep.close();
            conn.close();
            
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco de dados");
        }
        
        return listagem;
    }
    
}

