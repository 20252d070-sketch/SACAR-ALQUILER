package com.trivani.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
