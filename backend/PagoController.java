package com.trivani.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoController {

    // =========================================================
    // GUARDAR
    // =========================================================

    public boolean guardarPago(Pago pago) {

        String sql =
                "INSERT INTO pagos (" +
                "comprobante_codigo," +
                "inquilino_id," +
                "monto," +
                "monto_letras," +
                "periodo," +
                "fecha_operacion," +
                "concepto," +
                "ubicacion," +
                "cuenta_destino," +
                "arrendador_nombre," +
                "arrendador_dni," +
                "estado_pago," +
                "link_pago," +
                "preferencia_id," +
                "pdf_url" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, pago.getComprobanteCodigo());
            stmt.setObject(2, pago.getInquilinoId());
            stmt.setBigDecimal(3, pago.getMonto());
            stmt.setString(4, pago.getMontoLetras());
            stmt.setString(5, pago.getPeriodo());

            if (pago.getFechaOperacion() != null) {
                stmt.setDate(
                    6,
                    java.sql.Date.valueOf(
                        pago.getFechaOperacion()
                    )
                );
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }

            stmt.setString(7, pago.getConcepto());
            stmt.setString(8, pago.getUbicacion());
            stmt.setString(9, pago.getCuentaDestino());
            stmt.setString(10, pago.getArrendadorNombre());
            stmt.setString(11, pago.getArrendadorDni());

            stmt.setString(
                12,
                pago.getEstadoPago() == null
                    ? "PENDIENTE"
                    : pago.getEstadoPago()
            );

            stmt.setString(13, pago.getLinkPago());
            stmt.setString(14, pago.getPreferenciaId());
            stmt.setString(15, pago.getPdfUrl());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // LISTAR TODOS
    // =========================================================

    public List<Pago> obtenerTodosLosPagos() {

        List<Pago> lista = new ArrayList<>();

        String sql =
            "SELECT * FROM pagos " +
            "ORDER BY fecha_registro DESC";

        try (
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(mapearPago(rs));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    // =========================================================
    // BUSCAR POR ID
    // =========================================================

    public Pago obtenerPagoPorId(Integer id) {

        String sql =
            "SELECT * FROM pagos WHERE id = ?";

        try (
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearPago(rs);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // HISTORIAL DEL INQUILINO
    // =========================================================

    public List<Pago> obtenerPagosPorInquilino(
            Integer inquilinoId
    ) {

        List<Pago> lista = new ArrayList<>();

        String sql =
            "SELECT * FROM pagos " +
            "WHERE inquilino_id = ? " +
            "ORDER BY fecha_registro DESC";

        try (
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, inquilinoId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearPago(rs));
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    // =========================================================
    // CAMBIAR ESTADO
    // =========================================================

    public boolean actualizarEstado(
            Integer id,
            String estado
    ) {

        String sql =
            "UPDATE pagos " +
            "SET estado_pago = ? " +
            "WHERE id = ?";

        try (
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, estado);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // GUARDAR ENLACE DE PAGO
    // =========================================================

    public boolean guardarEnlacePago(
            Integer id,
            String linkPago,
            String preferenciaId
    ) {

        String sql =
            "UPDATE pagos " +
            "SET link_pago = ?, preferencia_id = ? " +
            "WHERE id = ?";

        try (
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, linkPago);
            stmt.setString(2, preferenciaId);
            stmt.setInt(3, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // GUARDAR PDF
    // =========================================================

    public boolean guardarPdfUrl(
            Integer id,
            String pdfUrl
    ) {

        String sql =
            "UPDATE pagos " +
            "SET pdf_url = ? " +
            "WHERE id = ?";

        try (
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, pdfUrl);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // ELIMINAR
    // =========================================================

    public boolean eliminarPago(Integer id) {

        String sql =
            "DELETE FROM pagos WHERE id = ?";

        try (
            Connection conn = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // MAPEAR
    // =========================================================

    private Pago mapearPago(ResultSet rs)
            throws SQLException {

        Pago pago = new Pago();

        pago.setId(rs.getInt("id"));

        pago.setComprobanteCodigo(
            rs.getString("comprobante_codigo")
        );

        int inquilino =
            rs.getInt("inquilino_id");

        if (rs.wasNull()) {
            pago.setInquilinoId(null);
        } else {
            pago.setInquilinoId(inquilino);
        }

        pago.setMonto(
            rs.getBigDecimal("monto")
        );

        pago.setMontoLetras(
            rs.getString("monto_letras")
        );

        pago.setPeriodo(
            rs.getString("periodo")
        );

        if (rs.getDate("fecha_operacion") != null) {

            pago.setFechaOperacion(
                rs.getDate("fecha_operacion")
                    .toLocalDate()
            );
        }

        pago.setConcepto(
            rs.getString("concepto")
        );

        pago.setUbicacion(
            rs.getString("ubicacion")
        );

        pago.setCuentaDestino(
            rs.getString("cuenta_destino")
        );

        pago.setArrendadorNombre(
            rs.getString("arrendador_nombre")
        );

        pago.setArrendadorDni(
            rs.getString("arrendador_dni")
        );

        pago.setEstadoPago(
            rs.getString("estado_pago")
        );

        pago.setLinkPago(
            rs.getString("link_pago")
        );

        pago.setPreferenciaId(
            rs.getString("preferencia_id")
        );

        pago.setPdfUrl(
            rs.getString("pdf_url")
        );

        return pago;
    }
}
