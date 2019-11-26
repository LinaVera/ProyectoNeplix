package VO;

public class Temporada {

    private String fechaP;
    private String fechaE;

    private Integer cod_temporada;

    private Integer cod_serie;

    public Temporada() {

    }

    public Temporada(String fechaP, String fechaE, Integer cod_temporada, Integer cod_serie) {
        this.fechaP = fechaP;
        this.fechaE = fechaE;
        this.cod_temporada = cod_temporada;
        this.cod_serie = cod_serie;

    }

    public String getFechaP() {
        return fechaP;
    }

    public void setFechaP(String fechaP) {
        this.fechaP = fechaP;
    }

    public String getFechaE() {
        return fechaE;
    }

    public void setFechaE(String fechaE) {
        this.fechaE = fechaE;
    }

    public Integer getCod_temporada() {
        return cod_temporada;
    }

    public void setCod_temporada(Integer cod_temporada) {
        this.cod_temporada = cod_temporada;
    }

    public Integer getCod_serie() {
        return cod_serie;
    }

    public void setCod_serie(Integer cod_serie) {
        this.cod_serie = cod_serie;
    }

}
