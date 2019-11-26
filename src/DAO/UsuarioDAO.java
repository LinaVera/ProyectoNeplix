package DAO;

import VO.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UsuarioDAO {

    public Integer idUsu = 0;

    public void registrarUsuario(Usuario usu) {

        String sql = "INSERT INTO user(id_usuario, nombre, apellido, correo, fecha_nac, contraseña) VALUES(?,?,?,?,?,?)";
        try (Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usu.getCod_usuario());
            ps.setString(2, usu.getNombre());
            ps.setString(3, usu.getApellido());
            ps.setString(4, usu.getEmail());
            ps.setString(5, usu.getFechaNac());
            ps.setString(6, usu.getContraseña());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Usuario registrado");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public boolean Buscar(Integer codigo) {
        boolean esta = false;
        Integer cod2 = 0;
        String sql = "SELECT * FROM user WHERE id_usuario = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                cod2 = rs.getInt("id_usuario");
            }
            esta = Objects.equals(cod2, codigo);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return esta;
    }

    public void Llenar(Integer codigo, JTextField nom, JTextField ape, JTextField con, JTextField correo, JTextField año, JComboBox dia, JComboBox mes) {

        String sql = "SELECT *,Year(fecha_nac), Day(fecha_nac), Month(fecha_nac) FROM user WHERE id_usuario = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                nom.setText(rs.getString("nombre"));
                ape.setText(rs.getString("apellido"));
                con.setText(rs.getString("contraseña"));
                correo.setText(rs.getString("correo"));
                año.setText(rs.getString("Year(fecha_nac)"));
                dia.setSelectedIndex(rs.getInt("Day(fecha_nac)"));
                mes.setSelectedIndex(rs.getInt("Month(fecha_nac)"));

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public int Ingresar(Integer codigo, String clave) {
        int usu = 0;
        Integer cod2 = 0;
        String sql = "SELECT * FROM user WHERE id_usuario = '" + codigo + "' AND contraseña = '" + clave + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                cod2 = rs.getInt("id_usuario");
            }

            if (cod2 == 123) {
                if ("admi".equals(JOptionPane.showInputDialog("Digite palabra(admi)"))) {
                    usu = 1;
                } else {
                    JOptionPane.showMessageDialog(null, "Palabra incorrecta");
                    usu = 0;
                }

            } else if (cod2 != 123 && cod2 != 0) {
                usu = 2;
            }
            idUsu = cod2;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return usu;
    }

    public void actualizar(Usuario u, Integer codigo) {
        String sql = "UPDATE user SET nombre = '" + u.getNombre()
                + "', apellido = '" + u.getApellido() + "', correo = '" + u.getEmail()
                + "', fecha_nac = '" + u.getFechaNac() + "', contraseña = '"
                + u.getContraseña() + "' WHERE id_usuario = '" + codigo + "'";

        try (Connection conexion2 = Conexion.getConnection();
                PreparedStatement pst = conexion2.prepareStatement(sql)) {
            JOptionPane.showMessageDialog(null, "Se actualizó Usuario");
            pst.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void eliminar(Integer codigo) {
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement()) {
            String sql = "DELETE FROM user WHERE id_usuario = '" + codigo + "'";
            declaracion.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se eliminó Usuario");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
