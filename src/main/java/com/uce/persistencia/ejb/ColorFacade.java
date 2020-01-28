/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.persistencia.ejb;

import com.uce.entidades.Color;
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
public class ColorFacade extends AbstractFacade<Color> {

    @PersistenceContext(unitName = "PlanificacionRentaAC")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ColorFacade() {
        super(Color.class);
    }

    public List<Color> obtenerListaColores() {
        Query q = em.createQuery("SELECT C FROM Color C ORDER BY C.descripcion asc");
        return (List<Color>) q.getResultList();
    }

}
