package com.trivani.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoController {

    public boolean guardarPago(Pago pago) {
        String sql = "INSERT INTO pagos (comprobante_codigo, inquilino_id, monto, monto_letras, periodo, "
                   + "fecha_operacion, concepto, ubicacion, cuenta_destino, arrendador_nombre, arrendador_dni) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pago.getComprobanteCodigo());
            stmt.setInt(2, pago.getInquilinoId());
            stmt.setBigDecimal(3, pago.getMonto());
            stmt.setString(4, pago.getMontoLetras());
            stmt.setString(5, pago.getPeriodo());
            stmt.setDate(6, java.sql.Date.valueOf(pago.getFechaOperacion()));
            stmt.setString(7, pago.getConcepto());
            stmt.setString(8, pago.getUbicacion());
            stmt.setString(9, pago.getCuentaDestino());
            stmt.setString(10, pago.getArrendadorNombre());
            stmt.setString(11, pago.getArrendadorDni());

            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pago> obtenerTodosLosPagos() {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagos ORDER BY fecha_registro DESC";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pago p = new Pago();
                p.setId(rs.getInt("id"));
                p.setComprobanteCodigo(rs.getString("comprobante_codigo"));
                p.setInquilinoId(rs.getInt("inquilino_id"));
                p.setMonto(rs.getBigDecimal("monto"));
                p.setMontoLetras(rs.getString("monto_letras"));
                p.setPeriodo(rs.getString("periodo"));
                p.setFechaOperacion(rs.getDate("fecha_operacion").toLocalDate());
                p.setConcepto(rs.getString("concepto"));
                p.setUbicacion(rs.getString("ubicacion"));
                p.setCuentaDestino(rs.getString("cuenta_destino"));
                p.setArrendadorNombre(rs.getString("arrendador_nombre"));
                p.setArrendadorDni(rs.getString("arrendador_dni"));
                
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
