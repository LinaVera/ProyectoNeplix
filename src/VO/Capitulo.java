package VO;


public class Capitulo {

    private String titulo;
    private int duracion;
    private String sinopsis;    
    private Integer id_capitulo; 
    private Integer id_tempoTemporada;
    private Integer id_subtitulos;

    public Capitulo() {
    }

    public Capitulo(String titulo, int duracion, String sinopsis, Integer cod_capitulo, Integer id_tempoTemporada, Integer id_subtitulos) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.sinopsis = sinopsis;       
        this.id_capitulo = id_capitulo;
        this.id_tempoTemporada = id_tempoTemporada;
        this.id_subtitulos = id_subtitulos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Integer getId_capitulo() {
        return id_capitulo;
    }

    public void setId_capitulo(Integer id_capitulo) {
        this.id_capitulo = id_capitulo;
    }

    public Integer getId_tempoTemporada() {
        return id_tempoTemporada;
    }

    public void setId_tempoTemporada(Integer id_tempoTemporada) {
        this.id_tempoTemporada = id_tempoTemporada;
    }

    public Integer getId_subtitulos() {
        return id_subtitulos;
    }

    public void setId_subtitulos(Integer id_subtitulos) {
        this.id_subtitulos = id_subtitulos;
    }

  
  

}
