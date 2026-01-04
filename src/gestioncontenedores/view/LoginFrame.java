/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioncontenedores.view;

import gestioncontenedores.model.Trabajador;
import gestioncontenedores.model.Usuario;
import gestioncontenedores.service.AuthService;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
/**
 *
 * @author rc202
 */
public class LoginFrame extends JFrame{
    private final AuthService authService;
    private int xMouse, yMouse;

    // Componentes
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JCheckBox chkModoTrabajador;

    public LoginFrame() {
        this.authService = new AuthService();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setUndecorated(true); // Sin bordes (estilo C#)
        setSize(350, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 350, 450);
        panel.setLayout(null);
        add(panel);

        // Barra superior para mover ventana
        JPanel header = new JPanel();
        header.setBackground(new Color(240, 240, 240));
        header.setBounds(0, 0, 350, 30);
        header.setLayout(null);
        // Eventos del mouse para arrastrar
        header.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
        header.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - xMouse, e.getYOnScreen() - yMouse);
            }
        });
        panel.add(header);

        JButton btnCerrar = new JButton("X");
        btnCerrar.setBounds(310, 0, 40, 30);
        btnCerrar.setBackground(new Color(231, 76, 60));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> System.exit(0));
        header.add(btnCerrar);

        // Contenido
        JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 50, 350, 30);
        panel.add(lblTitulo);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setBounds(40, 100, 100, 20);
        panel.add(lblUser);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(40, 120, 270, 30);
        panel.add(txtUsuario);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(40, 160, 100, 20);
        panel.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(40, 180, 270, 30);
        panel.add(txtPassword);

        chkModoTrabajador = new JCheckBox("Soy Trabajador");
        chkModoTrabajador.setBackground(Color.WHITE);
        chkModoTrabajador.setBounds(40, 220, 150, 20);
        panel.add(chkModoTrabajador);

        JButton btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBounds(40, 270, 270, 40);
        btnIngresar.setBackground(new Color(0, 120, 215));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.addActionListener(e -> manejarLogin());
        panel.add(btnIngresar);
    }

    private void manejarLogin() {
        String user = txtUsuario.getText();
        String pass = new String(txtPassword.getPassword());

        try {
            if (chkModoTrabajador.isSelected()) {
                // Login Trabajador
                Trabajador t = authService.loginTrabajador(user, pass);
                JOptionPane.showMessageDialog(this, "Bienvenido Trabajador: " + t.getNombres());
                // TODO: Abrir MainForm en modo Trabajador
            } else {
                // Login Vecino/Admin
                Usuario u = authService.loginUsuario(user, pass);
                JOptionPane.showMessageDialog(this, "Bienvenido Usuario. Permiso: " + u.getNivelPermiso());
                // TODO: Abrir MainForm en modo Usuario
            }
            this.dispose(); // Cerrar login
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
