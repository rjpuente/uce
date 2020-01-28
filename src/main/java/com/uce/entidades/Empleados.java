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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e")
    , @NamedQuery(name = "Empleados.findByIdEmpleado", query = "SELECT e FROM Empleados e WHERE e.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "Empleados.findByNombreEmpleado", query = "SELECT e FROM Empleados e WHERE e.nombreEmpleado = :nombreEmpleado")
    , @NamedQuery(name = "Empleados.findByDireccionEmpleado", query = "SELECT e FROM Empleados e WHERE e.direccionEmpleado = :direccionEmpleado")
    , @NamedQuery(name = "Empleados.findByCorreoElectronicoEmpleado", query = "SELECT e FROM Empleados e WHERE e.correoElectronicoEmpleado = :correoElectronicoEmpleado")})
public class Empleados implements Serializable {

    @Column(name = "estado_empelados")
    private Character estadoEmpelados;

    @JoinColumn(name = "empleadocargo", referencedColumnName = "codigo_cargo")
    @ManyToOne(optional = false)
    private Cargo empleadocargo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "contrasena_empleado")
    private String contrasenaEmpleado;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_empleado")
    private String nombreEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "direccion_empleado")
    private String direccionEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo_electronico_empleado")
    private String correoElectronicoEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private Collection<Ticket> ticketCollection;

    public Empleados() {
    }

    public Empleados(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleados(Long idEmpleado, String nombreEmpleado, String direccionEmpleado, String correoElectronicoEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.direccionEmpleado = direccionEmpleado;
        this.correoElectronicoEmpleado = correoElectronicoEmpleado;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }

    public String getCorreoElectronicoEmpleado() {
        return correoElectronicoEmpleado;
    }

    public void setCorreoElectronicoEmpleado(String correoElectronicoEmpleado) {
        this.correoElectronicoEmpleado = correoElectronicoEmpleado;
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
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uce.entidades.Empleados[ idEmpleado=" + idEmpleado + " ]";
    }

    public String getContrasenaEmpleado() {
        return contrasenaEmpleado;
    }

    public void setContrasenaEmpleado(String contrasenaEmpleado) {
        this.contrasenaEmpleado = contrasenaEmpleado;
    }

    public Cargo getEmpleadocargo() {
        return empleadocargo;
    }

    public void setEmpleadocargo(Cargo empleadocargo) {
        this.empleadocargo = empleadocargo;
    }

    public Character getEstadoEmpelados() {
        return estadoEmpelados;
    }

    public void setEstadoEmpelados(Character estadoEmpelados) {
        this.estadoEmpelados = estadoEmpelados;
    }

}
