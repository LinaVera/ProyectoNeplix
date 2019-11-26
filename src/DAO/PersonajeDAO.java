package DAO;

import VO.Personaje;
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

public class PersonajeDAO {

    public void registrarPerosnaje(Personaje pe) {

        String sql = "INSERT INTO personaje (id_personaje,rol,id_actor,id_capitulo)"
                + "VALUES (?,?,?,?)";
        try (Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pe.getCodigo_personaje());
            ps.setString(2, pe.getNombre_personaje());
            ps.setInt(3, pe.getId_Actor());
            ps.setInt(4, pe.getId_capitulo());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Personaje registrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public boolean Buscar(Integer codigo) {
        boolean esta = false;
        Integer cod2 = 0;
        String sql = "SELECT * FROM personaje WHERE id_personaje = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                cod2 = rs.getInt("id_personaje");
            }
            esta = Objects.equals(cod2, codigo);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return esta;
    }

    public void Llenar(Integer codigo, JTextField np, JComboBox ac, JComboBox cap) {

        String sql = "SELECT * FROM personaje WHERE id_personaje = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                np.setText(rs.getString("rol"));
                ac.setSelectedItem(rs.getString("id_actor"));
                cap.setSelectedItem(rs.getString("id_capitulo"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void actualizarPer(Personaje pe, Integer codigo) {
        String sql = "UPDATE personaje SET rol = '" + pe.getNombre_personaje()
                + "', id_actor = '" + pe.getId_Actor()
                + "', id_capitulo = '" + pe.getId_capitulo() + "' WHERE id_personaje = '" + codigo + "'";
        try (Connection conexion2 = Conexion.getConnection();
                PreparedStatement pst = conexion2.prepareStatement(sql)) {
            JOptionPane.showMessageDialog(null, "Se actualizó Personaje");
            pst.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void eliminar(Integer codigo) {
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement()) {
            String sql = "DELETE FROM personaje WHERE id_personaje = '" + codigo + "'";
            declaracion.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se eliminó Personaje");

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
            listains.add("Seleccione "+n);
            while (rs.next()) {
                listains.add(rs.getString("id_"+n));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return listains;
    }

}
