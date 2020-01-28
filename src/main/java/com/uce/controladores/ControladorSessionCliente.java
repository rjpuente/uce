/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import com.uce.entidades.Clientes;
import com.uce.entidades.Vehiculos;
import com.uce.persistencia.ejb.ClientesFacade;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Java
 */
@Named("controladorSessionCliente")
@SessionScoped
public class ControladorSessionCliente extends ControladorBase implements Serializable {

    @EJB
    private ClientesFacade clientesFacade;

    private List<Vehiculos> vehiculosDisponibles;
    private Clientes clienteActual;
    private String ruc;
    private String contraseña;
    private String token;
    private String mensajeValidacion;
    private String direccion;
    private String telefono;
    private String email;

    @PostConstruct
    public void inicializar() {
        
    }

    //Validar Login
    public String validarRucPassword() {
        Clientes u = null;
        try {
            u = clientesFacade.validarCredenciales(ruc, contraseña);
        } catch (Exception ex) {
            Logger.getLogger(ControladorSessionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean valid = (u != null);
        if (valid) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("ruc", ruc);
            try {
                clienteActual = clientesFacade.obtenerPorLogin(ruc);
                return "cliente/principal.xhtml?faces-redirect=true";
            } catch (Exception ex) {
                Logger.getLogger(ControladorSessionCliente.class.getName()).log(Level.SEVERE, null, ex);
                return "login";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario y/o contraseña incorrecto",
                            "Ingrese un usuario y/o contraseña"));
            return "login";
        }
    }

    //logout 
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/loginCliente.xhtml?faces-redirect=true";
    }


    
    
    /*
     * Getter´s y Setter´s
     */
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Clientes getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(Clientes clienteActual) {
        this.clienteActual = clienteActual;
    }
    
    
    /**
     * Clase para obtener la session del usuario, el nombre y el ruc
     */
    public static class SessionUtils {

        public SessionUtils() {
        }

        public static HttpSession getSession() {
            return (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
        }

        public static HttpServletRequest getRequest() {
            return (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        }

        public static String getUserName() {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            return session.getAttribute("ruc").toString();
        }

        public static String getUserId() {
            HttpSession session = getSession();
            if (session != null) {
                return (String) session.getAttribute("ruc");
            } else {
                return null;
            }
        }
    }

}
