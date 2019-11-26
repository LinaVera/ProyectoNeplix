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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SerieDAO {

    public void guardar(Connection conexion, Serie serie) throws SQLException {
        String sql = "INSERT INTO serie (id_serie,titulo,anio_inicio,sinopsis,id_genero,imagen, img) "
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            FileInputStream fi = null;
            PreparedStatement consulta;
            consulta = conexion.prepareStatement(sql);
            consulta.setInt(1, serie.getCod_serie());
            consulta.setString(2, serie.getTitulo());
            consulta.setInt(3, serie.getAno_inicio());
            consulta.setString(4, serie.getSinopsis());
            consulta.setInt(5, serie.getCod_genero());
            consulta.setString(6, serie.getImagen());
            File file = new File(serie.getImagen());
            fi = new FileInputStream(file);
            consulta.setBinaryStream(7, fi);
            consulta.executeUpdate();
            JOptionPane.showMessageDialog(null, "Serie registrada");
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Actualizar(Serie serie, Integer codigo) {

        String sql = "UPDATE serie SET titulo=?,anio_inicio=?,sinopsis=?"
                + ",id_genero=?,imagen=?, img=?  WHERE id_serie='" + codigo + "'";
        try (Connection conexion2 = Conexion.getConnection();
                PreparedStatement consulta = conexion2.prepareStatement(sql)) {
            FileInputStream fi = null;
            consulta.setString(1, serie.getTitulo());
            consulta.setInt(2, serie.getAno_inicio());
            consulta.setString(3, serie.getSinopsis());

            consulta.setInt(4, serie.getCod_genero());
            // archivo=new FileInputStream(serie.getImagen());
            consulta.setString(5, serie.getImagen());
            File file = new File(serie.getImagen());
            fi = new FileInputStream(file);
            consulta.setBinaryStream(6, fi);
            consulta.execute();
            JOptionPane.showMessageDialog(null, "Se actualizó Serie");
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Llenar(Integer codigo, JTextField titulo, JTextField año, JTextArea sinop, JTextField ruta, JComboBox genero, JLabel lbimg) {

        String sql = "SELECT titulo, anio_inicio, id_genero, sinopsis, imagen, img FROM serie WHERE id_serie = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                titulo.setText(rs.getString(1));
                año.setText(rs.getString(2));
                genero.setSelectedIndex(rs.getInt(3));
                sinop.setText(rs.getString(4));
                ruta.setText(rs.getString(5));
                Blob fotos = rs.getBlob(6);
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

    public boolean Buscar(Integer codigo) {
        boolean esta = false;
        Integer cod2 = 0;
        String sql = "SELECT id_serie FROM serie WHERE id_serie = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                cod2 = rs.getInt("id_serie");
            }
            esta = Objects.equals(cod2, codigo);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return esta;
    }

    public void Eliminar(Integer codigo) {
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement()) {
            String sql = "DELETE FROM serie WHERE id_serie = '" + codigo + "'";
            declaracion.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se eliminó Serie");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
     public ArrayList<String> llenar(String n) {

        ArrayList<String> listains = new ArrayList<>();
        String sql = "SELECT * FROM " + n;
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            listains.add("Seleccione genero*:");
            while (rs.next()) {
                listains.add(rs.getString(n));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return listains;
    }
}
