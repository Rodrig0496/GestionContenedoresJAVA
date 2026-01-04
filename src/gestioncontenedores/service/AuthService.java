/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncontenedores.service;

import gestioncontenedores.model.Trabajador;
import gestioncontenedores.model.Usuario;
import gestioncontenedores.repository.ITrabajadorRepository;
import gestioncontenedores.repository.IUsuarioRepository;
import gestioncontenedores.repository.impl.TrabajadorRepositoryImpl;
import gestioncontenedores.repository.impl.UsuarioRepositoryImpl;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;
/**
 *
 * @author rc202
 */
public class AuthService {
    private final IUsuarioRepository usuarioRepo;
    private final ITrabajadorRepository trabajadorRepo;

    public AuthService() {
        // Aquí hacemos la "inyección" manual de las implementaciones
        this.usuarioRepo = new UsuarioRepositoryImpl();
        this.trabajadorRepo = new TrabajadorRepositoryImpl();
    }

    public Usuario loginUsuario(String user, String pass) throws Exception {
        return usuarioRepo.buscarPorCredenciales(user, pass)
                .orElseThrow(() -> new Exception("Usuario o contraseña incorrectos."));
    }

    public Trabajador loginTrabajador(String user, String pass) throws Exception {
        String hash = encriptarSHA256(pass);
        return trabajadorRepo.buscarPorLogin(user, hash)
                .orElseThrow(() -> new Exception("Credenciales de TRABAJADOR incorrectas o cuenta inactiva."));
    }

    // Tu lógica de encriptación portada de C# a Java
    private String encriptarSHA256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar password", e);
        }
    }
}
