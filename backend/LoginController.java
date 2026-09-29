package com.trivani.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    public class UsuarioSesion {
        public boolean autenticado;
        public String usuario;
        public String rol;
        public Integer inquilinoId;

        public UsuarioSesion(boolean autenticado, String usuario, String rol, Integer inquilinoId) {
            this.autenticado = autenticado;
            this.usuario = usuario;
            this.rol = rol;
            this.inquilinoId = inquilinoId;
        }
    }

    public UsuarioSesion autenticar(String usuario, String password) {
        String sql = "SELECT u.usuario, u.rol, u.inquilino_id "
                   + "FROM usuarios u "
                   + "WHERE u.usuario = ? AND u.password_hash = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String user = rs.getString("usuario");
                String rol = rs.getString("rol");
                Integer inquilinoId = rs.getInt("inquilino_id");
                if (rs.wasNull()) inquilinoId = null;

                return new UsuarioSesion(true, user, rol, inquilinoId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new UsuarioSesion(false, null, null, null);
    }
}
