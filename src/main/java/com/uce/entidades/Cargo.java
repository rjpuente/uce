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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
    , @NamedQuery(name = "Cargo.findByCodigoCargo", query = "SELECT c FROM Cargo c WHERE c.codigoCargo = :codigoCargo")
    , @NamedQuery(name = "Cargo.findByDescripcion", query = "SELECT c FROM Cargo c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Cargo.findByCargoAdministradorSistema", query = "SELECT c FROM Cargo c WHERE c.cargoAdministradorSistema = :cargoAdministradorSistema")})
public class Cargo implements Serializable {

    @Column(name = "cargo_administrador_sistema")
    private Integer cargoAdministradorSistema;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cargo")
    private Integer codigoCargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadocargo")
    private Collection<Empleados> empleadosCollection;

    public Cargo() {
    }

    public Cargo(Integer codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public Cargo(Integer codigoCargo, String descripcion) {
        this.codigoCargo = codigoCargo;
        this.descripcion = descripcion;
    }

    public Integer getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Integer codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Empleados> getEmpleadosCollection() {
        return empleadosCollection;
    }

    public void setEmpleadosCollection(Collection<Empleados> empleadosCollection) {
        this.empleadosCollection = empleadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCargo != null ? codigoCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.codigoCargo == null && other.codigoCargo != null) || (this.codigoCargo != null && !this.codigoCargo.equals(other.codigoCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uce.entidades.Cargo[ codigoCargo=" + codigoCargo + " ]";
    }

    public Integer getCargoAdministradorSistema() {
        return cargoAdministradorSistema;
    }

    public void setCargoAdministradorSistema(Integer cargoAdministradorSistema) {
        this.cargoAdministradorSistema = cargoAdministradorSistema;
    }

}
