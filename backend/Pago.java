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

    // Constructor vacío
    public Pago() {}

    // Constructor completo
    public Pago(Integer id, String comprobanteCodigo, Integer inquilinoId, BigDecimal monto, 
                String montoLetras, String periodo, LocalDate fechaOperacion, String concepto, 
                String ubicacion, String cuentaDestino, String arrendadorNombre, String arrendadorDni) {
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
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getComprobanteCodigo() { return comprobanteCodigo; }
    public void setComprobanteCodigo(String comprobanteCodigo) { this.comprobanteCodigo = comprobanteCodigo; }

    public Integer getInquilinoId() { return inquilinoId; }
    public void setInquilinoId(Integer inquilinoId) { this.inquilinoId = inquilinoId; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getMontoLetras() { return montoLetras; }
    public void setMontoLetras(String montoLetras) { this.montoLetras = montoLetras; }

    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }

    public LocalDate getFechaOperacion() { return fechaOperacion; }
    public void setFechaOperacion(LocalDate fechaOperacion) { this.fechaOperacion = fechaOperacion; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getCuentaDestino() { return cuentaDestino; }
    public void setCuentaDestino(String cuentaDestino) { this.cuentaDestino = cuentaDestino; }

    public String getArrendadorNombre() { return arrendadorNombre; }
    public void setArrendadorNombre(String arrendadorNombre) { this.arrendadorNombre = arrendadorNombre; }

    public String getArrendadorDni() { return arrendadorDni; }
    public void setArrendadorDni(String arrendadorDni) { this.arrendadorDni = arrendadorDni; }
}
