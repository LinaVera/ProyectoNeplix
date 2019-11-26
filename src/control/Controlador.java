package control;

import DAO.*;
import vista.*;
import VO.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener {

    Integer idUsu = 0;
    Integer idSerie = 0;
    Integer idCap = 0;
    Ver v = new Ver();
    UsuarioDAO usuDAO = new UsuarioDAO();
    SubtituloDAO subDAO = new SubtituloDAO();
    ActorDAO acDAO = new ActorDAO();
    PersonajeDAO peDAO = new PersonajeDAO();
    SerieDAO seDAO = new SerieDAO();
    CapituloDAO capDAO = new CapituloDAO();
    TemporadaDAO temDAO = new TemporadaDAO();
    //Inicios

    private A_menu amenu = null;
    private Entrar e = null;
    private IniciarSesion isesion = null;
    //2.Actor
    private R_Actor ractor = null;
    private Actor actor = null;
    //3.Capitulo
    private R_Capitulo rcap = null;
    private U_VerCapitulo vcap = null;
    private Capitulo cap = null;
    //4.personaje
    private R_Personajes rpe = null;
    private Personaje pe = null;
    //1.Serie
    private R_Series rserie = null;
    private U_VerSeries vserie = null;
    private Serie serie = null;

    //5.subtitulo
    private R_Subtitulos rsub = null;
    private Subtitulo sub = null;
    //6.temporada
    private R_Temporadas rtemp = null;
    private Temporada temp = null;
    private U_VerTemporada vtemp = null;
    //8.Usuario
    private Usuario usu = null;
    private R_Usuario rusu = null;
    //7.Critica
    private Critica cri = null;
    private U_Critica rcri = null;
    //Otros
    private U_Favoritos fav = null;
    private U_PuntuarSerie puntuar = null;

    public Controlador(A_menu amenu, Entrar e, IniciarSesion isesion,
            R_Actor ractor, Actor actor,
            R_Capitulo rcap, Capitulo cap, U_VerCapitulo vcap,
            R_Personajes rpePersonaje, Personaje personaje,
            R_Series rserie, Serie serie, U_VerSeries vserie,
            R_Subtitulos rsub, Subtitulo sub,
            R_Temporadas rtemp, Temporada temp, U_VerTemporada vtemp,
            Usuario usu, R_Usuario rusu,
            Critica cri, U_Critica rcri,
            U_Favoritos fav, U_PuntuarSerie puntuar) {
        super();
        //menu y entrar
        this.e = e;
        this.amenu = amenu;
        this.isesion = isesion;

        //registros
        this.ractor = ractor;
        this.actor = actor;//
        this.rcap = rcap;
        this.cap = cap;
        this.vcap = vcap;//
        this.rpe = rpePersonaje;
        this.pe = personaje;//
        this.rserie = rserie;
        this.serie = serie;
        this.vserie = vserie;//
        this.rsub = rsub;
        this.sub = sub;//  
        this.rtemp = rtemp;
        this.temp = temp;
        this.vtemp = vtemp;//
        this.usu = usu;
        this.rusu = rusu;//
        this.cri = cri;
        this.rcri = rcri;
        this.fav = fav;
        this.puntuar = puntuar;
        actionListener(this);
        this.e.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == this.amenu.btnPer) {
                this.rpe.setVisible(true);
                this.rpe.llenar(this.rpe.cbActor, "actor");
                this.rpe.llenar(this.rpe.cbCap, "capitulo");
                this.amenu.dispose();
            } else if (ae.getSource() == this.amenu.btnActor) {
                this.ractor.setVisible(true);
                this.amenu.dispose();
            } else if (ae.getSource() == this.amenu.btnCap) {
                this.rcap.setVisible(true);
                this.rcap.llenar(this.rcap.cbTemporada, "temporada");
                this.rcap.llenar(this.rcap.cbSubti, "subtitulo");
                this.amenu.dispose();
            } else if (ae.getSource() == this.amenu.btnSalir) {
                this.isesion.setVisible(true);
                this.amenu.dispose();
            } else if (ae.getSource() == this.amenu.btnSeries) {
                this.rserie.setVisible(true);
                this.rserie.llenar(this.rserie.txtGenero, "genero");
                this.amenu.dispose();
            } else if (ae.getSource() == this.amenu.btnSub) {
                this.rsub.setVisible(true);
                this.amenu.dispose();
            } else if (ae.getSource() == this.amenu.btnTep) {

                this.rtemp.setVisible(true);
                this.rtemp.llenar(this.rtemp.id_serie);
                this.amenu.dispose();
            }

            //Entrar
            if (ae.getSource() == this.e.btnEntrar) {
                this.e.dispose();
                this.isesion.setVisible(true);
            } //Iniciar Sesion
            else if (ae.getSource() == this.isesion.btnEntrarSesion) {
                Integer usuario = Integer.parseInt(this.isesion.jTextField1.getText());
                String clave = this.isesion.jPasswordField1.getText();
                int n = this.usuDAO.Ingresar(usuario, clave);
                if (n == 1) {
                    this.isesion.dispose();
                    this.amenu.setVisible(true);

                } else if (n == 2) {
                    this.isesion.dispose();
                    this.vserie.setVisible(true);

                    //obtener el id del usuario
                    idUsu = usuDAO.idUsu;
                    this.vserie.lbId.setText(String.valueOf(idUsu));
                    v.visualizar(this.vserie.jTable1);
                    this.rserie.llenar(this.vserie.jComboBox1, "genero");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al ingresar");
                }
                this.isesion.limpiar();
            } else if (ae.getSource() == this.isesion.btnRegistrarse) {
                this.isesion.dispose();
                this.rusu.setVisible(true);
            } else if (ae.getSource() == this.isesion.btnVolver) {
                this.isesion.dispose();
                this.e.setVisible(true);
            } //Registrar Usuario
            else if (ae.getSource() == this.rusu.btnBuscar) {
                Integer id = Integer.parseInt(this.rusu.txtCedula.getText());
                if (this.rusu.txtCedula.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.rusu.txtCedula.setText("");
                    this.botones();
                    this.rusu.visible(true);
                } else {
                    if (this.usuDAO.Buscar(id) == true) {
                        this.usuDAO.Llenar(id, this.rusu.txtNombreUsuario,
                                this.rusu.txtApellidoUsuario, this.rusu.tpContraseña,
                                this.rusu.txtCorreo, this.rusu.txtAño, this.rusu.cbDia, this.rusu.cbMes);
                        this.rusu.btnActualizar.setEnabled(true);
                        this.rusu.btnEliminar.setEnabled(true);
                        this.rusu.btnRegistrar.setEnabled(false);
                        this.rusu.visible(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario " + id + ", no encontrado");
                        this.rusu.txtCedula.setText("");
                        this.botones();
                        this.rusu.visible(true);
                    }
                }
            } else if (ae.getSource() == this.rusu.btnRegistrar) {

                if (this.rusu.txtApellidoUsuario.getText().equalsIgnoreCase("") || this.rusu.txtNombreUsuario.getText().equalsIgnoreCase("")
                        || this.rusu.txtCedula.getText().equalsIgnoreCase("") || this.rusu.txtCorreo.getText().equalsIgnoreCase("")
                        || this.rusu.tpContraseña.getText().equalsIgnoreCase("") || this.rusu.txtAño.getText().equalsIgnoreCase("")
                        || this.rusu.cbDia.getSelectedIndex() == 0 || this.rusu.cbMes.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Llenar todos los campos");
                } else {
                    Integer id = Integer.parseInt(this.rusu.txtCedula.getText());
                    if (this.usuDAO.Buscar(id)) {
                        if (this.rusu.btnRegistrar.getText().equalsIgnoreCase("Guardar")) {
                            String correo = this.rusu.txtCorreo.getText();
                            String nombre = this.rusu.txtNombreUsuario.getText();
                            String apellido = this.rusu.txtApellidoUsuario.getText();
                            String contreseña = this.rusu.tpContraseña.getText();
                            String fecha = this.rusu.txtAño.getText() + "-" + this.rusu.cbMes.getSelectedIndex() + "-" + this.rusu.cbDia.getSelectedIndex();
                            usu.setNombre(nombre);
                            usu.setApellido(apellido);
                            usu.setEmail(correo);
                            usu.setContraseña(contreseña);
                            usu.setFechaNac(fecha);
                            this.usuDAO.actualizar(usu, id);
                            this.rusu.limpiar();
                            this.rusu.btnActualizar.setEnabled(false);
                            this.rusu.btnEliminar.setEnabled(false);
                            this.rusu.btnRegistrar.setText("Registrar");
                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario ya esta registrado");
                            this.rusu.limpiar();
                        }
                    } else {
                        if (this.rusu.btnRegistrar.getText().equalsIgnoreCase("Registrar")) {
                            String correo = this.rusu.txtCorreo.getText();
                            String nombre = this.rusu.txtNombreUsuario.getText();
                            String apellido = this.rusu.txtApellidoUsuario.getText();
                            String contreseña = this.rusu.tpContraseña.getText();
                            String fecha = this.rusu.txtAño.getText() + "-" + this.rusu.cbMes.getSelectedIndex() + "-" + this.rusu.cbDia.getSelectedIndex();
                            usu = new Usuario(nombre, apellido, correo, fecha, id, contreseña);
                            this.usuDAO.registrarUsuario(usu);
                            this.rusu.limpiar();
                            this.rusu.btnActualizar.setEnabled(false);
                            this.rusu.btnEliminar.setEnabled(false);
                            this.rusu.btnRegistrar.setText("Registrar");
                        }
                    }
                }
            } else if (ae.getSource() == this.rusu.btnActualizar) {
                this.rusu.btnRegistrar.setText("Guardar");
                this.rusu.btnRegistrar.setEnabled(true);
                this.rusu.btnActualizar.setEnabled(false);
                this.rusu.btnEliminar.setEnabled(false);
                this.rusu.txtCedula.setEnabled(false);
                this.rusu.visible(true);
            } else if (ae.getSource() == this.rusu.btnEliminar) {
                Integer id = Integer.parseInt(this.rusu.txtCedula.getText());
                if (this.rusu.txtCedula.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.rusu.txtCedula.setText("");
                    this.rusu.visible(true);

                    this.botones();
                } else {
                    if (this.usuDAO.Buscar(id)) {
                        usu = new Usuario();
                        this.usuDAO.eliminar(id);
                        this.rusu.txtCedula.setText("");
                        this.botones();
                        this.rusu.visible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario " + id + ", no encontrado");
                        this.rusu.txtCedula.setText("");
                        this.rusu.visible(true);
                        this.botones();
                    }
                }
            } else if (ae.getSource() == this.rusu.btnVolver) {
                this.rusu.dispose();
                this.isesion.setVisible(true);
            } //Registrar subtitulos
            else if (ae.getSource() == this.rsub.btnBuscar) {
                String id = this.rsub.txtCodigo.getText();
                if (this.rsub.txtCodigo.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.rsub.txtCodigo.setText("");
                    this.rsub.botones(false);
                    this.rsub.visible(true);
                } else {
                    if (this.subDAO.Buscar(id) == true) {
                        this.subDAO.Llenar(id, this.rsub.txtIdioma, this.rsub.txtAutor);
                        this.rsub.btnActualizar.setEnabled(true);
                        this.rsub.btnElimminar.setEnabled(true);
                        this.rsub.btnRegistrarSubtitulos.setEnabled(false);
                        this.rsub.visible(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "Subtítulo " + id + ", no encontrado");
                        this.rsub.txtCodigo.setText("");
                        this.rsub.botones(false);
                        this.rsub.visible(true);
                    }
                }
            } else if (ae.getSource() == this.rsub.btnVolver) {
                this.rsub.dispose();
                this.rsub.limpiar();
                this.amenu.setVisible(true);
            } else if (ae.getSource() == this.rsub.btnRegistrarSubtitulos) {
                if (this.rsub.txtAutor.getText().equalsIgnoreCase("") || this.rsub.txtIdioma.getText().equalsIgnoreCase("")
                        || this.rsub.txtCodigo.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar todos los campos");
                    this.rsub.limpiar();
                } else {
                    String id = this.rsub.txtCodigo.getText();
                    if (this.subDAO.Buscar(id)) {
                        if (this.rsub.btnRegistrarSubtitulos.getText().equalsIgnoreCase("Guardar")) {
                            String autor = this.rsub.txtAutor.getText();
                            String idioma = this.rsub.txtIdioma.getText();
                            this.sub.setAutor(autor);
                            this.sub.setCod_subtitulo(id);
                            this.sub.setIdioma(idioma);

                            this.subDAO.actualizar(sub, id);
                            this.rsub.limpiar();
                            this.rsub.btnActualizar.setEnabled(false);
                            this.rsub.btnElimminar.setEnabled(false);
                            this.rsub.btnRegistrarSubtitulos.setText("Registrar");
                        } else {
                            JOptionPane.showMessageDialog(null, "Subtítulo ya esta registrado");
                            this.rsub.limpiar();
                        }
                    } else {
                        if (this.rsub.btnRegistrarSubtitulos.getText().equalsIgnoreCase("Registrar")) {
                            String autor = this.rsub.txtAutor.getText();
                            String idioma = this.rsub.txtIdioma.getText();
                            String archivo = this.rsub.lbArchivo.getText();
                            sub = new Subtitulo(idioma, autor, id, archivo);
                            this.subDAO.registrarSubtitulo(sub);
                            this.rsub.limpiar();
                            this.rsub.btnActualizar.setEnabled(false);
                            this.rsub.btnElimminar.setEnabled(false);
                            this.rsub.btnRegistrarSubtitulos.setText("Registrar");
                        }
                    }
                    this.rsub.limpiar();
                }
                this.rsub.txtCodigo.setEnabled(true);

            } else if (ae.getSource() == this.rsub.btnSubir) {

            } else if (ae.getSource() == this.rsub.btnActualizar) {
                this.rsub.btnRegistrarSubtitulos.setText("Guardar");
                this.rsub.btnRegistrarSubtitulos.setEnabled(true);
                this.rsub.btnActualizar.setEnabled(false);
                this.rsub.btnElimminar.setEnabled(false);
                this.rsub.txtCodigo.setEnabled(false);
                this.rsub.visible(true);
            } else if (ae.getSource() == this.rsub.btnElimminar) {
                String id = this.rsub.txtCodigo.getText();
                if (this.rsub.txtCodigo.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.rsub.txtCodigo.setText("");
                    this.rsub.visible(true);
                    this.rsub.botones(false);
                    this.rsub.limpiar();
                } else {
                    if (this.subDAO.Buscar(id)) {
                        sub = new Subtitulo();
                        this.subDAO.eliminar(id);
                        this.rsub.txtCodigo.setText("");
                        this.rsub.botones(false);
                        this.rsub.visible(true);
                        this.rsub.limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Subtítulo " + id + ", no encontrado");
                        this.rsub.txtCodigo.setText("");
                        this.rsub.visible(true);
                        this.rsub.botones(false);
                        this.rsub.limpiar();
                    }
                }
            }

            ///RegistrarActor
            if (ae.getSource() == ractor.btnRegistrarActor) {

                if (ractor.txtCodigoActor.getText().equalsIgnoreCase("") || ractor.txtNombreRealActor.getText().equalsIgnoreCase("")
                        || ractor.txtNombreArtistico.getText().equalsIgnoreCase("")) {
                    JOptionPane.showConfirmDialog(null, "Llenar todos los campos");
                    ractor.txtCodigoActor.requestFocus();
                    this.ractor.limpiar();

                } else {
                    Integer id = Integer.parseInt(this.ractor.txtCodigoActor.getText());
                    if (this.acDAO.Buscar(id)) {
                        if (this.ractor.btnRegistrarActor.getText().equalsIgnoreCase("Guardar")) {
                            String noma = this.ractor.txtNombreArtistico.getText();
                            String nomr = this.ractor.txtNombreRealActor.getText();

                            this.actor.setId_actor(id);
                            this.actor.setNombre_pers(noma);
                            this.actor.setNombre_real(nomr);

                            this.acDAO.actualizar(actor, id);
                            this.ractor.limpiar();
                            this.ractor.btnModificar.setEnabled(false);
                            this.ractor.btnEliminar.setEnabled(false);
                            this.ractor.btnRegistrarActor.setText("Registrar");
                        } else {
                            JOptionPane.showMessageDialog(null, "Actor ya esta registrado");
                            this.ractor.limpiar();
                        }
                    } else {
                        if (this.ractor.btnRegistrarActor.getText().equalsIgnoreCase("Registrar")) {
                            String noma = this.ractor.txtNombreArtistico.getText();
                            String nomr = this.ractor.txtNombreRealActor.getText();
                            actor = new Actor(id, nomr, noma);
                            this.acDAO.registrarActor(actor);
                            this.ractor.limpiar();
                            this.ractor.btnModificar.setEnabled(false);
                            this.ractor.btnEliminar.setEnabled(false);
                            this.ractor.btnRegistrarActor.setText("Registrar");
                        }
                    }
                    this.ractor.limpiar();
                    this.ractor.txtCodigoActor.setText("");
                    this.ractor.txtCodigoActor.setEnabled(true);
                }

            } else if (ae.getSource() == ractor.btnBuscarActor) {

                Integer id = Integer.parseInt(this.ractor.txtCodigoActor.getText());
                if (this.ractor.txtCodigoActor.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.ractor.txtCodigoActor.setText("");
                    this.ractor.botones(false);
                    this.ractor.limpiar();
                    this.ractor.visible(true);
                } else {
                    if (this.acDAO.Buscar(id) == true) {
                        this.acDAO.Llenar(id, this.ractor.txtNombreRealActor, this.ractor.txtNombreArtistico);
                        this.ractor.btnModificar.setEnabled(true);
                        this.ractor.btnEliminar.setEnabled(true);
                        this.ractor.btnRegistrarActor.setEnabled(false);
                        this.ractor.visible(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "Actor " + id + ", no encontrado");
                        this.ractor.txtCodigoActor.setText("");
                        this.ractor.botones(false);
                        this.ractor.limpiar();
                        this.ractor.visible(true);
                    }

                    this.ractor.btnRegistrarActor.setEnabled(true);
                }

            } else if (ae.getSource() == ractor.btnModificar) {
                this.ractor.btnRegistrarActor.setText("Guardar");
                this.ractor.btnRegistrarActor.setEnabled(true);
                this.ractor.btnModificar.setEnabled(false);
                this.ractor.btnEliminar.setEnabled(false);
                this.ractor.txtCodigoActor.setEnabled(false);
                this.ractor.visible(true);

            } else if (ae.getSource() == ractor.btnEliminar) {

                Integer id = Integer.parseInt(this.ractor.txtCodigoActor.getText());
                if (this.ractor.txtCodigoActor.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.ractor.txtCodigoActor.setText("");
                    this.ractor.visible(true);
                    this.ractor.botones(false);
                    this.ractor.limpiar();
                } else {
                    if (this.acDAO.Buscar(id)) {
                        actor = new Actor();
                        this.acDAO.eliminar(id);
                        this.ractor.txtCodigoActor.setText("");
                        this.ractor.botones(false);
                        this.ractor.visible(true);
                        this.ractor.limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Actor " + id + ", no encontrado");
                        this.ractor.txtCodigoActor.setText("");
                        this.ractor.visible(true);
                        this.ractor.botones(false);
                        this.ractor.limpiar();
                    }
                    this.ractor.limpiar();
                    this.ractor.txtCodigoActor.setText("");
                    this.ractor.txtCodigoActor.setEnabled(true);
                    this.ractor.btnRegistrarActor.setEnabled(true);
                }

            } else if (ae.getSource() == ractor.btnVolver) {
                this.ractor.dispose();
                this.ractor.limpiar();
                this.amenu.setVisible(true);
            } //Registrar personaje
            else if (ae.getSource() == rpe.btnRegistrarPersonaje) {

                if (this.rpe.txtCodigoPersonaje.getText().equalsIgnoreCase("") || this.rpe.txtNombrePersonaje.getText().equalsIgnoreCase("")
                        || this.rpe.cbActor.getSelectedIndex() == 0/*Puede dejar cap sin llenar*/) {
                    JOptionPane.showConfirmDialog(null, "Llenar todos los campos");
                    rpe.txtCodigoPersonaje.requestFocus();
                    this.rpe.limpiar();

                } else {
                    Integer id = Integer.parseInt(this.rpe.txtCodigoPersonaje.getText());
                    if (this.peDAO.Buscar(id)) {
                        if (this.rpe.btnRegistrarPersonaje.getText().equalsIgnoreCase("Guardar")) {
                            String nomp = this.rpe.txtNombrePersonaje.getText();
                            Integer id_cap = Integer.parseInt(this.rpe.cbCap.getSelectedItem().toString());
                            Integer id_ac = Integer.parseInt(this.rpe.cbActor.getSelectedItem().toString());

                            this.pe.setCodigo_personaje(id);
                            this.pe.setNombre_personaje(nomp);
                            this.pe.setId_Actor(id_ac);
                            this.pe.setId_capitulo(id_cap);

                            this.peDAO.actualizarPer(pe, id);
                            this.rpe.limpiar();
                            this.rpe.btnModificarPersonaje.setEnabled(false);
                            this.rpe.btnEliminarPersonaje.setEnabled(false);
                            this.rpe.btnRegistrarPersonaje.setText("Registrar");
                        } else {
                            JOptionPane.showMessageDialog(null, "Personaje ya esta registrado");
                            this.rpe.limpiar();
                        }
                    } else {
                        if (this.rpe.btnRegistrarPersonaje.getText().equalsIgnoreCase("Registrar")) {
                            String nomp = this.rpe.txtNombrePersonaje.getText();
                            Integer id_cap = Integer.parseInt(this.rpe.cbCap.getSelectedItem().toString());
                            Integer id_ac = Integer.parseInt(this.rpe.cbActor.getSelectedItem().toString());
                            pe = new Personaje(id, nomp, id_cap, id_ac);
                            this.peDAO.registrarPerosnaje(pe);
                            this.rpe.limpiar();
                            this.rpe.btnModificarPersonaje.setEnabled(false);
                            this.rpe.btnEliminarPersonaje.setEnabled(false);
                            this.rpe.btnRegistrarPersonaje.setText("Registrar");
                        }
                    }
                    this.rpe.limpiar();
                    this.rpe.txtCodigoPersonaje.setText("");
                    this.rpe.txtCodigoPersonaje.setEnabled(true);
                }

            } else if (ae.getSource() == rpe.btnBuscarPersonaje) {

                Integer id = Integer.parseInt(this.rpe.txtCodigoPersonaje.getText());
                if (this.rpe.txtCodigoPersonaje.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.rpe.txtCodigoPersonaje.setText("");
                    this.rpe.botones(false);
                    this.rpe.limpiar();
                    this.rpe.visible(true);
                } else {
                    if (this.peDAO.Buscar(id) == true) {
                        this.peDAO.Llenar(id, this.rpe.txtNombrePersonaje, this.rpe.cbActor, this.rpe.cbCap);
                        this.rpe.btnModificarPersonaje.setEnabled(true);
                        this.rpe.btnEliminarPersonaje.setEnabled(true);
                        this.rpe.btnRegistrarPersonaje.setEnabled(false);
                        this.rpe.visible(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "Personaje " + id + ", no encontrado");
                        this.rpe.txtCodigoPersonaje.setText("");
                        this.rpe.botones(false);
                        this.rpe.limpiar();
                        this.rpe.visible(true);
                    }

                    this.rpe.btnRegistrarPersonaje.setEnabled(true);
                }

            } else if (ae.getSource() == rpe.btnModificarPersonaje) {
                this.rpe.btnRegistrarPersonaje.setText("Guardar");
                this.rpe.btnRegistrarPersonaje.setEnabled(true);
                this.rpe.btnModificarPersonaje.setEnabled(false);
                this.rpe.btnEliminarPersonaje.setEnabled(false);
                this.rpe.txtCodigoPersonaje.setEnabled(false);
                this.rpe.visible(true);

            } else if (ae.getSource() == rpe.btnEliminarPersonaje) {

                Integer id = Integer.parseInt(this.rpe.txtCodigoPersonaje.getText());
                if (this.rpe.txtCodigoPersonaje.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.rpe.txtCodigoPersonaje.setText("");
                    this.rpe.visible(true);
                    this.rpe.botones(false);
                    this.rpe.limpiar();
                } else {
                    if (this.peDAO.Buscar(id)) {
                        pe = new Personaje();
                        this.peDAO.eliminar(id);
                        this.rpe.txtCodigoPersonaje.setText("");
                        this.rpe.botones(false);
                        this.rpe.visible(true);
                        this.rpe.limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Personaje " + id + ", no encontrado");
                        this.rpe.txtCodigoPersonaje.setText("");
                        this.rpe.visible(true);
                        this.rpe.botones(false);
                        this.rpe.limpiar();
                    }
                    this.rpe.limpiar();
                    this.rpe.txtCodigoPersonaje.setText("");
                    this.rpe.txtCodigoPersonaje.setEnabled(true);
                    this.rpe.btnRegistrarPersonaje.setEnabled(true);
                }

            } else if (ae.getSource() == rpe.btnVolver) {
                this.rpe.dispose();
                this.rpe.limpiar();
                this.rpe.cbActor.removeAllItems();
                this.rpe.cbCap.removeAllItems();
                this.amenu.setVisible(true);
            } //Registrar serie
            else if (ae.getSource() == this.rserie.btnActualizar) {
                this.rserie.btnActualizar.setEnabled(false);
                this.rserie.btnEliminar.setEnabled(false);
                this.rserie.btnRegistrar.setEnabled(true);
                this.rserie.visible(true);
                this.rserie.txtCodigo.setEnabled(false);
                this.rserie.btnRegistrar.setText("Guardar");

            } else if (ae.getSource() == this.rserie.btnRegistrar) {

                if ((this.rserie.txtCodigo.getText().equals("")) || (this.rserie.txtTitulo.getText().equals("")) || (this.rserie.txtGenero.getSelectedIndex() == 0)
                        || (this.rserie.txtAño.getText().equals("")) || (this.rserie.txtSinopsis.getText().equals("")) || (this.rserie.ruta.getText().equals(""))) {
                    JOptionPane.showConfirmDialog(null, "Llenar todos los campos");
                    this.rserie.txtCodigo.requestFocus();
                    this.rserie.limpiar();

                } else {
                    Integer id = Integer.parseInt(this.rserie.txtCodigo.getText());
                    if (this.seDAO.Buscar(id)) {
                        if (this.rserie.btnRegistrar.getText().equalsIgnoreCase("Guardar")) {
                            String titulo = this.rserie.txtTitulo.getText();
                            String sinopsis = this.rserie.txtSinopsis.getText();
                            int anio = Integer.parseInt(this.rserie.txtAño.getText());
                            Integer id_genero = this.rserie.txtGenero.getSelectedIndex();
                            String ruta = this.rserie.ruta.getText();
                            this.serie.setCod_serie(id);
                            this.serie.setTitulo(titulo);
                            this.serie.setSinopsis(sinopsis);
                            this.serie.setAno_inicio(anio);
                            this.serie.setCod_genero(id_genero);
                            this.serie.setImagen(ruta);

                            this.seDAO.Actualizar(serie, id);
                            this.rserie.limpiar();
                            this.rserie.botones(false);
                            this.rserie.btnRegistrar.setText("Registrar");
                        } else {
                            JOptionPane.showMessageDialog(null, "Serie ya esta registrada");
                            this.rserie.limpiar();
                        }
                    } else {
                        if (this.rserie.btnRegistrar.getText().equalsIgnoreCase("Registrar")) {
                            String titulo = this.rserie.txtTitulo.getText();
                            String sinopsis = this.rserie.txtSinopsis.getText();
                            int anio = Integer.parseInt(this.rserie.txtAño.getText());
                            Integer id_genero = this.rserie.txtGenero.getSelectedIndex();
                            String ruta = this.rserie.ruta.getText();
                            serie = new Serie(id, titulo, anio, sinopsis, id_genero, ruta);
                            this.seDAO.guardar(Conexion.getConnection(), serie);
                            this.rserie.limpiar();
                            this.rserie.botones(false);
                            this.rserie.btnRegistrar.setText("Registrar");
                        }
                    }
                    this.rserie.limpiar();
                    this.rserie.txtCodigo.setText("");
                    this.rserie.txtCodigo.setEnabled(true);
                }

            } else if (ae.getSource() == this.rserie.btnBuscar) {
                if (this.rserie.txtCodigo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                } else {
                    Integer id = Integer.parseInt(this.rserie.txtCodigo.getText());
                    boolean n = seDAO.Buscar(id);
                    if (n == true) {
                        this.rserie.botones(true);

                        this.rserie.btnRegistrar.setEnabled(false);
                        this.rserie.txtCodigo.setEnabled(false);
                        this.rserie.visible(false);
                        seDAO.Llenar(id, this.rserie.txtTitulo, this.rserie.txtAño, this.rserie.txtSinopsis, this.rserie.ruta, this.rserie.txtGenero, this.rserie.imagen);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró Serie: " + id);
                        this.rserie.limpiar();
                        this.rserie.botones(false);
                    }
                }
            } else if (ae.getSource() == this.rserie.btnEliminar) {
                Integer id = Integer.parseInt(this.rserie.txtCodigo.getText());
                if (this.rserie.txtCodigo.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.rserie.txtCodigo.setText("");
                    this.rserie.visible(true);
                    this.rserie.botones(false);
                    this.rserie.limpiar();
                } else {
                    if (this.seDAO.Buscar(id)) {
                        serie = new Serie();
                        this.seDAO.Eliminar(id);
                        this.rserie.txtCodigo.setText("");
                        this.rserie.botones(false);
                        this.rserie.visible(true);
                        this.rserie.limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Personaje " + id + ", no encontrado");
                        this.rserie.txtCodigo.setText("");
                        this.rserie.visible(true);
                        this.rserie.botones(false);
                        this.rserie.limpiar();
                    }
                    this.rserie.limpiar();
                    this.rserie.txtCodigo.setText("");
                    this.rserie.txtCodigo.setEnabled(true);
                    this.rserie.btnRegistrar.setEnabled(true);
                }

            } else if (ae.getSource() == this.rserie.btnVolver) {
                this.rserie.dispose();
                this.rserie.limpiar();
                this.rserie.txtGenero.removeAllItems();
                this.amenu.setVisible(true);
            } /*//Registrar temporada*/ else if (ae.getSource() == this.rtemp.btnActualizar) {
                this.rtemp.btnActualizar.setEnabled(false);

                this.rtemp.btnEliminar.setEnabled(false);
                this.rtemp.btnRegistrar.setEnabled(true);
                this.rtemp.visible(true);
                this.rtemp.txtCodigo.setEnabled(false);
                this.rtemp.btnRegistrar.setText("Guardar");

            } else if (ae.getSource() == this.rtemp.btnRegistrar) {

                if ((this.rtemp.txtCodigo.getText().equals("")) || (this.rtemp.txtFechaE.getText().equals("")) || (this.rtemp.id_serie.getSelectedIndex() == 0)
                        || (this.rtemp.txtFechaP.getText().equals(""))) {
                    JOptionPane.showConfirmDialog(null, "Llenar todos los campos");
                    this.rtemp.txtCodigo.requestFocus();
                    this.rtemp.limpiar();

                } else {
                    Integer id = Integer.parseInt(this.rtemp.txtCodigo.getText());
                    if (this.temDAO.Buscar(id)) {
                        if (this.rtemp.btnRegistrar.getText().equalsIgnoreCase("Guardar")) {
                            String fechaprod = this.rtemp.txtFechaP.getText();
                            String fechaestreno = this.rtemp.txtFechaE.getText();
                            Integer id_serie = Integer.parseInt(this.rtemp.id_serie.getSelectedItem().toString());

                            this.temp.setCod_temporada(id);
                            this.temp.setCod_serie(id_serie);
                            this.temp.setFechaE(fechaestreno);
                            this.temp.setFechaP(fechaprod);

                            this.temDAO.Actualizar(temp, id);
                            this.rtemp.limpiar();
                            this.rtemp.botones(false);
                            this.rtemp.btnRegistrar.setText("Registrar");
                        } else {
                            JOptionPane.showMessageDialog(null, "Temporada ya esta registrada");
                            this.rtemp.limpiar();
                        }
                    } else {
                        if (this.rtemp.btnRegistrar.getText().equalsIgnoreCase("Registrar")) {
                            String fechaprod = this.rtemp.txtFechaP.getText();
                            String fechaestreno = this.rtemp.txtFechaE.getText();
                            Integer id_serie = Integer.parseInt(this.rtemp.id_serie.getSelectedItem().toString());

                            temp = new Temporada(fechaprod, fechaestreno, id, id_serie);
                            this.temDAO.guardar(Conexion.getConnection(), temp);
                            this.rtemp.limpiar();
                            this.rtemp.botones(false);
                            this.rtemp.btnRegistrar.setText("Registrar");
                        }
                    }
                    this.rtemp.limpiar();
                    this.rtemp.txtCodigo.setText("");
                    this.rtemp.txtCodigo.setEnabled(true);
                }

            } else if (ae.getSource() == this.rtemp.btnBuscar) {
                if (this.rtemp.txtCodigo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                } else {
                    Integer id = Integer.parseInt(this.rtemp.txtCodigo.getText());
                    boolean n = temDAO.Buscar(id);
                    if (n == true) {
                        this.rtemp.botones(true);
                        this.rtemp.btnRegistrar.setEnabled(false);
                        this.rtemp.visible(false);
                        temDAO.Llenar(id, this.rtemp.txtFechaP, this.rtemp.txtFechaE, this.rtemp.id_serie);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró Temporada: " + id);
                        this.rtemp.limpiar();
                        this.rtemp.botones(false);
                    }
                }
            } else if (ae.getSource() == this.rtemp.btnEliminar) {
                Integer id = Integer.parseInt(this.rtemp.txtCodigo.getText());
                if (this.rtemp.txtCodigo.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.rtemp.txtCodigo.setText("");
                    this.rtemp.visible(true);
                    this.rtemp.botones(false);
                    this.rtemp.limpiar();
                } else {
                    if (this.temDAO.Buscar(id)) {
                        temp = new Temporada();
                        this.temDAO.Eliminar(id);
                        this.rtemp.txtCodigo.setText("");
                        this.rtemp.botones(false);
                        this.rtemp.visible(true);
                        this.rtemp.limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Temporada" + id + ", no encontrado");
                        this.rtemp.txtCodigo.setText("");
                        this.rtemp.visible(true);
                        this.rtemp.botones(false);
                        this.rtemp.limpiar();
                    }
                    this.rtemp.limpiar();
                    this.rtemp.txtCodigo.setText("");
                    this.rtemp.txtCodigo.setEnabled(true);
                    this.rtemp.btnRegistrar.setEnabled(true);
                }

            } else if (ae.getSource() == this.rtemp.btnVolver) {
                this.rtemp.dispose();
                this.rtemp.limpiar();
                this.rtemp.id_serie.removeAllItems();
                this.amenu.setVisible(true);
            } //Registrar Capitulo
            else if (ae.getSource() == this.rcap.btnActualizar) {
                this.rcap.btnActualizar.setEnabled(false);

                this.rcap.btnEliminar.setEnabled(false);
                this.rcap.btnRegistrar.setEnabled(true);
                this.rcap.visible(true);
                this.rcap.txtCodigo.setEnabled(false);
                this.rcap.btnRegistrar.setText("Guardar");

            } else if (ae.getSource() == this.rcap.btnRegistrar) {

                if ((this.rcap.txtCodigo.getText().equals("")) || (this.rcap.txtTitulo.getText().equals(""))
                        || (this.rcap.cbTemporada.getSelectedIndex() == 0) || (this.rcap.txtDuracion.getText().equals("")) || (this.rcap.txtSinopsis.getText().equals(""))) {
                    JOptionPane.showConfirmDialog(null, "Llenar todos los campos");
                    this.rcap.txtCodigo.requestFocus();
                    this.rcap.limpiar();

                } else {
                    Integer id = Integer.parseInt(this.rcap.txtCodigo.getText());
                    if (this.capDAO.Buscar(id)) {
                        if (this.rcap.btnRegistrar.getText().equalsIgnoreCase("Guardar")) {
                            String titulo = this.rcap.txtTitulo.getText();
                            String sinopsis = this.rcap.txtSinopsis.getText();
                            int duracion = Integer.parseInt(this.rcap.txtDuracion.getText());
                            Integer id_temp = Integer.parseInt(this.rcap.cbTemporada.getSelectedItem().toString());
                            Integer id_sub = Integer.parseInt(this.rcap.cbSubti.getSelectedItem().toString());
                            cap.setId_capitulo(id);
                            cap.setTitulo(titulo);
                            cap.setSinopsis(sinopsis);
                            cap.setDuracion(duracion);
                            cap.setId_tempoTemporada(id_temp);
                            cap.setId_subtitulos(id_sub);

                            this.capDAO.Actualizar(cap, id);
                            this.rcap.limpiar();
                            this.rcap.botones(false);
                            this.rcap.btnRegistrar.setText("Registrar");
                        } else {
                            JOptionPane.showMessageDialog(null, "Capítulo ya esta registrada");
                            this.rcap.limpiar();
                        }
                    } else {
                        if (this.rcap.btnRegistrar.getText().equalsIgnoreCase("Registrar")) {
                            String titulo = this.rcap.txtTitulo.getText();
                            String sinopsis = this.rcap.txtSinopsis.getText();
                            int duracion = Integer.parseInt(this.rcap.txtDuracion.getText());
                            Integer id_temp = Integer.parseInt(this.rcap.cbTemporada.getSelectedItem().toString());
                            Integer id_sub = Integer.parseInt(this.rcap.cbSubti.getSelectedItem().toString());
                            System.out.println(id_sub);
                            cap.setId_capitulo(id);
                            cap.setTitulo(titulo);
                            cap.setSinopsis(sinopsis);
                            cap.setDuracion(duracion);
                            cap.setId_tempoTemporada(id_temp);
                            cap.setId_subtitulos(id_sub);

                            this.capDAO.guardar(Conexion.getConnection(), cap);
                            this.rcap.limpiar();
                            this.rcap.botones(false);
                            this.rcap.btnRegistrar.setText("Registrar");
                        }
                    }
                    this.rcap.limpiar();
                    this.rcap.txtCodigo.setText("");
                    this.rcap.txtCodigo.setEnabled(true);
                }

            } else if (ae.getSource() == this.rcap.btnBuscar) {
                if (this.rcap.txtCodigo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                } else {
                    Integer id = Integer.parseInt(this.rcap.txtCodigo.getText());
                    boolean n = capDAO.Buscar(id);
                    if (n == true) {
                        this.rcap.botones(true);
                        this.rcap.btnRegistrar.setEnabled(false);
                        this.rcap.txtCodigo.setEnabled(false);
                        this.rcap.visible(false);

                        capDAO.Llenar(id, this.rcap.txtTitulo, this.rcap.txtDuracion, this.rcap.txtSinopsis, this.rcap.cbTemporada, this.rcap.cbSubti);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró Capítulo: " + id);
                        this.rcap.limpiar();
                        this.rcap.botones(false);
                    }
                }
            } else if (ae.getSource() == this.rcap.btnEliminar) {
                Integer id = Integer.parseInt(this.rcap.txtCodigo.getText());
                if (this.rcap.txtCodigo.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Llenar los campos");
                    this.rcap.txtCodigo.setText("");
                    this.rcap.visible(true);
                    this.rcap.botones(false);
                    this.rcap.limpiar();
                } else {
                    if (this.capDAO.Buscar(id)) {
                        cap = new Capitulo();
                        this.capDAO.Eliminar(id);
                        this.rcap.txtCodigo.setText("");
                        this.rcap.botones(false);
                        this.rcap.visible(true);
                        this.rcap.limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Capítulo " + id + ", no encontrado");
                        this.rcap.txtCodigo.setText("");
                        this.rcap.visible(true);
                        this.rcap.botones(false);
                        this.rcap.limpiar();
                    }
                    this.rcap.limpiar();
                    this.rcap.txtCodigo.setText("");
                    this.rcap.txtCodigo.setEnabled(true);
                    this.rcap.btnRegistrar.setEnabled(true);
                }

            } else if (ae.getSource() == this.rcap.bntVolver) {
                this.rcap.dispose();

                this.amenu.setVisible(true);
                this.rcap.cbSubti.removeAllItems();
                this.rcap.cbTemporada.removeAllItems();
                this.rcap.limpiar();

            } //Ver serie
            else if (ae.getSource() == this.vserie.btnBuscarGe) {
                //buecar
                Integer genero = this.vserie.jComboBox1.getSelectedIndex();
                v.visualizarGenro(this.vserie.jTable1, genero);
            } else if (ae.getSource() == this.vserie.btnFavoritos) {
                this.vserie.dispose();
                this.fav.setVisible(true);
                v.visualizarFavo(this.fav.tablaFavoritos, idUsu);

            } else if (ae.getSource() == this.fav.jButton1) {
                this.fav.dispose();
                this.vserie.setVisible(true);
            } else if (ae.getSource() == this.vserie.btnVer) {
                int n = this.vserie.jTable1.getSelectedRow();

                if (n == -1) {
                    JOptionPane.showMessageDialog(null, "Llenar todos los espacios, o seleccionar fila");
                } else {
                    idSerie = (Integer) (this.vserie.jTable1.getValueAt(n, 0));
                    this.vserie.dispose();
                    this.vtemp.setVisible(true);
                    v.Llenar(idSerie, this.vtemp.lbSerie, this.vtemp.lbAño, this.vtemp.jTextArea1, this.vtemp.lbimagen);
                    this.vtemp.llenar(this.vtemp.cbTemporadas, idSerie);

                }

            } else if (ae.getSource() == this.vtemp.jButton1) {
                Integer tem = Integer.parseInt(this.vtemp.cbTemporadas.getSelectedItem().toString());
                this.v.visualizarCap(this.vtemp.tablaCap, tem);
            } else if (ae.getSource() == this.vtemp.btnAtras) {
                this.vtemp.dispose();
                this.vserie.setVisible(true);
            } else if (ae.getSource() == this.vtemp.btnFavo) {
                v.guardar(Conexion.getConnection(), idUsu, idSerie);

            } else if (ae.getSource() == this.vtemp.btnVer) {
                int nc = this.vtemp.tablaCap.getSelectedRow();

                if (nc == -1) {
                    JOptionPane.showMessageDialog(null, "Llenar todos los espacios, o seleccionar fila");
                } else {

                    idCap = (Integer) (this.vtemp.tablaCap.getValueAt(nc, 0));
                    System.out.println(idCap);
                    this.vtemp.dispose();
                    this.vcap.setVisible(true);
                    this.v.LlenarCap(idCap, this.vcap.lbNomCap, this.vcap.lbDuracion, this.vcap.jTextArea1);
                    this.v.visualizarPer(this.vcap.tablaPer, idCap);
                }
            } else if (ae.getSource() == this.vcap.btnAtras) {
                this.vcap.dispose();
                this.vserie.setVisible(true);
            } else if (ae.getSource() == this.vcap.jButton2) {
                JOptionPane.showMessageDialog(null, "Reproduciendo capítulo");

            }
        } catch (Exception exs) {
            JOptionPane.showMessageDialog(null, exs);
        }
    }

    private void actionListener(ActionListener controlador) {
        //Menu administrador
        this.amenu.btnPer.addActionListener(controlador);
        this.amenu.btnActor.addActionListener(controlador);
        this.amenu.btnCap.addActionListener(controlador);

        this.amenu.btnSalir.addActionListener(controlador);
        this.amenu.btnSeries.addActionListener(controlador);
        this.amenu.btnSub.addActionListener(controlador);
        this.amenu.btnTep.addActionListener(controlador);
        //Entrar      
        this.e.btnEntrar.addActionListener(controlador);
        //IniciarSesion
        this.isesion.btnEntrarSesion.addActionListener(controlador);
        this.isesion.btnRegistrarse.addActionListener(controlador);
        this.isesion.btnVolver.addActionListener(controlador);
        //registrar usuario
        this.rusu.btnRegistrar.addActionListener(controlador);
        this.rusu.btnBuscar.addActionListener(controlador);
        this.rusu.btnActualizar.addActionListener(controlador);
        this.rusu.btnEliminar.addActionListener(controlador);
        this.rusu.btnVolver.addActionListener(controlador);
        //Registrar subtitulo
        this.rsub.btnRegistrarSubtitulos.addActionListener(controlador);
        this.rsub.btnActualizar.addActionListener(controlador);
        this.rsub.btnBuscar.addActionListener(controlador);
        this.rsub.btnElimminar.addActionListener(controlador);
        this.rsub.btnVolver.addActionListener(controlador);
        //Registrar critica
        this.rcri.btnGuardar.addActionListener(controlador);
        this.rcri.btnVolver.addActionListener(controlador);
        //Actor
        this.ractor.btnBuscarActor.addActionListener(controlador);
        this.ractor.btnEliminar.addActionListener(controlador);
        this.ractor.btnModificar.addActionListener(controlador);
        this.ractor.btnRegistrarActor.addActionListener(controlador);
        this.ractor.btnVolver.addActionListener(controlador);
        //Personaje
        this.rpe.btnBuscarPersonaje.addActionListener(controlador);
        this.rpe.btnEliminarPersonaje.addActionListener(controlador);
        this.rpe.btnModificarPersonaje.addActionListener(controlador);
        this.rpe.btnRegistrarPersonaje.addActionListener(controlador);
        this.rpe.btnVolver.addActionListener(controlador);
        //Serie
        this.rserie.btnActualizar.addActionListener(controlador);
        this.rserie.btnBuscar.addActionListener(controlador);
        this.rserie.btnEliminar.addActionListener(controlador);
        this.rserie.btnImagen.addActionListener(controlador);
        this.rserie.btnRegistrar.addActionListener(controlador);
        this.rserie.btnVolver.addActionListener(controlador);
        //Temporada
        this.rtemp.btnActualizar.addActionListener(controlador);
        this.rtemp.btnBuscar.addActionListener(controlador);
        this.rtemp.btnEliminar.addActionListener(controlador);
        this.rtemp.btnRegistrar.addActionListener(controlador);
        this.rtemp.btnVolver.addActionListener(controlador);
        //Capitulo
        this.rcap.bntVolver.addActionListener(controlador);
        this.rcap.btnActualizar.addActionListener(controlador);
        this.rcap.btnBuscar.addActionListener(controlador);
        this.rcap.btnEliminar.addActionListener(controlador);
        this.rcap.btnRegistrar.addActionListener(controlador);

        //verSerie
        this.vserie.btnFavoritos.addActionListener(controlador);
        this.vserie.btnSalirUsus.addActionListener(controlador);
        this.vserie.btnVer.addActionListener(controlador);
        this.vserie.btnBuscarGe.addActionListener(controlador);

        //Vertmeporada
        this.vtemp.jButton1.addActionListener(controlador);
        this.vtemp.btnAtras.addActionListener(controlador);
        this.vtemp.btnFavo.addActionListener(controlador);
        this.vtemp.btnVer.addActionListener(controlador);
        //Vercap
        this.vcap.btnAtras.addActionListener(controlador);
        this.vcap.jButton2.addActionListener(controlador);
    }

    public void botones() {
        this.rusu.limpiar();
        this.rusu.btnActualizar.setEnabled(false);
        this.rusu.btnEliminar.setEnabled(false);
        this.rusu.btnRegistrar.setEnabled(true);
    }

}
