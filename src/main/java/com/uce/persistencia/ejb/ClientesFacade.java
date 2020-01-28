/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.persistencia.ejb;

import com.uce.entidades.Clientes;
import com.uce.globales.Constantes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Java
 */
@Stateless
public class ClientesFacade extends AbstractFacade<Clientes> {

    @PersistenceContext(unitName = "PlanificacionRentaAC")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientesFacade() {
        super(Clientes.class);
    }

    public Clientes validarCredenciales(String ruc, String contrasena) throws Exception {
        try {
            String encrip_password = DigestUtils.sha1Hex(contrasena);
            System.out.println(String.format("Ruc: -->%s<--", ruc));
            System.out.println(String.format("Contrasena: -->%s<--", contrasena));
            System.out.println(String.format("Encriptado: -->%s<--", encrip_password));
            Query validacion = em.createQuery("SELECT u FROM Clientes u WHERE u.cedula = :ruc AND u.contrasenaCliente =:contrasena AND u.estadoRegistro = :estado", Clientes.class);
            validacion.setParameter("ruc", Integer.valueOf(ruc));
            validacion.setParameter("contrasena", encrip_password);
            validacion.setParameter("estado", Constantes.ESTADO_CONTRIBUYENTE_REGISTRADO);
            Clientes ret = (Clientes) validacion.getSingleResult();
            return ret;
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Clientes obtenerPorLogin(String ruc) throws Exception {
        try {
            Query consulta = em.createQuery("SELECT u FROM Clientes u WHERE u.cedula = :ruc");
            consulta.setParameter("ruc", Integer.valueOf(ruc));
            consulta.setMaxResults(1);
            return (Clientes) consulta.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Clientes> obtenerListaClientes() {
        Query q = em.createQuery("SELECT A FROM Clientes A WHERE A.estadoRegistro = :estadoRegistro", Clientes.class);
        q.setParameter("estadoRegistro", Constantes.ESTADO_CONTRIBUYENTE_REGISTRADO);

        return (List<Clientes>) q.getResultList();
    }

}
