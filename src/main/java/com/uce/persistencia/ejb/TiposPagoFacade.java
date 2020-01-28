/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.persistencia.ejb;

import com.uce.entidades.TiposPago;
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
public class TiposPagoFacade extends AbstractFacade<TiposPago> {

    @PersistenceContext(unitName = "PlanificacionRentaAC")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposPagoFacade() {
        super(TiposPago.class);
    }
    
    public List<TiposPago> obtenerListaTiposPago(){
        Query q = em.createQuery("SELECT TP FROM TiposPago TP ORDER BY TP.descripcion ASC", TiposPago.class);
        
        return (List<TiposPago>)q.getResultList();
    }
    
}
