package DAO;

import Veiculo.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    private Connection conexao;

    public VeiculoDAO(Connection conexao){
        this.conexao = conexao;
    }
    public boolean inserir (Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO VEICULO (PLACA, CATEGORIA, PCD, MODELO) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getCategoria());
            stmt.setBoolean(3, veiculo.isPcd());
            stmt.setString(4, veiculo.getModelo());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas != 0){
                System.out.println("Usuario inserido com sucesso");
                return true;
            } else {
                System.out.println("ERRO, falha ao inserir!");
            return false;
            }
        }catch (SQLException e){
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
            return false;
        }

    }
    public List<Veiculo> buscar() {
        String sql = "SELECT * FROM VEICULO ORDER BY PLACA";
        List<Veiculo> veiculos = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId_veiculo(rs.getInt("ID_VEICULO"));
                veiculo.setCategoria(rs.getString("CATEGORIA"));
                veiculo.setPlaca(rs.getString("PLACA"));
                veiculo.setModelo(rs.getString("MODELO"));
                veiculo.setPcd(rs.getBoolean("PCD"));
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar veiculo" + e.getMessage());
        }
        return veiculos;
    }
public boolean atualizar(Veiculo veiculo) {
    String sql = "UPDATE VEICULO SET CATEGORIA = ?, PCD = ?, MODELO = ? WHERE ID_VEICULO = ?";
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, veiculo.getCategoria());
        stmt.setBoolean(2, veiculo.isPcd());
        stmt.setString(3, veiculo.getModelo());
        stmt.setInt(4, veiculo.getId_veiculo());
        int linhasAfetadas = stmt.executeUpdate();
        if (linhasAfetadas > 0) {
            System.out.println("Usuario atualizado com sucesso!");
            return true;
        } else {
            System.out.println("Nenhum usuario encontrado");
            return false;
        }
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar usuario: " + e.getMessage());
        return false;
    }
}
public boolean deletear (int id_veiculo){
            String sql = "DELETE FROM VEICULO WHERE ID_VEICULO = ?";
            try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setInt(1, id_veiculo);
                int linhasAfetadas = stmt.executeUpdate();
                if(linhasAfetadas != 0){
                    System.out.println("veiculo deletado com sucesso");
                    return true;
                } else {
                    System.out.println("Veiculo não encontrado");
                    return false;
                }
                } catch (SQLException e) {
                System.out.println("Erro ao deletar usuario: " + e.getMessage());
                return false;
            }
}
    public Veiculo buscarPlaca(String placa){
        String sql = "SELECT * FROM VEICULO WHERE PLACA = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Veiculo v = new Veiculo();
                    v.setId_veiculo(rs.getInt("ID_VEICULO"));
                    v.setPlaca(rs.getString("PLACA"));
                    return v;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar placa: " + e.getMessage());
        }
        return null;
    }
}
