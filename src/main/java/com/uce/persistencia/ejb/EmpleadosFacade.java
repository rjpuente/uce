/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.persistencia.ejb;

import com.uce.entidades.Clientes;
import com.uce.entidades.Empleados;
import com.uce.globales.Constantes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Java
 */
@Stateless
public class EmpleadosFacade extends AbstractFacade<Empleados> {

    @PersistenceContext(unitName = "PlanificacionRentaAC")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadosFacade() {
        super(Empleados.class);
    }

    public Empleados validarCredenciales(String idEmpleado, String contrasena) throws Exception {
        try {
            String encrip_password = DigestUtils.sha1Hex(contrasena);
            System.out.println(String.format("idEmpleado: -->%s<--", idEmpleado));
            System.out.println(String.format("Contrasena: -->%s<--", contrasena));
            System.out.println(String.format("Encriptado: -->%s<--", encrip_password));
            Query validacion = em.createQuery("SELECT e FROM Empleados e WHERE e.idEmpleado = :idEmpleado AND e.contrasenaEmpleado = :contrasena AND e.estadoEmpelados = :estadoEmpleados", Empleados.class);
            validacion.setParameter("idEmpleado", Integer.valueOf(idEmpleado));
            if (idEmpleado.equals(Constantes.ID_ADMINISTRADOR)) {
                validacion.setParameter("contrasena", contrasena);
            } else {
                validacion.setParameter("contrasena", encrip_password);
            }
            validacion.setParameter("estadoEmpleados", Constantes.EMPLEADO_ACTIVO);
            Empleados ret = (Empleados) validacion.getSingleResult();
            return ret;
        } catch (NoResultException nre) {
            nre.printStackTrace();
            return null;
        }
    }

    public Empleados obtenerPorLogin(String idEmpleado) throws Exception {
        try {
            Query consulta = em.createQuery("SELECT u FROM Empleados u WHERE u.idEmpleado = :idEmpleado");
            consulta.setParameter("idEmpleado", Integer.valueOf(idEmpleado));
            consulta.setMaxResults(1);
            return (Empleados) consulta.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Empleados> obtenerListaEmpelados() throws Exception {
        Query q = em.createQuery("SELECT E FROM Empleados AS E WHERE E.estadoEmpelados = :estadoEmpleados ORDER BY E.nombreEmpleado DESC", Empleados.class);
        q.setParameter("estadoEmpleados", Constantes.EMPLEADO_ACTIVO);
        return (List<Empleados>) q.getResultList();
    }

    public List<Empleados> obtenerListaEmpleadosInactivos() throws Exception {
        Query q = em.createQuery("SELECT E FROM Empleados E WHERE E.estadoEmpelados = :estadoEmpleadoas ORDER BY E.nombreEmpleado ASC", Empleados.class);
        q.setParameter("estadoEmpleadoas", Constantes.EMPLEADO_INACTIVO);
        return (List<Empleados>) q.getResultList();
    }

}
