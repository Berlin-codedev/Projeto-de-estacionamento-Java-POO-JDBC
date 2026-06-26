package Veiculo;

public class Veiculo {
    private int id_veiculo;
    private String placa;
    private String categoria;
    private boolean pcd;
    private String modelo;
    public Veiculo(String placa, String categoria, boolean deficiente, String modelo){
        this.placa = placa;
        this.categoria = categoria;
        this.pcd = pcd;
        this.modelo = modelo;
    }
    public Veiculo(){

    }
    public int getId_veiculo(){
        return id_veiculo;
    }
    public void setId_veiculo(int id_veiculo){
        this.id_veiculo = id_veiculo;
    }
    public String getPlaca(){
        return placa;
    }
    public void setPlaca(String placa){
        this.placa = placa;
    }
    public String getCategoria(){
        return categoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public String getModelo(){
        return modelo;
    }
    public boolean isPcd(){
        return pcd;
    }
    public void setPcd(boolean pcd){
        this.pcd = pcd;
    }
}
