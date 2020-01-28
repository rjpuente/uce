/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import com.uce.entidades.Color;
import com.uce.entidades.Marcas;
import com.uce.entidades.Ticket;
import com.uce.entidades.TipoCombustible;
import com.uce.entidades.TiposPago;
import com.uce.entidades.TiposVehiculos;
import com.uce.entidades.Vehiculos;
import com.uce.globales.Constantes;
import com.uce.persistencia.ejb.ColorFacade;
import com.uce.persistencia.ejb.MarcasFacade;
import com.uce.persistencia.ejb.TicketFacade;
import com.uce.persistencia.ejb.TipoCombustibleFacade;
import com.uce.persistencia.ejb.TiposPagoFacade;
import com.uce.persistencia.ejb.TiposVehiculosFacade;
import com.uce.persistencia.ejb.VehiculosFacade;
import com.uce.util.EstadoVehiculo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import java.util.stream.Collectors;

/**
 *
 * @author Java
 */
@Named("controladorPantallaPrincipalCliente")
@ViewScoped
public class ControladorPantallaPrincipalCliente extends ControladorBase implements Serializable {

    @Inject
    private ControladorSessionCliente controladorSessionCliente;

    @EJB
    private VehiculosFacade vehiculosFacade;
    @EJB
    private TiposPagoFacade tiposPagoFacade;
    @EJB
    private TicketFacade ticketFacade;
    @EJB
    private ColorFacade colorFacade;
    @EJB
    private MarcasFacade marcasFacade;
    @EJB
    private TipoCombustibleFacade tipoCombustibleFacade;
    @EJB
    private TiposVehiculosFacade tiposVehiculosFacade;

    private List<Vehiculos> listaVehiculosDisponible;
    private List<TiposPago> listaTiposPagos;
    private List<Color> listaColores;
    private List<Marcas> listaMarcas;
    private List<TipoCombustible> listaCombustibles;
    private List<TiposVehiculos> listaTiposVehiculos;
    private Vehiculos vehiculoSeleccionado;
    private Integer codigoMarca;
    private Integer codigoColor;
    private Integer codigoCmbustible;
    private Integer codigoTipoVehiculo;
    private Integer tipoDePago;
    private Date fechaSalida;
    private Date fechaEntrega;
    private Ticket ticket;
    private Double precioDesde;
    private Double precioHasta;
    private Integer cantidad;

