package DAO;

import VO.Temporada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class TemporadaDAO {

    public void guardar(Connection conexion, Temporada tempo) throws SQLException {
        try {

            PreparedStatement consulta;
            consulta = conexion.prepareStatement("INSERT INTO temporada (id_temporada,fecha_pro,fecha_estreno,id_serie) "
                    + "VALUES(?,?,?,?)");
            consulta.setInt(1, tempo.getCod_temporada());
            consulta.setString(2, tempo.getFechaP());
            consulta.setString(3, tempo.getFechaE());
            consulta.setInt(4, tempo.getCod_serie());
            consulta.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro guardado");
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void Actualizar(Temporada t, Integer codigo) {
        String sql = "UPDATE temporada SET fecha_pro= '" + t.getFechaP() + "',fecha_estreno = '" + t.getFechaE()
                + "',id_serie = '" + t.getCod_serie()
                + "' WHERE id_temporada = '" + codigo + "'";
        try (Connection conexion2 = Conexion.getConnection();
                PreparedStatement pst = conexion2.prepareStatement(sql)) {
            JOptionPane.showMessageDialog(null, "Se actualizó los datos");
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void Llenar(Integer codigo, JTextField fechaP, JTextField fechaE, JComboBox serie) {

        String sql = "SELECT * FROM temporada WHERE id_temporada = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                fechaP.setText(rs.getString("fecha_pro"));
                fechaE.setText(rs.getString("fecha_estreno"));
                serie.setSelectedItem(rs.getString("id_serie"));

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean Buscar(Integer codigo) {
        boolean esta = false;
        Integer cod2 = 0;
        String sql = "SELECT id_temporada FROM temporada WHERE id_temporada = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                cod2 = rs.getInt("id_temporada");
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
            String sql = "DELETE FROM temporada WHERE id_temporada = '" + codigo + "'";
            declaracion.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se eliminó registro");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
     public ArrayList<String> llenar() {

        ArrayList<String> listains = new ArrayList<>();
        String sql = "SELECT * FROM serie";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            listains.add("Seleccione serie*:");
            while (rs.next()) {
                listains.add(rs.getString("id_serie"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return listains;
    }
}
