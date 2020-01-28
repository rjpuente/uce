/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Java
 */
@Entity
@Table(name = "vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculos.findAll", query = "SELECT v FROM Vehiculos v")
    , @NamedQuery(name = "Vehiculos.findByNumeroPlaca", query = "SELECT v FROM Vehiculos v WHERE v.numeroPlaca = :numeroPlaca")
    , @NamedQuery(name = "Vehiculos.findByPrecioActual", query = "SELECT v FROM Vehiculos v WHERE v.precioActual = :precioActual")})
public class Vehiculos implements Serializable {

    @Size(max = 50)
    @Column(name = "imagen_vehiculo2")
    private String imagenVehiculo2;
    @Size(max = 50)
    @Column(name = "imagen_vehiculo3")
    private String imagenVehiculo3;

    @Size(max = 50)
    @Column(name = "imagen_vehiculo")
    private String imagenVehiculo;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Basic(optional = false)
    @NotNull
    @Column(name = "a\u00f1o_vehiculo")
    @Temporal(TemporalType.DATE)
    private Date añoVehiculo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_vehiculo")
    private Character estadoVehiculo;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "numero_placa")
    private String numeroPlaca;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_actual")
    private BigDecimal precioActual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculoPlaca")
    private Collection<Ticket> ticketCollection;
    @JoinColumn(name = "color", referencedColumnName = "id_color")
    @ManyToOne(optional = false)
    private Color color;
    @JoinColumn(name = "marcas", referencedColumnName = "id_marcas")
    @ManyToOne(optional = false)
    private Marcas marcas;
    @JoinColumn(name = "tipos_combustible", referencedColumnName = "id_tipo_combustible")
    @ManyToOne(optional = false)
    private TipoCombustible tiposCombustible;
    @JoinColumn(name = "tipos_vehiculos", referencedColumnName = "id_tipos_vehiculos")
    @ManyToOne(optional = false)
    private TiposVehiculos tiposVehiculos;

    public Vehiculos() {
    }

    public Vehiculos(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public Vehiculos(String numeroPlaca, BigDecimal precioActual, Integer cantidad) {
        this.numeroPlaca = numeroPlaca;
        this.precioActual = precioActual;
        this.cantidad = 1;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public BigDecimal getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(BigDecimal precioActual) {
        this.precioActual = precioActual;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Marcas getMarcas() {
        return marcas;
    }

    public void setMarcas(Marcas marcas) {
        this.marcas = marcas;
    }

    public TipoCombustible getTiposCombustible() {
        return tiposCombustible;
    }

    public void setTiposCombustible(TipoCombustible tiposCombustible) {
        this.tiposCombustible = tiposCombustible;
    }

    public TiposVehiculos getTiposVehiculos() {
        return tiposVehiculos;
    }

    public void setTiposVehiculos(TiposVehiculos tiposVehiculos) {
        this.tiposVehiculos = tiposVehiculos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroPlaca != null ? numeroPlaca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculos)) {
            return false;
        }
        Vehiculos other = (Vehiculos) object;
        if ((this.numeroPlaca == null && other.numeroPlaca != null) || (this.numeroPlaca != null && !this.numeroPlaca.equals(other.numeroPlaca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uce.entidades.Vehiculos[ numeroPlaca=" + numeroPlaca + " ]";
    }

    public Character getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(Character estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

    public Date getAñoVehiculo() {
        return añoVehiculo;
    }

    public void setAñoVehiculo(Date añoVehiculo) {
        this.añoVehiculo = añoVehiculo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagenVehiculo() {
        return imagenVehiculo;
    }

    public void setImagenVehiculo(String imagenVehiculo) {
        this.imagenVehiculo = imagenVehiculo;
    }

    public String getImagenVehiculo2() {
        return imagenVehiculo2;
    }

    public void setImagenVehiculo2(String imagenVehiculo2) {
        this.imagenVehiculo2 = imagenVehiculo2;
    }

    public String getImagenVehiculo3() {
        return imagenVehiculo3;
    }

    public void setImagenVehiculo3(String imagenVehiculo3) {
        this.imagenVehiculo3 = imagenVehiculo3;
    }

}
