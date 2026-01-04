/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncontenedores.repository;

import gestioncontenedores.model.Trabajador;
import java.util.Optional;
/**
 *
 * @author rc202
 */
public interface ITrabajadorRepository {
    Optional<Trabajador> buscarPorLogin(String usuario, String passwordHash);
}
