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
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES(?,?,?)";
        
        try {
            conn = new conectaDAO().connectDB();
            prep  = conn.prepareStatement(sql);
        
            prep.setString(1, produto.getNome());
            prep.setString(2, produto.getValor().toString());
            prep.setString(3, "À Venda");
            
            prep.executeUpdate();
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
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
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

    public void venderProduto(int parseInt) {
       String sql = "UPDATE produtos SET status = ? WHERE id = ?";
       
       try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            
            String status = "Vendido";
            
            prep.setString(1, status);
            prep.setInt(2, parseInt);
            prep.executeUpdate();
            
            // Fechando recursos
            prep.close();
            conn.close();
                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível completar a ação.");
        }
    }
    
   public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = ?";
       
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            
            String status = "Vendido";
            prep.setString(1, status);
            resultset = prep.executeQuery();
            
            try {
                // Loop pelos resultados
                while (resultset.next()) {
                    ProdutosDTO produto = new ProdutosDTO();
                    produto.setId(resultset.getInt("id"));
                    produto.setNome(resultset.getString("nome"));
                    produto.setValor(resultset.getInt("valor"));
                    produto.setStatus(resultset.getString("status"));

                    listagem.add(produto);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Algo errado no loop: " + e.getMessage());
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

