/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Java
 */
@Entity
@Table(name = "permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p")
    , @NamedQuery(name = "Permiso.findByCodigoPermiso", query = "SELECT p FROM Permiso p WHERE p.codigoPermiso = :codigoPermiso")
    , @NamedQuery(name = "Permiso.findByDescripcion", query = "SELECT p FROM Permiso p WHERE p.descripcion = :descripcion")})
public class Permiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_permiso")
    private Integer codigoPermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;

    public Permiso() {
    }

    public Permiso(Integer codigoPermiso) {
        this.codigoPermiso = codigoPermiso;
    }

    public Permiso(Integer codigoPermiso, String descripcion) {
        this.codigoPermiso = codigoPermiso;
        this.descripcion = descripcion;
    }

    public Integer getCodigoPermiso() {
        return codigoPermiso;
    }

    public void setCodigoPermiso(Integer codigoPermiso) {
        this.codigoPermiso = codigoPermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPermiso != null ? codigoPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.codigoPermiso == null && other.codigoPermiso != null) || (this.codigoPermiso != null && !this.codigoPermiso.equals(other.codigoPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uce.entidades.Permiso[ codigoPermiso=" + codigoPermiso + " ]";
    }
    
}
