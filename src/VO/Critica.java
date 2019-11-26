package VO;

public class Critica extends Usuario {    

    private String fechaC;
    private String critica;
    private Integer cod_critica;
    private Integer tipo_critica;

    public Critica() {
    }

    public Critica(String fechaC, String critica, Integer cod_critica, Integer tipo_critica) {
        this.fechaC = fechaC;
        this.critica = critica;
        this.cod_critica = cod_critica;
        this.tipo_critica = tipo_critica;
    }

    public String getFechaC() {
        return fechaC;
    }

    public void setFechaC(String fechaC) {
        this.fechaC = fechaC;
    }

    public String getCritica() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica = critica;
    }

    public Integer getCod_critica() {
        return cod_critica;
    }

    public void setCod_critica(Integer cod_critica) {
        this.cod_critica = cod_critica;
    }

    public Integer getTipo_critica() {
        return tipo_critica;
    }

    public void setTipo_critica(Integer tipo_critica) {
        this.tipo_critica = tipo_critica;
    }

    
}
