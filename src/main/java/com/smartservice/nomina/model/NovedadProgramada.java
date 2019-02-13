package com.smartservice.nomina.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "novedad_programada", schema = "empresa")
public class NovedadProgramada {

    @Id
    @Column(name = "id_novedad_programada")
    @SequenceGenerator(name="empresa.novedad_programada_id_novedad_programada_seq", sequenceName="empresa.novedad_programada_id_novedad_programada_seq",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="empresa.novedad_programada_id_novedad_programada_seq")
    private long idNovedadProgramada;

    @ManyToOne
    @JoinColumn(name = "id_concepto")
    private Concepto concepto;

    @Column(name = "cantidad")
    private long cantidad;

    @Column(name = "valor")
    private long valor;

    @ManyToOne
    @JoinColumn(name = "id_contrato", insertable = false, updatable = false)
    private Contrato contrato;

    @Column(name = "id_contrato")
    private long idContrato;

    @Column(name = "estado_novedad")
    private String estadoNovedad;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_finalizacion")
    private Date fechaFinalizacion;

    @Column(name = "id_empresa")
    private long idEmpresa;


    public long getIdNovedadProgramada() {
        return idNovedadProgramada;
    }

    public void setIdNovedadProgramada(long idNovedadProgramada) {
        this.idNovedadProgramada = idNovedadProgramada;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }

    public long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(long idContrato) {
        this.idContrato = idContrato;
    }

    public String getEstadoNovedad() {
        return estadoNovedad;
    }

    public void setEstadoNovedad(String estadoNovedad) {
        this.estadoNovedad = estadoNovedad;
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

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    @Override
    public String toString() {
        return "NovedadProgramada{" +
                "idNovedadProgramada=" + idNovedadProgramada +
                ", concepto=" + concepto +
                ", cantidad=" + cantidad +
                ", valor=" + valor +
                ", contrato=" + contrato +
                ", idContrato=" + idContrato +
                ", estadoNovedad='" + estadoNovedad + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFinalizacion=" + fechaFinalizacion +
                ", idEmpresa=" + idEmpresa +
                '}';
    }
}
