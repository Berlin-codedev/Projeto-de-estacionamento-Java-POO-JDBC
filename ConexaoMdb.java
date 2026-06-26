package ConexaoMdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMdb {
    private static final String URL = "jdbc:mariadb://localhost:3306/ESTACIONAMENTO";
    private static final String USUARIO = "root";
    private static final String SENHA = "12345678";

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.err.println("Erro critico: Drive Mdb não encontrado" + e.getMessage());
            throw new RuntimeException("Falha ao carregar o Banco de dados", e);
        }
    }
public static Connection conectar() {
    try {
        Connection conectar = DriverManager.getConnection(URL, USUARIO, SENHA);
        return conectar;
    } catch (SQLException e) {
        System.out.println("Erro ao conectar ao banco: " + e.getMessage());
        return null;
    }
}
public static void desconectar(Connection conectar) {
    try {
        if(conectar != null && !conectar.isClosed()){
            conectar.close();
            System.out.println("Conexão encerrada");
        }
    } catch (SQLException e) {
        System.err.println("Erro ao desconectar: " + e.getMessage());
    }
}
}