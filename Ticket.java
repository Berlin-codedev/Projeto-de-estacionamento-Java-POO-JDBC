package Ticket;
import Veiculo.Veiculo;

import java.sql.Timestamp;
public class Ticket {
    private int id_ticket;
    private int id_veiculo;
    private Veiculo veiculo;
    private Timestamp dataEntrada;
    private Timestamp dataSaida;
    public Ticket(){
    }
    public int getIdTicket(){
        return id_ticket;
    }
    public void setIdTicket(int id_ticket){
        this.id_ticket = id_ticket;
    }
    public int getIdVeiculo(){
        return id_veiculo;
    }
    public void setIdVeiculo(int id_veiculo){
        this.id_veiculo = id_veiculo;
    }
    public Timestamp getDataEntrada(){ return dataEntrada;}
    public void setDataEntrada(Timestamp dataEntrada){this.dataEntrada = dataEntrada;}
    public Timestamp getDataSaida(){ return dataSaida;}
    public void setDataSaida(Timestamp dataSaida){this.dataSaida = dataSaida;}
    public void setVeiculo(Veiculo veiculo ){this.veiculo = veiculo;}
    public Veiculo getVeiculo(){return veiculo;}
}
