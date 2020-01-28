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
@Table(name = "marcas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marcas.findAll", query = "SELECT m FROM Marcas m")
    , @NamedQuery(name = "Marcas.findByIdMarcas", query = "SELECT m FROM Marcas m WHERE m.idMarcas = :idMarcas")
    , @NamedQuery(name = "Marcas.findByDescripcion", query = "SELECT m FROM Marcas m WHERE m.descripcion = :descripcion")})
public class Marcas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marcas")
    private Integer idMarcas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marcas")
    private Collection<Vehiculos> vehiculosCollection;

    public Marcas() {
    }

    public Marcas(Integer idMarcas) {
        this.idMarcas = idMarcas;
    }

    public Marcas(Integer idMarcas, String descripcion) {
        this.idMarcas = idMarcas;
        this.descripcion = descripcion;
    }

    public Integer getIdMarcas() {
        return idMarcas;
    }

    public void setIdMarcas(Integer idMarcas) {
        this.idMarcas = idMarcas;
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
        hash += (idMarcas != null ? idMarcas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marcas)) {
            return false;
        }
        Marcas other = (Marcas) object;
        if ((this.idMarcas == null && other.idMarcas != null) || (this.idMarcas != null && !this.idMarcas.equals(other.idMarcas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uce.entidades.Marcas[ idMarcas=" + idMarcas + " ]";
    }
    
}
