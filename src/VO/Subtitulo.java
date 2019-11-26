package VO;

public class Subtitulo {

    private String idioma;
    private String autor;
    private String cod_subtitulo;
    private String ruta;

    public Subtitulo() {
    }

    public Subtitulo(String idioma, String autor, String cod_subtitulo, String ruta) {
        this.idioma = idioma;
        this.autor = autor;
        this.cod_subtitulo = cod_subtitulo;
        this.ruta = ruta;

    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCod_subtitulo() {
        return cod_subtitulo;
    }

    public void setCod_subtitulo(String cod_subtitulo) {
        this.cod_subtitulo = cod_subtitulo;
    }
}
