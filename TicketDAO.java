package DAO;

import Ticket.Ticket;
import Veiculo.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private Connection conexao;
    public TicketDAO(Connection conexao){
        this.conexao = conexao;
    }
    public void entrada(int id_veiculo) throws SQLException{
        String sql = "INSERT INTO TICKET(ID_VEICULO, DATA_ENTRADA) VALUE (?, NOW())";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, id_veiculo);
            stmt.executeUpdate();
            System.out.println("Entrada registrada com sucesso.");
        }
    }
    public boolean saida(int id_ticket) throws SQLException{
        String sql = "UPDATE TICKET SET DATA_SAIDA = NOW() WHERE ID_TICKET = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, id_ticket);
            int linhas = stmt.executeUpdate();
            return linhas >0;
        } catch (SQLException e) {
            return false;
        }
    }
    public Ticket buscarTicketAbertos(int id_veiculo) throws SQLException{
        String sql = "SELECT * FROM TICKET WHERE ID_VEICULO =? AND DATA_SAIDA IS NULL";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)){
             stmt.setInt(1, id_veiculo);
             try (ResultSet rs = stmt.executeQuery()) {
                 if (rs.next()) {
                     Ticket ticket = new Ticket();
                     ticket.setIdTicket(rs.getInt("ID_TICKET"));
                     ticket.setIdVeiculo(rs.getInt("ID_VEICULO"));
                     ticket.setDataEntrada(rs.getTimestamp("DATA_ENTRADA"));
                     return ticket;
                 }
             }
             }
    return null;
    }

public List<Ticket> buscarTodosAbertos() throws SQLException {
    String sql = "SELECT * FROM TICKET WHERE DATA_SAIDA IS NULL";
    List<Ticket> list = new ArrayList<>();
    try (PreparedStatement stmt = conexao.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Ticket t = new Ticket();
            t.setIdTicket(rs.getInt("ID_TICKET"));
            t.setIdTicket(rs.getInt("ID_VEICULO"));
            t.setDataEntrada(rs.getTimestamp("DATA_ENTRADA"));
            list.add(t);
        }
    }

    return list;
}

}
