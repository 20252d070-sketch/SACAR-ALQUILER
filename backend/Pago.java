package com.trivani.backend;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pago {

    private Integer id;
    private String comprobanteCodigo;
    private Integer inquilinoId;
    private BigDecimal monto;
    private String montoLetras;
    private String periodo;
    private LocalDate fechaOperacion;
    private String concepto;
    private String ubicacion;
    private String cuentaDestino;
    private String arrendadorNombre;
    private String arrendadorDni;

    // Nuevos campos
    private String estadoPago;
    private String linkPago;
    private String preferenciaId;
    private String pdfUrl;

    public Pago() {
    }

    public Pago(
            Integer id,
            String comprobanteCodigo,
            Integer inquilinoId,
            BigDecimal monto,
            String montoLetras,
            String periodo,
            LocalDate fechaOperacion,
            String concepto,
            String ubicacion,
            String cuentaDestino,
            String arrendadorNombre,
            String arrendadorDni,
            String estadoPago,
            String linkPago,
            String preferenciaId,
            String pdfUrl
    ) {
        this.id = id;
        this.comprobanteCodigo = comprobanteCodigo;
        this.inquilinoId = inquilinoId;
        this.monto = monto;
        this.montoLetras = montoLetras;
        this.periodo = periodo;
        this.fechaOperacion = fechaOperacion;
        this.concepto = concepto;
        this.ubicacion = ubicacion;
        this.cuentaDestino = cuentaDestino;
        this.arrendadorNombre = arrendadorNombre;
        this.arrendadorDni = arrendadorDni;
        this.estadoPago = estadoPago;
        this.linkPago = linkPago;
        this.preferenciaId = preferenciaId;
        this.pdfUrl = pdfUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComprobanteCodigo() {
        return comprobanteCodigo;
    }

    public void setComprobanteCodigo(String comprobanteCodigo) {
        this.comprobanteCodigo = comprobanteCodigo;
    }

    public Integer getInquilinoId() {
        return inquilinoId;
    }

    public void setInquilinoId(Integer inquilinoId) {
        this.inquilinoId = inquilinoId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMontoLetras() {
        return montoLetras;
    }

    public void setMontoLetras(String montoLetras) {
        this.montoLetras = montoLetras;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDate fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public String getArrendadorNombre() {
        return arrendadorNombre;
    }

    public void setArrendadorNombre(String arrendadorNombre) {
        this.arrendadorNombre = arrendadorNombre;
    }

    public String getArrendadorDni() {
        return arrendadorDni;
    }

    public void setArrendadorDni(String arrendadorDni) {
        this.arrendadorDni = arrendadorDni;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getLinkPago() {
        return linkPago;
    }

    public void setLinkPago(String linkPago) {
        this.linkPago = linkPago;
    }

    public String getPreferenciaId() {
        return preferenciaId;
    }

    public void setPreferenciaId(String preferenciaId) {
        this.preferenciaId = preferenciaId;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
