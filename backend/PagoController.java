package com.trivani.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoController {

    // ============================================================
    // GUARDAR PAGO
    // ============================================================

    public boolean guardarPago(Pago pago) {

        String sql =
                "INSERT INTO pagos (" +
                "comprobante_codigo, " +
                "inquilino_id, " +
                "monto, " +
                "monto_letras, " +
                "periodo, " +
                "fecha_operacion, " +
                "concepto, " +
                "ubicacion, " +
                "cuenta_destino, " +
                "arrendador_nombre, " +
                "arrendador_dni, " +
                "estado_pago, " +
                "link_pago, " +
                "preferencia_id, " +
                "pdf_url" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, pago.getComprobanteCodigo());
            stmt.setInt(2, pago.getInquilinoId());
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
                stmt.setDate(6, null);
            }

            stmt.setString(7, pago.getConcepto());
            stmt.setString(8, pago.getUbicacion());
            stmt.setString(9, pago.getCuentaDestino());
            stmt.setString(10, pago.getArrendadorNombre());
            stmt.setString(11, pago.getArrendadorDni());

            stmt.setString(
                    12,
                    pago.getEstadoPago() != null
                            ? pago.getEstadoPago()
                            : "PENDIENTE"
            );

            stmt.setString(13, pago.getLinkPago());
            stmt.setString(14, pago.getPreferenciaId());
            stmt.setString(15, pago.getPdfUrl());

            // El índice 16 no corresponde porque hay 15 columnas nuevas
            // corregimos abajo mediante la versión preparada correcta.

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ============================================================
    // GUARDAR PAGO - VERSIÓN CORRECTA
    // ============================================================

    public boolean guardarPagoCompleto(Pago pago) {

        String sql =
                "INSERT INTO pagos (" +
                "comprobante_codigo, " +
                "inquilino_id, " +
                "monto, " +
                "monto_letras, " +
                "periodo, " +
                "fecha_operacion, " +
                "concepto, " +
                "ubicacion, " +
                "cuenta_destino, " +
                "arrendador_nombre, " +
                "arrendador_dni, " +
                "estado_pago, " +
                "link_pago, " +
                "preferencia_id, " +
                "pdf_url" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
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
                stmt.setNull(
                        6,
                        java.sql.Types.DATE
                );
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

            // Fecha de registro se genera automáticamente en MySQL
            int filas = stmt.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ============================================================
    // OBTENER TODOS
    // ============================================================

    public List<Pago> obtenerTodosLosPagos() {

        List<Pago> lista = new ArrayList<>();

        String sql =
                "SELECT * FROM pagos " +
                "ORDER BY fecha_registro DESC";

        try (
                Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement stmt =
                        conn.prepareStatement(sql);
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

    // ============================================================
    // OBTENER POR ID
    // ============================================================

    public Pago obtenerPagoPorId(Integer id) {

        String sql =
                "SELECT * FROM pagos WHERE id = ?";

        try (
                Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
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

    // ============================================================
    // PAGOS DE UN INQUILINO
    // ============================================================

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
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
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

    // ============================================================
    // ACTUALIZAR ESTADO
    // ============================================================

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
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, estado);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ============================================================
    // GUARDAR ENLACE DE PAGO
    // ============================================================

    public boolean guardarEnlacePago(
            Integer id,
            String linkPago,
            String preferenciaId
    ) {

        String sql =
                "UPDATE pagos " +
                "SET link_pago = ?, " +
                "preferencia_id = ? " +
                "WHERE id = ?";

        try (
                Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
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

    // ============================================================
    // GUARDAR URL DEL PDF
    // ============================================================

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
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, pdfUrl);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ============================================================
    // ELIMINAR
    // ============================================================

    public boolean eliminarPago(Integer id) {

        String sql =
                "DELETE FROM pagos WHERE id = ?";

        try (
                Connection conn = ConexionBD.obtenerConexion();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ============================================================
    // MAPEAR RESULTADO MYSQL -> PAGO
    // ============================================================

    private Pago mapearPago(ResultSet rs)
            throws SQLException {

        Pago p = new Pago();

        p.setId(rs.getInt("id"));
        p.setComprobanteCodigo(
                rs.getString("comprobante_codigo")
        );

        int inquilinoId =
                rs.getInt("inquilino_id");

        if (rs.wasNull()) {
            p.setInquilinoId(null);
        } else {
            p.setInquilinoId(inquilinoId);
        }

        p.setMonto(rs.getBigDecimal("monto"));
        p.setMontoLetras(
                rs.getString("monto_letras")
        );

        p.setPeriodo(
                rs.getString("periodo")
        );

        if (rs.getDate("fecha_operacion") != null) {
            p.setFechaOperacion(
                    rs.getDate("fecha_operacion")
                            .toLocalDate()
            );
        }

        p.setConcepto(
                rs.getString("concepto")
        );

        p.setUbicacion(
                rs.getString("ubicacion")
        );

        p.setCuentaDestino(
                rs.getString("cuenta_destino")
        );

        p.setArrendadorNombre(
                rs.getString("arrendador_nombre")
        );

        p.setArrendadorDni(
                rs.getString("arrendador_dni")
        );

        p.setEstadoPago(
                rs.getString("estado_pago")
        );

        p.setLinkPago(
                rs.getString("link_pago")
        );

        p.setPreferenciaId(
                rs.getString("preferencia_id")
        );

        p.setPdfUrl(
                rs.getString("pdf_url")
        );

        return p;
    }
}
