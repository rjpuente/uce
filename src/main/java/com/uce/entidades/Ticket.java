/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Java
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t")
    , @NamedQuery(name = "Ticket.findByIdTicket", query = "SELECT t FROM Ticket t WHERE t.idTicket = :idTicket")
    , @NamedQuery(name = "Ticket.findByFechaSalida", query = "SELECT t FROM Ticket t WHERE t.fechaSalida = :fechaSalida")
    , @NamedQuery(name = "Ticket.findByFechaEntrega", query = "SELECT t FROM Ticket t WHERE t.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "Ticket.findByPagoAlquiler", query = "SELECT t FROM Ticket t WHERE t.pagoAlquiler = :pagoAlquiler")})
public class Ticket implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_ticket")
    private Character estadoTicket;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Integer idTicket;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "pago_alquiler")
    private BigDecimal pagoAlquiler;
    @JoinColumn(name = "cliente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Clientes cliente;
    @JoinColumn(name = "empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados empleado;
    @JoinColumn(name = "tipo_pago", referencedColumnName = "id_tipo_pago")
    @ManyToOne(optional = false)
    private TiposPago tipoPago;
    @JoinColumn(name = "vehiculo_placa", referencedColumnName = "numero_placa")
    @ManyToOne(optional = false)
    private Vehiculos vehiculoPlaca;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(Integer idTicket, Date fechaSalida, Date horaSalida, Date fechaEntrega, BigDecimal pagoAlquiler) {
        this.idTicket = idTicket;
        this.fechaSalida = fechaSalida;
        this.fechaEntrega = fechaEntrega;
        this.pagoAlquiler = pagoAlquiler;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public BigDecimal getPagoAlquiler() {
        return pagoAlquiler;
    }

    public void setPagoAlquiler(BigDecimal pagoAlquiler) {
        this.pagoAlquiler = pagoAlquiler;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public TiposPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TiposPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Vehiculos getVehiculoPlaca() {
        return vehiculoPlaca;
    }

    public void setVehiculoPlaca(Vehiculos vehiculoPlaca) {
        this.vehiculoPlaca = vehiculoPlaca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uce.entidades.Ticket[ idTicket=" + idTicket + " ]";
    }

    public Character getEstadoTicket() {
        return estadoTicket;
    }

    public void setEstadoTicket(Character estadoTicket) {
        this.estadoTicket = estadoTicket;
    }
    
}
