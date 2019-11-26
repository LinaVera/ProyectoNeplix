package DAO;

import VO.Serie;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import javax.swing.table.DefaultTableModel;

public class Ver {

    public void visualizar(JTable tabla) {
        String sql = "SELECT s.id_serie, s.titulo, s.anio_inicio,s.sinopsis, g.genero FROM serie AS s, genero AS g WHERE s.id_genero= g.id_genero";

        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            tabla.setDefaultRenderer(Object.class, new tablaImagen());
            DefaultTableModel dt = new DefaultTableModel();
            dt.addColumn("Serie");
            dt.addColumn("Título");
            dt.addColumn("Año");
            dt.addColumn("Sinopsis");
            dt.addColumn("Genero");
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getObject(1);
                fila[1] = rs.getObject(2);
                fila[2] = rs.getObject(3);
                fila[3] = rs.getObject(4);
                fila[4] = rs.getObject(5);
                dt.addRow(fila);
            }
            tabla.setModel(dt);
        } catch (SQLException ex) {
            Logger.getLogger(Ver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void visualizarFavo(JTable tabla, Integer usu) {
        String sql = "SELECT * FROM favorito WHERE id_usuario='" + usu + "'";

        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            tabla.setDefaultRenderer(Object.class, new tablaImagen());
            DefaultTableModel dt = new DefaultTableModel();
            dt.addColumn("Serie");

            while (rs.next()) {
                Object[] fila = new Object[1];
                fila[0] = rs.getObject(2);
                dt.addRow(fila);

            }
            tabla.setModel(dt);

        } catch (SQLException ex) {
            Logger.getLogger(Ver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void visualizarCap(JTable tabla, Integer temp) {
        String sql = "SELECT * FROM capitulo AS c, temporada AS t WHERE t.id_temporada=c.id_temporada AND t.id_temporada='" + temp + "'";

        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            tabla.setDefaultRenderer(Object.class, new tablaImagen());
            DefaultTableModel dt = new DefaultTableModel();
            dt.addColumn("Capitulo");
            dt.addColumn("Titulo");
            dt.addColumn("Sinopsis");
            dt.addColumn("Duración");

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getObject(1);
                fila[1] = rs.getObject(2);
                fila[2] = rs.getObject(3);
                fila[3] = rs.getObject(4);
                dt.addRow(fila);

            }
            tabla.setModel(dt);

        } catch (SQLException ex) {
            Logger.getLogger(Ver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void visualizarGenro(JTable tabla, Integer g) {
        String sql = "SELECT s.id_serie, s.titulo, s.anio_inicio,s.sinopsis, g.genero FROM serie AS s, genero AS g WHERE s.id_genero= g.id_genero AND g.id_genero='" + g + "'";

        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            tabla.setDefaultRenderer(Object.class, new tablaImagen());
            DefaultTableModel dt = new DefaultTableModel();
            dt.addColumn("Serie");
            dt.addColumn("Título");
            dt.addColumn("Año");
            dt.addColumn("Sinopsis");
            dt.addColumn("Genero");
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getObject(1);
                fila[1] = rs.getObject(2);
                fila[2] = rs.getObject(3);
                fila[3] = rs.getObject(4);
                fila[4] = rs.getObject(5);
                dt.addRow(fila);

            }
            tabla.setModel(dt);

            /*
                Blob blob = rs.getBlob(7);
                byte[] data = blob.getBytes(1, (int) blob.length());
                BufferedImage img = null;
                img = ImageIO.read(new ByteArrayInputStream(data));
                ImageIcon icon = new ImageIcon(img);
                fila[1] = new JLabel(icon);*/
        } catch (SQLException ex) {
            Logger.getLogger(Ver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Llenar(Integer codigo, JLabel titulo, JLabel año, JTextArea sinop, JLabel lbimg) {

        String sql = "SELECT titulo, anio_inicio, sinopsis, img FROM serie WHERE id_serie = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                titulo.setText(rs.getString(1));
                año.setText(rs.getString(2));
                sinop.setText(rs.getString(3));
                Blob fotos = rs.getBlob(4);
                byte[] recu = fotos.getBytes(1, (int) (fotos.length()));
                BufferedImage imgee;
                imgee = ImageIO.read(new ByteArrayInputStream(recu));
                Image images = imgee.getScaledInstance(lbimg.getWidth(), lbimg.getHeight(), Image.SCALE_SMOOTH);
                lbimg.setIcon(new ImageIcon(images));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(SerieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> llenar(Integer se) {

        ArrayList<String> listains = new ArrayList<>();
        String sql = "SELECT t.id_temporada FROM temporada AS t, serie AS s WHERE s.id_serie= t.id_serie AND s.id_serie ='" + se + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            listains.add("Seleccione Temporada*:");
            while (rs.next()) {
                listains.add(rs.getString("id_temporada"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return listains;
    }

    public void guardar(Connection conexion, Integer usu, Integer ser) {
        String sql = "INSERT INTO favorito (id_usuario,id_serie) "
                + "VALUES(?,?)";
        try {
            FileInputStream fi = null;
            PreparedStatement consulta;
            consulta = conexion.prepareStatement(sql);
            consulta.setInt(1, usu);
            consulta.setInt(2, ser);

            consulta.executeUpdate();
            JOptionPane.showMessageDialog(null, "Favorito registrada");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void LlenarCap(Integer codigo, JLabel titulo, JLabel duracion, JTextArea sinop) {
        String sql = "SELECT titulo, sinopsis, duracion FROM capitulo WHERE id_capitulo = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                titulo.setText(rs.getString(1));
                
                sinop.setText(rs.getString(2));
                duracion.setText(rs.getString(3));
              
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } 
    }
     public void visualizarPer(JTable tabla, Integer cap) {
        String sql = "SELECT * FROM personaje WHERE id_capitulo='" + cap + "'";

        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            tabla.setDefaultRenderer(Object.class, new tablaImagen());
            DefaultTableModel dt = new DefaultTableModel();
            dt.addColumn("Personaje");
            dt.addColumn("Rol");
            dt.addColumn("Actor");
            

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getObject(1);
                fila[1] = rs.getObject(2);
                fila[2] = rs.getObject(3);
                
                dt.addRow(fila);

            }
            tabla.setModel(dt);

        } catch (SQLException ex) {
            Logger.getLogger(Ver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