    @PostConstruct
    public void inicializar() {
        try {
            listaVehiculosDisponible = vehiculosFacade.obtenerListaVehiculosDisponibles();
            listaColores = colorFacade.obtenerListaColores();
            listaCombustibles = tipoCombustibleFacade.obtenerListaCombustible();
            listaMarcas = marcasFacade.obtenerListaMarcas();
            listaTiposVehiculos = tiposVehiculosFacade.obtenerTiposVehiculos();
            listaTiposPagos = new ArrayList<>();
            fechaSalida = new Date();
            fechaEntrega = new Date();
        } catch (Exception ex) {
            Logger.getLogger(ControladorPantallaPrincipalCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarListaAutosDisponibles() {
        try {
            listaVehiculosDisponible = vehiculosFacade.obtenerListaVehiculosDisponibles();
            if (!listaVehiculosDisponible.isEmpty()) {
                vehiculoSeleccionado = listaVehiculosDisponible.get(0);
            }
        } catch (Exception ex) {
            Logger.getLogger(ControladorPantallaPrincipalCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nueuvoTicket(Vehiculos vehiculo) {
        this.vehiculoSeleccionado = vehiculo;
        listaTiposPagos = new ArrayList<>();
        fechaSalida = new Date();
        fechaEntrega = new Date();
        listaTiposPagos = tiposPagoFacade.obtenerListaTiposPago();
        ticket = new Ticket();

    }

    public void guardarTicket() {

        switch (fechaSalida.compareTo(new Date())) {
            case -1:
                mostrarError("Porfavor elija una fecha posterior");
                break;
            case 0:
                try {
                    CrearYRellenarTicket();
                    mostrarMensaje("Ticket enviado para su aprobación");
                    PrimeFaces.current().executeScript("PF('dlgRentarAuto').hide();");
                    actualizarListaAutosDisponibles();
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(ControladorPantallaPrincipalCliente.class.getName()).log(Level.SEVERE, null, ex);
                    mostrarError("No se pudo generar el ticket");
                    break;
                }
            case 1:
                try {
                    CrearYRellenarTicket();
                    mostrarMensaje("Ticket enviado para su aprobación");
                    PrimeFaces.current().executeScript("PF('dlgRentarAuto').hide();");
                    actualizarListaAutosDisponibles();
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(ControladorPantallaPrincipalCliente.class.getName()).log(Level.SEVERE, null, ex);
                    mostrarError("No se pudo generar el ticket");
                    break;
                }

        }

    }

    private void CrearYRellenarTicket() {
        TiposPago t = tiposPagoFacade.find(tipoDePago);
        ticket.setFechaSalida(fechaSalida);
        ticket.setFechaEntrega(fechaEntrega);
        ticket.setCliente(controladorSessionCliente.getClienteActual());
        ticket.setTipoPago(t);
        ticket.setVehiculoPlaca(vehiculoSeleccionado);
        ticket.setEstadoTicket(Constantes.POR_APROBAR);
        ticket = ticketFacade.create(ticket);

        vehiculoSeleccionado.setCantidad(vehiculoSeleccionado.getCantidad() - 1);
        if (vehiculoSeleccionado.getCantidad() == 0) {
            vehiculoSeleccionado.setEstadoVehiculo(EstadoVehiculo.AUTO_RENTADO);
        }
        vehiculosFacade.edit(vehiculoSeleccionado);
    }

    public void aplicarFiltros() {
        if (codigoMarca != null || codigoCmbustible != null || (precioDesde != null && precioHasta != null) || codigoColor != null || codigoCmbustible != null || codigoTipoVehiculo != null || cantidad != null) {

            if (precioDesde != null && precioHasta != null && precioDesde >= precioHasta) {
                mostrarError("Debe ingresar precios diferentes");
            } else if (precioHasta != null && precioHasta == 0) {
                mostrarError("El precio limite no puede ser cero.");
            } else {
                try {
                    listaVehiculosDisponible = vehiculosFacade.obtenerListaVehiculosPorFiltros(codigoMarca, cantidad, precioDesde, precioHasta, codigoColor, codigoCmbustible, codigoTipoVehiculo);
                    mostrarMensaje("Filtros aplicados correctamente");
                } catch (Exception ex) {
                    Logger.getLogger(ControladorPantallaPrincipalCliente.class.getName()).log(Level.SEVERE, null, ex);
                    mostrarError("No se pudieron aplicar los filtros");
                }
            }
        } else {
            actualizarListaAutosDisponibles();
        }
    }

    public void limpiarFiltros() {
        codigoMarca = null;
        cantidad = null;
        precioDesde = null;
        precioHasta = null;
        codigoColor = null;
        codigoCmbustible = null;
        actualizarListaAutosDisponibles();
    }

    /*
     * Getter´´s Y Setter´s
     */
    public List<Vehiculos> getListaVehiculosDisponible() {
        return listaVehiculosDisponible;
    }

    public void setListaVehiculosDisponible(List<Vehiculos> listaVehiculosDisponible) throws Exception {
        this.listaVehiculosDisponible = listaVehiculosDisponible;
    }

    public List<TiposPago> getListaTiposPagos() {
        return listaTiposPagos;
    }

    public void setListaTiposPagos(List<TiposPago> listaTiposPagos) {
        this.listaTiposPagos = listaTiposPagos;
    }

    public Vehiculos getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }

    public void setVehiculoSeleccionado(Vehiculos vehiculoSeleccionado) {
        this.vehiculoSeleccionado = vehiculoSeleccionado;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Integer getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(Integer tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public List<Color> getListaColores() {
        return listaColores;
    }

    public void setListaColores(List<Color> listaColores) {
        this.listaColores = listaColores;
    }

    public List<Marcas> getListaMarcases() {
        return listaMarcas;
    }

    public void setListaMarcases(List<Marcas> listaMarcas) {
        this.listaMarcas = listaMarcas;
    }

    public List<TipoCombustible> getListaCombustibles() {
        return listaCombustibles;
    }

    public void setListaCombustibles(List<TipoCombustible> listaCombustibles) {
        this.listaCombustibles = listaCombustibles;
    }

    public Double getPrecioDesde() {
        return precioDesde;
    }

    public void setPrecioDesde(Double precioDesde) {
        this.precioDesde = precioDesde;
    }

    public Double getPrecioHasta() {
        return precioHasta;
    }

    public void setPrecioHasta(Double precioHasta) {
        this.precioHasta = precioHasta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<Marcas> getListaMarcas() {
        return listaMarcas;
    }

    public void setListaMarcas(List<Marcas> listaMarcas) {
        this.listaMarcas = listaMarcas;
    }

    public List<TiposVehiculos> getListaTiposVehiculos() {
        return listaTiposVehiculos;
    }

    public void setListaTiposVehiculos(List<TiposVehiculos> listaTiposVehiculos) {
        this.listaTiposVehiculos = listaTiposVehiculos;
    }

    public ColorFacade getColorFacade() {
        return colorFacade;
    }

    public void setColorFacade(ColorFacade colorFacade) {
        this.colorFacade = colorFacade;
    }

    public Integer getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(Integer codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public Integer getCodigoColor() {
        return codigoColor;
    }

    public void setCodigoColor(Integer codigoColor) {
        this.codigoColor = codigoColor;
    }

    public Integer getCodigoCmbustible() {
        return codigoCmbustible;
    }

    public void setCodigoCmbustible(Integer codigoCmbustible) {
        this.codigoCmbustible = codigoCmbustible;
    }

    public Integer getCodigoTipoVehiculo() {
        return codigoTipoVehiculo;
    }

    public void setCodigoTipoVehiculo(Integer codigoTipoVehiculo) {
        this.codigoTipoVehiculo = codigoTipoVehiculo;
    }

}
