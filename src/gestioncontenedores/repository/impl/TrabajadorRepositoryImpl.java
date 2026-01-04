/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncontenedores.repository.impl;

import gestioncontenedores.config.DatabaseConnection;
import gestioncontenedores.model.Trabajador;
import gestioncontenedores.repository.ITrabajadorRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
/**
 *
 * @author rc202
 */
public class TrabajadorRepositoryImpl implements ITrabajadorRepository {
    public Optional<Trabajador> buscarPorLogin(String usuario, String passwordHash) {
        // Validación: Usuario, Hash y Estado Activo (1)
        String sql = "SELECT * FROM Trabajadores WHERE UsuarioLogin = ? AND PasswordHash = ? AND Estado = 1";

        try (Connection conn = DatabaseConnection.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, passwordHash);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Trabajador t = new Trabajador();
                    t.setId(rs.getInt("Id"));
                    t.setDni(rs.getString("DNI"));
                    t.setNombres(rs.getString("Nombres"));
                    t.setApellidos(rs.getString("Apellidos"));
                    t.setCargo(rs.getString("Cargo"));
                    t.setUsuarioLogin(rs.getString("UsuarioLogin"));
                    t.setEstado(rs.getBoolean("Estado"));
                    return Optional.of(t);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL en Trabajador: " + e.getMessage());
        }
        return Optional.empty();
    }
}
