package DAO;

import VO.Subtitulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SubtituloDAO {

    public void registrarSubtitulo(Subtitulo sub) {

        String sql = "INSERT INTO subtitulo(id_subtitulo,idioma, autor) VALUES(?,?,?)";
        try (Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sub.getCod_subtitulo());
            ps.setString(2, sub.getIdioma());
            ps.setString(3, sub.getAutor());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Subtítulo registrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public boolean Buscar(String codigo) {
        boolean esta = false;
        String cod2 = "";
        String sql = "SELECT * FROM subtitulo WHERE id_subtitulo = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                cod2 = rs.getString("id_subtitulo");
            }
            esta = Objects.equals(cod2, codigo);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return esta;
    }

    public void Llenar(String codigo, JTextField idi, JTextField aut) {

        String sql = "SELECT * FROM subtitulo WHERE id_subtitulo = '" + codigo + "'";
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement();
                ResultSet rs = declaracion.executeQuery(sql)) {
            while (rs.next()) {
                idi.setText(rs.getString("idioma"));
                aut.setText(rs.getString("autor"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void actualizar(Subtitulo sub, String codigo) {
        String sql = "UPDATE subtitulo SET idioma = '" + sub.getIdioma()
                + "', autor = '" + sub.getAutor() + "'WHERE id_subtitulo = '" + codigo + "'";
        try (Connection conexion2 = Conexion.getConnection();
                PreparedStatement pst = conexion2.prepareStatement(sql)) {
            JOptionPane.showMessageDialog(null, "Se actualizó Subtítulo");
            pst.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void eliminar(String codigo) {
        try (Connection conexion3 = Conexion.getConnection();
                Statement declaracion = conexion3.createStatement()) {
            String sql = "DELETE FROM subtitulo WHERE id_subtitulo = '" + codigo + "'";
            declaracion.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se eliminó Subtítulo");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
