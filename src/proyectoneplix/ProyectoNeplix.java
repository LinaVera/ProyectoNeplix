package proyectoneplix;

import VO.*;
import control.Controlador;
import vista.*;

public class ProyectoNeplix {

    public static void main(String[] args) {
        Entrar e = new Entrar();
        A_menu amenu = new A_menu();
        IniciarSesion ini = new IniciarSesion();

        R_Series rserie = new R_Series();
        Serie serie = new Serie();
        U_VerSeries vserie = new U_VerSeries();

        R_Actor ractor = new R_Actor();
        Actor actor = new Actor();

        R_Capitulo rcap = new R_Capitulo();
        Capitulo cap = new Capitulo();
        U_VerCapitulo vcap = new U_VerCapitulo();

        R_Personajes rpePersonaje = new R_Personajes();
        Personaje personaje = new Personaje();

        R_Subtitulos rsub = new R_Subtitulos();
        Subtitulo sub = new Subtitulo();

        R_Temporadas rtemp = new R_Temporadas();
        Temporada temp = new Temporada();
        U_VerTemporada vtemp = new U_VerTemporada();

        Critica cri = new Critica();
        U_Critica rcri = new U_Critica();

        Usuario usu = new Usuario();
        R_Usuario rusu = new R_Usuario();

        U_Favoritos fav = new U_Favoritos();
        U_PuntuarSerie puntuar = new U_PuntuarSerie();

        Controlador c = new Controlador(amenu, e, ini,
                ractor, actor,
                rcap, cap, vcap,
                rpePersonaje, personaje,
                rserie, serie, vserie,
                rsub, sub,
                rtemp, temp, vtemp,
                usu, rusu,
                cri, rcri,
                fav,
                puntuar);
    }

}
