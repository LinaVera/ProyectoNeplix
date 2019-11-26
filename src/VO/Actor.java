package VO;

public class Actor {

    private Integer id_actor;
    private String nombre_real;
    private String nombre_pers;

    public Actor() {
        super();
    }

    public Actor(Integer id_actor, String nombre_real, String nombre_pers) {
        this.id_actor = id_actor;
        this.nombre_real = nombre_real;
        this.nombre_pers = nombre_pers;
    }

    public Integer getId_actor() {
        return id_actor;
    }

    public void setId_actor(Integer id_actor) {
        this.id_actor = id_actor;
    }

    public String getNombre_real() {
        return nombre_real;
    }

    public void setNombre_real(String nombre_real) {
        this.nombre_real = nombre_real;
    }

    public String getNombre_pers() {
        return nombre_pers;
    }

    public void setNombre_pers(String nombre_pers) {
        this.nombre_pers = nombre_pers;
    }

}
