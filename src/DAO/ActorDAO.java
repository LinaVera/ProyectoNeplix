package DAO;

import VO.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ActorDAO {

   
    public void registrarActor(Actor ac) {

        String sql = "INSERT INTO actor(id_actor, nombre_real, nombre_pers) VALUES(?,?,?)";
        try (Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ac.getId_actor());
            ps.setString(2, ac.getNombre_real());
            ps.setString(3, ac.getNombre_pers());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Actor registrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public boolean Buscar(Integer codigo) {
        boolean esta = false;
        Integer cod2 = 0;
        String sql = "SELECT * FROM actor WHERE id_actor = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                cod2 = rs.getInt("id_actor");
            }
            esta = Objects.equals(cod2, codigo);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return esta;
    }

    public void Llenar(Integer codigo, JTextField nr, JTextField na) {

        String sql = "SELECT * FROM actor WHERE id_actor = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                nr.setText(rs.getString("nombre_real"));
                na.setText(rs.getString("nombre_pers"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void actualizar(Actor ac, Integer codigo) {
        String sql = "UPDATE actor SET nombre_real = '" + ac.getNombre_real()
                + "', nombre_pers= '" + ac.getNombre_pers() + "' WHERE id_actor = '" + codigo + "'";
        try (Connection conexion2 = Conexion.getConnection();
                PreparedStatement pst = conexion2.prepareStatement(sql)) {
            JOptionPane.showMessageDialog(null, "Se actualizó Actor");
            pst.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void eliminar(Integer codigo) {
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement()) {
            String sql = "DELETE FROM actor WHERE id_actor = '" + codigo + "'";
            declaracion.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se eliminó Actor");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
