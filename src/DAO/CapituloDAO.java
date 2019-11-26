package DAO;

import VO.Capitulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CapituloDAO {

    public void guardar(Connection conexion, Capitulo capi) throws SQLException {
        try {

            PreparedStatement consulta;
            consulta = conexion.prepareStatement("INSERT INTO capitulo (id_capitulo,titulo,sinopsis,duracion,id_temporada,id_sub) "
                    + "VALUES(?,?,?,?,?,?)");
            consulta.setInt(1, capi.getId_capitulo());
            consulta.setString(2, capi.getTitulo());
            consulta.setString(3, capi.getSinopsis());
            consulta.setInt(4, capi.getDuracion());
            consulta.setInt(5, capi.getId_tempoTemporada());
            consulta.setInt(6, capi.getId_subtitulos());
            consulta.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro guardado");
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void Actualizar(Capitulo c, Integer codigo) {
        String sql = "UPDATE capitulo SET titulo= '" + c.getTitulo() + "',sinopsis = '" + c.getSinopsis()
                + "',duracion = '" + c.getDuracion() + "',id_temporada = '" + c.getId_tempoTemporada()
                + "',id_subtitulo = '" + c.getId_subtitulos() + "' WHERE id_capitulo = '" + codigo + "'";

        try (Connection conexion2 = Conexion.getConnection();
                PreparedStatement pst = conexion2.prepareStatement(sql)) {
            JOptionPane.showMessageDialog(null, "Se actualizó Capitulo");
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void Llenar(Integer codigo, JTextField titulo, JTextField duracion, JTextArea sinopsis, JComboBox id_tem, JComboBox id_sub) {

        String sql = "SELECT * FROM capitulo WHERE id_capitulo = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                titulo.setText(rs.getString("titulo"));
                duracion.setText(rs.getString("duracion"));
                sinopsis.setText(rs.getString("sinopsis"));
                id_tem.setSelectedItem(rs.getString("id_temporada"));
                id_sub.setSelectedItem(rs.getString("id_subtitulo"));

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean Buscar(Integer codigo) {
        boolean esta = false;
        Integer cod2 = 0;
        String sql = "SELECT id_capitulo FROM capitulo WHERE id_capitulo = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                cod2 = rs.getInt("id_capitulo");
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
            String sql = "DELETE FROM capitulo WHERE id_capitulo = '" + codigo + "'";
            declaracion.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se eliminó registro");
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
            listains.add("Seleccione "+n+"*:");
            while (rs.next()) {
                listains.add(rs.getString("id_"+n));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return listains;
    }
}
