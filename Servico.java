package ServiceLayer;

import DAO.TicketDAO;
import DAO.VeiculoDAO;
import Ticket.Ticket;
import Veiculo.Veiculo;
import jdk.dynalink.linker.LinkerServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Servico {
    private VeiculoDAO vDAO;
    private TicketDAO tDAO;
    private Scanner scanner;
    public Servico(Connection conn, Scanner scanner){
        this.vDAO = new VeiculoDAO(conn);
        this.tDAO = new TicketDAO(conn);
        this.scanner = scanner;
    }
    public void processarEntrada() throws SQLException {
        System.out.println("-=-=-=-=-=REGISTRO DE ENTRADA -=-=-=-=-=-=-");
        System.out.println("Qual a placa do seu veiculo? ");
        String placa =scanner.nextLine();
        Veiculo v = vDAO.buscarPlaca(placa);
        if (v==null) {
            System.out.println("Qual a categoria do veiculo? ");
            String categoriaVeiculo = scanner.nextLine();
            System.out.println("É pcd? (S/N) ");
            boolean pcd = scanner.next().equalsIgnoreCase("S");
            scanner.nextLine();
            System.out.println("Qual o modelo?");
            String modelo = scanner.nextLine();
            Veiculo novoVeiculo = new Veiculo(placa, categoriaVeiculo, pcd, modelo);
            vDAO.inserir(novoVeiculo);
            v = vDAO.buscarPlaca(placa);

        }else {
            System.out.println("Veiculo ja cadastrado: " + v.getModelo());
        }
        tDAO.entrada(v.getId_veiculo());
        System.out.println("Entrada registrada com sucesso");
    }
    public void processarSaida() throws SQLException{
        System.out.println("-=-=-=-=-= REGISTRAR DE SAIDA -=-=-=-=-=");
        System.out.println("Qual a placa para finalizar o ticket? ");
        String placa = scanner.nextLine();
        Veiculo v = vDAO.buscarPlaca(placa);
        if (v != null){
            Ticket ticket = tDAO.buscarTicketAbertos(v.getId_veiculo());
            if (ticket != null) {

                LocalDateTime entrada = ticket.getDataEntrada().toLocalDateTime();
                double valor = Calcular.calculaPreco(entrada);
                System.out.printf("Valor a pagar: R$ %.2f%n", valor);
                tDAO.saida(ticket.getIdTicket());
                System.out.println("Saida processada com sucesso!");
            }else{
                    System.out.println("Nenhum ticket aberto encontrado para esta placa.");
                }
            }else {
                System.out.println("Veiculo não cadastrado no sistema!");
            }
        }
        public void listarVeiculosAbertos() throws SQLException{
            List<Ticket> tickets = tDAO.buscarTodosAbertos();
            if (tickets.isEmpty()){
                System.out.println("Nenhum veiculo estacionamento no momento.");
            } else {
                System.out.println("\n---- VEICULOS NO ESTACIONEMENTO ------");
                for (Ticket t : tickets){
                    System.out.println(String.format("ID Ticket: %d | Placa %s | Modelo: %s",
                            t.getIdTicket(), t.getVeiculo().getPlaca(),
                            t.getVeiculo().getModelo()));
                }
            }
        }
        public void gerenciarCadastro() throws SQLException{
            System.out.println("Digite a placa do veiculo que deseja atualizar: ");
            String placa = scanner.nextLine();
            Veiculo v = vDAO.buscarPlaca(placa);
            if (v != null){
                System.out.println("Veiculo encontrado: " + v.getModelo());
                System.out.println("Novo modelo (ou aperte enter para manter): ");
                String novoModelo = scanner.nextLine();
                if(!novoModelo.isEmpty()) v.setModelo(novoModelo);
                System.out.println("É PCD? (S/N): ");
                String pcdInput = scanner.nextLine();
                if (pcdInput.equalsIgnoreCase("S") || pcdInput.equalsIgnoreCase("N")){
                    v.setPcd(pcdInput.equalsIgnoreCase("S"));
                }
                vDAO.atualizar(v);
            }else {
                System.out.println("Veiculo não encontrado");
            }
        }
}

