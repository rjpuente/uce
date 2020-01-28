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
@Table(name = "tipo_combustible")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCombustible.findAll", query = "SELECT t FROM TipoCombustible t")
    , @NamedQuery(name = "TipoCombustible.findByIdTipoCombustible", query = "SELECT t FROM TipoCombustible t WHERE t.idTipoCombustible = :idTipoCombustible")
    , @NamedQuery(name = "TipoCombustible.findByDescripcion", query = "SELECT t FROM TipoCombustible t WHERE t.descripcion = :descripcion")})
public class TipoCombustible implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_combustible")
    private Integer idTipoCombustible;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiposCombustible")
    private Collection<Vehiculos> vehiculosCollection;

    public TipoCombustible() {
    }

    public TipoCombustible(Integer idTipoCombustible) {
        this.idTipoCombustible = idTipoCombustible;
    }

    public TipoCombustible(Integer idTipoCombustible, String descripcion) {
        this.idTipoCombustible = idTipoCombustible;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoCombustible() {
        return idTipoCombustible;
    }

    public void setIdTipoCombustible(Integer idTipoCombustible) {
        this.idTipoCombustible = idTipoCombustible;
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
        hash += (idTipoCombustible != null ? idTipoCombustible.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCombustible)) {
            return false;
        }
        TipoCombustible other = (TipoCombustible) object;
        if ((this.idTipoCombustible == null && other.idTipoCombustible != null) || (this.idTipoCombustible != null && !this.idTipoCombustible.equals(other.idTipoCombustible))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uce.entidades.TipoCombustible[ idTipoCombustible=" + idTipoCombustible + " ]";
    }
    
}
