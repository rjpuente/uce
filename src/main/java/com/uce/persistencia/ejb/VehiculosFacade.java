/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.persistencia.ejb;

import com.uce.entidades.Vehiculos;
import com.uce.util.EstadoVehiculo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Java
 */
@Stateless
public class VehiculosFacade extends AbstractFacade<Vehiculos> {

    @EJB
    private TicketFacade ticketFacade;
    @EJB
    private ColorFacade colorFacade;
    @EJB
    private MarcasFacade marcasFacade;
    @EJB
    private TiposVehiculosFacade tiposVehiculosFacade;
    @EJB
    private TipoCombustibleFacade tipoCombustibleFacade;

    @PersistenceContext(unitName = "PlanificacionRentaAC")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculosFacade() {
        super(Vehiculos.class);
    }

    public List<Vehiculos> obtenerListaVehiculos() throws Exception {
        Query q = em.createQuery("SELECT V FROM Vehiculos V ORDER BY V.marcas desc ", Vehiculos.class);
        return (List<Vehiculos>) q.getResultList();
    }

    public List<Vehiculos> obtenerListaVehiculosDisponibles() {

        Query q = em.createQuery("SELECT A FROM Vehiculos AS A  WHERE A.estadoVehiculo = :estadoVehiculo ", Vehiculos.class);

        q.setParameter("estadoVehiculo", EstadoVehiculo.AUTO_DISPONIBLE);

        return (List<Vehiculos>) q.getResultList();
    }

    public List<Vehiculos> obtenerListaVehiculosPorFiltros(Integer codigoMarca, Integer cantidadDisponible, Double precioDesde, Double precioHasta, Integer codigoColor, Integer codigoCombustible, Integer codigoTipoVehiculo) {
        String consulta = "";
        consulta += "SELECT A.numero_placa, A.precio_actual, A.estado_vehiculo, A.cantidad, A.marcas, A.color, A.tipos_vehiculos, A.tipos_combustible ";
        consulta += "FROM vehiculos A ";
        consulta += "JOIN marcas B ON A.marcas = B.id_marcas ";
        consulta += "JOIN color C ON A.color = C.id_color ";
        consulta += "JOIN tipos_vehiculos D ON A.tipos_vehiculos = D.id_tipos_vehiculos ";
        consulta += "JOIN tipo_combustible E ON A.tipos_combustible = E.id_tipo_combustible ";
        consulta += "WHERE A.estado_vehiculo = ? ";
        if (precioDesde != null && precioHasta != null) {
            consulta += "AND A.precio_actual BETWEEN ? AND ? ";
        }
        if (codigoMarca != null) {
            consulta += "AND B.id_marcas = ? ";
        }
        if (cantidadDisponible != null) {
            consulta += "AND A.cantidad <= ? ";
        }
        if (codigoColor != null) {
            consulta += "AND C.id_color = ? ";
        }
        if (codigoCombustible != null) {
            consulta += "AND E.id_tipo_combustible = ? ";
        }
        if (codigoTipoVehiculo != null) {
            consulta += "AND D.id_tipos_vehiculos = ? ";
        }
        consulta += "ORDER BY A.numero_placa DESC; ";

        Query q = em.createNativeQuery(consulta);

        int i = 2;
        q.setParameter(1, EstadoVehiculo.AUTO_DISPONIBLE);
        if (precioDesde != null && precioHasta != null) {
            q.setParameter(i, precioDesde);
            i++;
            q.setParameter(i, precioHasta);
            i++;
        }
        if (codigoMarca != null) {
            q.setParameter(i, codigoMarca);
            i++;
        }
        if (cantidadDisponible != null) {
            q.setParameter(i, cantidadDisponible);
            i++;
        }
        if (codigoColor != null) {
            q.setParameter(i, codigoColor);
            i++;
        }
        if (codigoCombustible != null) {
            q.setParameter(i, codigoCombustible);
            i++;
        }
        if (codigoTipoVehiculo != null) {
            q.setParameter(i, codigoTipoVehiculo);
        }

        List<Vehiculos> listaVehiculosFiltrada = new ArrayList<>();

        try {
            List<Object[]> listaRespuesta = (List<Object[]>) q.getResultList();

            for (Object[] respuesta : listaRespuesta) {
                Vehiculos vehiculo = new Vehiculos();
                vehiculo = convertirAEntidadVehiculos(respuesta, vehiculo);
                listaVehiculosFiltrada.add(vehiculo);
            }
        } catch (Exception ex) {
            Logger.getLogger(VehiculosFacade.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return listaVehiculosFiltrada;
    }

    private Vehiculos convertirAEntidadVehiculos(Object[] respuesta, Vehiculos vehiculo) {
        vehiculo.setNumeroPlaca((String) respuesta[0]);
        vehiculo.setPrecioActual((BigDecimal) respuesta[1]);
        vehiculo.setEstadoVehiculo(((String) respuesta[2]).charAt(0));
        vehiculo.setCantidad((Integer) respuesta[3]);
        vehiculo.setMarcas(marcasFacade.find((Integer) respuesta[3]));
        vehiculo.setColor(colorFacade.find((Integer) respuesta[4]));
        vehiculo.setTiposVehiculos(tiposVehiculosFacade.find((Integer) respuesta[5]));
        vehiculo.setTiposCombustible(tipoCombustibleFacade.find((Integer) respuesta[6]));
        return vehiculo;
    }

}
