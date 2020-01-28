/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.persistencia.ejb;

import com.uce.entidades.TiposVehiculos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Java
 */
@Stateless
public class TiposVehiculosFacade extends AbstractFacade<TiposVehiculos> {

    @PersistenceContext(unitName = "PlanificacionRentaAC")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposVehiculosFacade() {
        super(TiposVehiculos.class);
    }

    public List<TiposVehiculos> obtenerTiposVehiculos() {
        Query q = em.createQuery("SELECT T FROM TiposVehiculos T ORDER BY T.descripcion ASC", TiposVehiculos.class);
        return (List<TiposVehiculos>) q.getResultList();
    }

}
