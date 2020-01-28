/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.persistencia.ejb;

import com.uce.entidades.TipoCombustible;
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
public class TipoCombustibleFacade extends AbstractFacade<TipoCombustible> {

    @PersistenceContext(unitName = "PlanificacionRentaAC")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCombustibleFacade() {
        super(TipoCombustible.class);
    }

    public List<TipoCombustible> obtenerListaCombustible() {
        Query q = em.createQuery("SELECT TC FROM TipoCombustible TC ORDER BY TC.descripcion ASC", TipoCombustible.class);

        return (List<TipoCombustible>) q.getResultList();
    }

}
