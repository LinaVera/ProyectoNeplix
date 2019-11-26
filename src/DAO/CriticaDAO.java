
package DAO;

import VO.Critica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CriticaDAO {
     public void registrarCritica(Critica cri) {

        String sql = "INSERT INTO critica(comentario,fecha,id_usuario,ids)"
                + "VALUES (?,?,?,?)";
        try (Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cri.getCritica());
            ps.setString(2, cri.getFechaC());
            ps.setInt(3, cri.getCod_critica());
            ps.setInt(4, cri.getTipo_critica());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Critica registrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
