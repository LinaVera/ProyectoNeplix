package VO;

public class Personaje {

    private Integer codigo_personaje;
    private String nombre_personaje;
    private Integer id_capitulo;
    private Integer id_Actor;

    public Personaje() {
    }

    public Personaje(Integer codigo_personaje, String nombre_personaje, Integer id_capitulo, Integer id_Actor) {
        this.codigo_personaje = codigo_personaje;
        this.nombre_personaje = nombre_personaje;
        this.id_capitulo = id_capitulo;
        this.id_Actor = id_Actor;
    }

    public Integer getCodigo_personaje() {
        return codigo_personaje;
    }

    public void setCodigo_personaje(Integer codigo_personaje) {
        this.codigo_personaje = codigo_personaje;
    }

    public String getNombre_personaje() {
        return nombre_personaje;
    }

    public void setNombre_personaje(String nombre_personaje) {
        this.nombre_personaje = nombre_personaje;
    }

    public Integer getId_capitulo() {
        return id_capitulo;
    }

    public void setId_capitulo(Integer id_capitulo) {
        this.id_capitulo = id_capitulo;
    }

    public Integer getId_Actor() {
        return id_Actor;
    }

    public void setId_Actor(Integer id_Actor) {
        this.id_Actor = id_Actor;
    }

}
