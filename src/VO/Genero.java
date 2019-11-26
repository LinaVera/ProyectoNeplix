package VO;

public class Genero {

    private Integer Cod_genero;
    private String nombre;

    public Genero() {
    }

    public Genero(Integer Cod_genero, String nombre) {
        this.Cod_genero = Cod_genero;
        this.nombre = nombre;
    }

    public Integer getCod_genero() {
        return Cod_genero;
    }

    public void setCod_genero(Integer Cod_genero) {
        this.Cod_genero = Cod_genero;
    }

    public String getNombre() {
        switch (Cod_genero) {

            case 1:
                nombre = "Accion";
                break;
            case 2:
                nombre = "Aventura";
                break;
        }
        return nombre;
    }

}
