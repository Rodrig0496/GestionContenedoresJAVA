/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncontenedores.repository;

import gestioncontenedores.model.Usuario;
import java.util.Optional;

/**
 *
 * @author rc202
 */
public interface IUsuarioRepository {
    Optional<Usuario> buscarPorCredenciales(String username, String password);
}
