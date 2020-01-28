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
@Table(name = "tipos_vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposVehiculos.findAll", query = "SELECT t FROM TiposVehiculos t")
    , @NamedQuery(name = "TiposVehiculos.findByIdTiposVehiculos", query = "SELECT t FROM TiposVehiculos t WHERE t.idTiposVehiculos = :idTiposVehiculos")
    , @NamedQuery(name = "TiposVehiculos.findByDescripcion", query = "SELECT t FROM TiposVehiculos t WHERE t.descripcion = :descripcion")})
public class TiposVehiculos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipos_vehiculos")
    private Integer idTiposVehiculos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiposVehiculos")
    private Collection<Vehiculos> vehiculosCollection;

    public TiposVehiculos() {
    }

    public TiposVehiculos(Integer idTiposVehiculos) {
        this.idTiposVehiculos = idTiposVehiculos;
    }

    public TiposVehiculos(Integer idTiposVehiculos, String descripcion) {
        this.idTiposVehiculos = idTiposVehiculos;
        this.descripcion = descripcion;
    }

    public Integer getIdTiposVehiculos() {
        return idTiposVehiculos;
    }

    public void setIdTiposVehiculos(Integer idTiposVehiculos) {
        this.idTiposVehiculos = idTiposVehiculos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Vehiculos> getVehiculosCollection() {
        return vehiculosCollection;
    }

    public void setVehiculosCollection(Collection<Vehiculos> vehiculosCollection) {
        this.vehiculosCollection = vehiculosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiposVehiculos != null ? idTiposVehiculos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposVehiculos)) {
            return false;
        }
        TiposVehiculos other = (TiposVehiculos) object;
        if ((this.idTiposVehiculos == null && other.idTiposVehiculos != null) || (this.idTiposVehiculos != null && !this.idTiposVehiculos.equals(other.idTiposVehiculos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uce.entidades.TiposVehiculos[ idTiposVehiculos=" + idTiposVehiculos + " ]";
    }
    
}
