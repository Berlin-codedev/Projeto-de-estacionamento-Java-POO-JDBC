import ConexaoMdb.ConexaoMdb;
import ServiceLayer.Servico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        try (Connection conexao = ConexaoMdb.conectar()) {
            Servico servico = new Servico(conexao, scanner);

            boolean executando = true;
            while (executando) {
                System.out.println(" -=-=-=-=-= MENU ESTACIONAMENTO -=-=-=-=-");
                System.out.println("1- Registrar Entrada");
                System.out.println("2- Registrar Saida");
                System.out.println("3- Listar Veiculos Abertos");
                System.out.println("4- Gerenciar Cadastro de Veiculo");
                System.out.println("5- Sair ");
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        servico.processarEntrada();
                        break;
                    case 2:
                        servico.processarSaida();
                        break;
                    case 3:
                        servico.listarVeiculosAbertos();
                        break;
                    case 4:
                        servico.gerenciarCadastro();
                        break;
                    case 5:
                        System.out.println("Encecrrando o sistema. até logo!");
                        executando = false;
                        break;
                    default:
                        System.out.println("Opção invalida!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conecatar ao banco: " + e.getMessage());
        }finally {
            scanner.close();
        }
        }
}
