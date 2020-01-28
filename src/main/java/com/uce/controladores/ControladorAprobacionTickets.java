/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import com.uce.entidades.Ticket;
import com.uce.globales.Constantes;
import com.uce.persistencia.ejb.TicketFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Java
 */
@Named("controladorAprobacionTickets")
@ViewScoped
public class ControladorAprobacionTickets extends ControladorBase implements Serializable {
    
    @Inject
    private ControladorSesion controladorSesion;
    @EJB
    private TicketFacade ticketFacade;
    
    private List<Ticket> listaTicketsPorAprobar;
    private Ticket ticketSelecionado;
    
    @PostConstruct
    public void inicializar() {
        try {
            listaTicketsPorAprobar = ticketFacade.obtenerTicketsPorAprobar();
        } catch (Exception ex) {
            Logger.getLogger(ControladorAprobacionTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void iniciarAprobacion(Ticket ticket) {
        this.ticketSelecionado = ticket;
    }
    
    public void aprobarTicket() {
        try {
            ticketSelecionado.setEmpleado(controladorSesion.getEmpleadoActual());
            ticketSelecionado.setEstadoTicket(Constantes.APROBADO);
            ticketFacade.create(ticketSelecionado);
            PrimeFaces.current().executeScript("PF('dlgAprobarTicket').hide();");
            mostrarMensaje("Ticket aprobado correctamente");
            
        } catch (Exception e) {
            Logger.getLogger(ControladorAprobacionTickets.class.getName()).log(Level.SEVERE, null, e);
            mostrarError("No se pudo aprobar el ticket");
        }
    }
    
    public void rechazarTicket() {
        try {
            ticketSelecionado.setEstadoTicket(Constantes.RECHAZADO);
            ticketSelecionado.setEmpleado(controladorSesion.getEmpleadoActual());
            mostrarMensaje("Ticket rechazado exitosamente");
        } catch (Exception ex) {
            Logger.getLogger(ControladorAprobacionTickets.class.getName()).log(Level.SEVERE, null, ex);
            mostrarError("No se pudo rechazar el ticket");
        }
    }

    /*
     * Getter´s y Setter´s
     */
    public List<Ticket> getListaTicketsPorAprobar() {
        return listaTicketsPorAprobar;
    }
    
    public void setListaTicketsPorAprobar(List<Ticket> listaTicketsPorAprobar) {
        this.listaTicketsPorAprobar = listaTicketsPorAprobar;
    }
    
    public Ticket getTicketSelecionado() {
        return ticketSelecionado;
    }
    
    public void setTicketSelecionado(Ticket ticketSelecionado) {
        this.ticketSelecionado = ticketSelecionado;
    }
    
}
