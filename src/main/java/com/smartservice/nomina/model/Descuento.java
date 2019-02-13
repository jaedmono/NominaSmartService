package com.smartservice.nomina.model;


import org.springframework.boot.actuate.endpoint.jmx.DataEndpointMBean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "descuento", schema = "empresa")
public class Descuento {

    @Id
    @Column(name = "id_descuento")
    @SequenceGenerator(name="empresa.descuento_id_descuento_seq", sequenceName="empresa.descuento_id_descuento_seq",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="empresa.descuento_id_descuento_seq")
    private long idDescuento;

    @ManyToOne
    @JoinColumn(name = "id_concepto")
    private Concepto concepto;

    @ManyToOne
    @JoinColumn(name = "id_contrato", insertable = false, updatable = false)
    private Contrato contrato;

    @Column(name = "id_contrato")
    private long idContrato;

    @Column(name = "total_descuento")
    private long totalDescuento;

    @Column(name = "numero_cuotas")
    private int numeroCuotas;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_finalizacion")
    private Date fechaFinalizacion;

    @Column(name = "estado")
    private  String estado;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "periodo_pago")
    private String periodoPago;

    @Column(name = "id_empresa")
    private long idEmpresa;


    public long getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(long idDescuento) {
        this.idDescuento = idDescuento;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(long idContrato) {
        this.idContrato = idContrato;
    }

    public long getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(long totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPeriodoPago() {
        return periodoPago;
    }

    public void setPeriodoPago(String periodoPago) {
        this.periodoPago = periodoPago;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
