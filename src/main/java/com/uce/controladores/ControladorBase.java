/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Java
 */
public class ControladorBase {

    public void mostrarMensaje(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        //context.addMessage(null, new FacesMessage(mensaje));
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary(mensaje);
        context.addMessage(null, facesMessage);
    }

    public void mostrarError(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
    }

    public void redirigir(String pagina) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(pagina);
        } catch (IOException ex) {
            Logger.getLogger(ControladorBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
