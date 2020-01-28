/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.persistencia.ejb;

import com.uce.entidades.Empleados;
import com.uce.entidades.Ticket;
import java.util.Date;
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
public class TicketFacade extends AbstractFacade<Ticket> {

    @PersistenceContext(unitName = "PlanificacionRentaAC")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketFacade() {
        super(Ticket.class);
    }

    public List<Ticket> obtenerTicketsPorAprobar() {
        Query q = em.createQuery("SELECT A FROM Ticket A WHERE A.estadoTicket = :estadoTicket ORDER BY A.fechaSalida DESC", Ticket.class);

        return (List<Ticket>) q.getResultList();
    }

    public List<Ticket> obtenerReporteTickets(Date fechaDesde, Date fechaHasta, Character tipo, Empleados empleado, Long[] clientes) {

        String consulta = "";
        consulta += "SELECT A FROM Ticket A WHERE A.fechaSalida BETWEEN :fechaDesde AND :fechaHasta ";
        if (tipo != null) {
            consulta += "AND A.estadoTicket = :estado ";
        }
        if (empleado != null) {
            consulta += "AND A.empleado = :empleado ";
        }
        if (clientes != null && clientes.length != 0 && clientes[0] != null) {
            consulta += "AND A.cliente.cedula IN (  ";

            for (int i = 0; i < clientes.length; i++) {
                consulta += clientes[i].toString();
                if (i < clientes.length) {
                    consulta += ", ";
                }
            }

        }
        consulta += "ORDER BY A.fechaSalida DESC ";

        Query q = em.createQuery(consulta, Ticket.class);

        q.setParameter("fechaDesde", fechaDesde);
        q.setParameter("fechaHasta", fechaHasta);

        if (tipo != null) {
            q.setParameter("estado", tipo);
        }

        if (empleado != null) {
            q.setParameter("empleado", empleado);
        }

        return (List<Ticket>) q.getResultList();
    }

}
