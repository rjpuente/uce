/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Java
 */
@Entity
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")
    , @NamedQuery(name = "Clientes.findByCedula", query = "SELECT c FROM Clientes c WHERE c.cedula = :cedula")
    , @NamedQuery(name = "Clientes.findByNombreCliente", query = "SELECT c FROM Clientes c WHERE c.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "Clientes.findByDireccionCliente", query = "SELECT c FROM Clientes c WHERE c.direccionCliente = :direccionCliente")
    , @NamedQuery(name = "Clientes.findByCorreoElectronicoCliente", query = "SELECT c FROM Clientes c WHERE c.correoElectronicoCliente = :correoElectronicoCliente")})
public class Clientes implements Serializable {

    @Column(name = "estado_registro")
    private Character estadoRegistro;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "contrasena_cliente")
    private String contrasenaCliente;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cedula")
    private Long cedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "direccion_cliente")
    private String direccionCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo_electronico_cliente")
    private String correoElectronicoCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Ticket> ticketCollection;

    public Clientes() {
    }

    public Clientes(Long cedula) {
        this.cedula = cedula;
    }

    public Clientes(Long cedula, String nombreCliente, String direccionCliente, String correoElectronicoCliente) {
        this.cedula = cedula;
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.correoElectronicoCliente = correoElectronicoCliente;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getCorreoElectronicoCliente() {
        return correoElectronicoCliente;
    }

    public void setCorreoElectronicoCliente(String correoElectronicoCliente) {
        this.correoElectronicoCliente = correoElectronicoCliente;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uce.entidades.Clientes[ cedula=" + cedula + " ]";
    }

    public String getContrasenaCliente() {
        return contrasenaCliente;
    }

    public void setContrasenaCliente(String contrasenaCliente) {
        this.contrasenaCliente = contrasenaCliente;
    }

    public Character getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(Character estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
