package VO;

public class Serie {

    private Integer cod_serie;
    private String titulo;
    private Integer ano_inicio;
    private String sinopsis;
    private int Cod_genero;
    private String imagen;

    public Serie() {

    }

    public Serie(Integer cod_serie, String titulo, Integer ano_inicio, String sinopsis, int Cod_genero, String imagen) {
        this.cod_serie = cod_serie;
        this.titulo = titulo;
        this.ano_inicio = ano_inicio;
        this.sinopsis = sinopsis;

        this.Cod_genero = Cod_genero;
        this.imagen = imagen;
    }

    public Integer getCod_serie() {
        return cod_serie;
    }

    public void setCod_serie(Integer cod_serie) {
        this.cod_serie = cod_serie;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno_inicio() {
        return ano_inicio;
    }

    public void setAno_inicio(Integer ano_inicio) {
        this.ano_inicio = ano_inicio;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getCod_genero() {
        return Cod_genero;
    }

    public void setCod_genero(int Cod_genero) {
        this.Cod_genero = Cod_genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
