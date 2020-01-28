/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import com.uce.entidades.Clientes;
import com.uce.entidades.Empleados;
import com.uce.entidades.Ticket;
import com.uce.globales.Constantes;
import com.uce.persistencia.ejb.ClientesFacade;
import com.uce.persistencia.ejb.EmpleadosFacade;
import com.uce.persistencia.ejb.TicketFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Java
 */
@Named("controladorReporteRentaAutos")
@ViewScoped
public class ControladorReporteRentaAutos extends ControladorBase implements Serializable {

    @Inject
    private ControladorSesion controladorSesion;
    @EJB
    private TicketFacade ticketFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private ClientesFacade clientesFacade;

    private List<Ticket> listaTickets;
    private List<Ticket> listaTicketsFiltradoos;
    private List<Empleados> listaEmpleados;
    private List<Clientes> listaClientes;
    private List<String> listaEstadosTicket;
    private Long[] clientes;
    private Empleados empleadoSeleccionado;
    private Date fechaDesde;
    private Date fechaHasta;
    private String estadoSeleccionado;
    private Integer codigoEmpleadoSeleccionado;
    private Double total;

    @PostConstruct
    public void inicializar() {
        try {
            listaEmpleados = empleadosFacade.obtenerListaEmpelados();
            listaClientes = clientesFacade.obtenerListaClientes();
            fechaDesde = new Date();
            fechaHasta = new Date();
            listaEstadosTicket = Constantes.getListaEstadoTicket();
            total = 0d;
        } catch (Exception ex) {
            Logger.getLogger(ControladorReporteRentaAutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarReporte() {
        if (codigoEmpleadoSeleccionado != null) {
            empleadoSeleccionado = empleadosFacade.find(codigoEmpleadoSeleccionado);
        }
        listaTickets = ticketFacade.obtenerReporteTickets(fechaDesde, fechaHasta, Constantes.traducirEstadoTicketAChar(estadoSeleccionado), empleadoSeleccionado, clientes);
    }

    public void onItemUnselectEvent(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        mostrarMensaje("Cliente no seleccionado");
    }

    public Double obtenerTotal(Double pagoAlquiler) {
        if (pagoAlquiler != null) {
            return total += pagoAlquiler;
        } else {
            return total;
        }
    }


    /*
     * Getter´s y Setter´s
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public List<String> getListaEstadosTicket() {
        return listaEstadosTicket;
    }

    public void setListaEstadosTicket(List<String> listaEstadosTicket) {
        this.listaEstadosTicket = listaEstadosTicket;
    }

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public List<Ticket> getListaTickets() {
        return listaTickets;
    }

    public void setListaTickets(List<Ticket> listaTickets) {
        this.listaTickets = listaTickets;
    }

    public List<Empleados> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleados> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Empleados getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Empleados empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public Integer getCodigoEmpleadoSeleccionado() {
        return codigoEmpleadoSeleccionado;
    }

    public void setCodigoEmpleadoSeleccionado(Integer codigoEmpleadoSeleccionado) {
        this.codigoEmpleadoSeleccionado = codigoEmpleadoSeleccionado;
    }

    public List<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Long[] getClientes() {
        return clientes;
    }

    public void setClientes(Long[] clientes) {
        this.clientes = clientes;
    }

    public List<Ticket> getListaTicketsFiltradoos() {
        return listaTicketsFiltradoos;
    }

    public void setListaTicketsFiltradoos(List<Ticket> listaTicketsFiltradoos) {
        this.listaTicketsFiltradoos = listaTicketsFiltradoos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
