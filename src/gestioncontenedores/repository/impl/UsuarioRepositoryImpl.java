/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncontenedores.repository.impl;

import gestioncontenedores.config.DatabaseConnection;
import gestioncontenedores.model.Usuario;
import gestioncontenedores.repository.IUsuarioRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author rc202
 */
public class UsuarioRepositoryImpl implements IUsuarioRepository {
    @Override
    public Optional<Usuario> buscarPorCredenciales(String username, String password) {
        // En tu C# original, la contraseña de usuarios (vecinos) no estaba encriptada
        String sql = "SELECT * FROM Usuarios WHERE Username = ? AND Password = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("Id"));
                    u.setUsername(rs.getString("Username"));
                    u.setPassword(rs.getString("Password"));
                    u.setNivelPermiso(rs.getInt("NivelPermiso"));
                    return Optional.of(u);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL en Usuario: " + e.getMessage());
        }
        return Optional.empty();
    }
}
