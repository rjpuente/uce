/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import com.uce.entidades.Clientes;
import com.uce.entidades.Empleados;
import com.uce.persistencia.ejb.ClientesFacade;
import com.uce.persistencia.ejb.EmpleadosFacade;
import com.uce.persistencia.ejb.PermisoFacade;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Java
 */
@Named("controladorSesion")
@SessionScoped
public class ControladorSesion extends ControladorBase implements Serializable {

    @EJB
    private PermisoFacade permisoFacade;
    @EJB
    private ClientesFacade clientesFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;

    private String nombreUsuario;
    private String contrasena;
    private String numeroSerie;
    private Integer sistemaSeleccionado;
    private String mensajeValidacion;
    private Empleados empleadoActual;
    private String login;
    private Clientes clienteSeleccionado;

    @PostConstruct
    public void inicializar() {

    }

    public String obtenerUsuario(String query) {
        String[] arreglo = query.split("&");
        for (int i = 0; i < arreglo.length; i++) {
            String[] parametro = arreglo[i].split("=");
            if (parametro[0].equals("usuario")) {
                return parametro[1];
            }
        }
        return null;
    }

    public String obtenerContrasenia(String query) {
        String[] arreglo = query.split("&");
        for (int i = 0; i < arreglo.length; i++) {
            String[] parametro = arreglo[i].split("=");
            if (parametro[0].equals("contrasenia")) {
                return parametro[1];
            }
        }
        return null;
    }

    public String validarCredenciales() {
        Empleados e = null;
        try {
            e = empleadosFacade.validarCredenciales(nombreUsuario, contrasena);
        } catch (Exception ex) {
            Logger.getLogger(ControladorSessionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean valid = (e != null);
        if (valid) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("idEmpleado", nombreUsuario);
            try {
                empleadoActual = empleadosFacade.obtenerPorLogin(nombreUsuario);
                return "app/principal.xhtml?faces-redirect=true";
            } catch (Exception ex) {
                Logger.getLogger(ControladorSessionCliente.class.getName()).log(Level.SEVERE, null, ex);;
                return "loginFuncionario";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario y/o contraseña incorrecto",
                            "Ingrese un usuario y/o contraseña"));
            return "loginFuncionario";
        }
    }

    public String cerrarSesion() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("nombreUsuario", "");
        return "/login.xhtml?faces-redirect=true";
    }

    /**
     * Abrir Dialogos
     */
    public void abrirDialogoNuevoEmpleado() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("contentHeight", "100%");
        options.put("contentWidth", "100%");
        options.put("height", "50vh");
        options.put("width", "50%");

        PrimeFaces.current().dialog().openDynamic("nuevoCliente", options, null);
    }
    
    public void abrirDialogoCliente(Clientes cliente) {
        try {
            this.clienteSeleccionado = cliente;
            Map<String, Object> options = new HashMap<>();
            options.put("modal", true);
            options.put("contentHeight", "100%");
            options.put("contentWidth", "100%");
            options.put("height", "90vh");
            options.put("width", "84%");

            PrimeFaces.current().dialog().openDynamic("pantallaCliente", options, null);

        } catch (Exception ex) {
            Logger.getLogger(ControladorSesion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
    Getter´s y Setter´s
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Integer getSistemaSeleccionado() {
        return sistemaSeleccionado;
    }

    public void setSistemaSeleccionado(Integer sistemaSeleccionado) {
        this.sistemaSeleccionado = sistemaSeleccionado;
    }

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

    public Empleados getEmpleadoActual() {
        return empleadoActual;
    }

    public void setEmpleadoActual(Empleados empleadoActual) {
        this.empleadoActual = empleadoActual;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Clientes getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Clientes clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
