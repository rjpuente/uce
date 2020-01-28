/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import com.uce.entidades.Clientes;
import com.uce.globales.Constantes;
import com.uce.persistencia.ejb.ClientesFacade;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Java
 */
@Named("controladorRegistro")
@ViewScoped
public class ControladorRegistro extends ControladorBase implements Serializable {
    
    @Inject
    private ControladorSesion controladorSesion;
    @EJB
    private ClientesFacade clientesFacade;
    
    private boolean usuarioSeleccionado;
    private boolean solicitudEnviada;
    private String ruc;
    private String contrasena;
    private String confirmarContrasena;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private boolean yaRegistrado;
    
    @PostConstruct
    public void inicializar() {
        usuarioSeleccionado = false;
        solicitudEnviada = false;
        yaRegistrado = false;
    }
    
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
    
    public String nuevoUsuario() {
        if (!contrasena.isEmpty()) {
            if (contrasena.equals(confirmarContrasena)) {
                try {
                    
                    if (validarCedula(ruc)) {
                        Clientes clienteActual = new Clientes();
                        clienteActual.setCedula(Long.valueOf(ruc));
                        clienteActual.setCorreoElectronicoCliente(email);
                        clienteActual.setDireccionCliente(direccion);
                        clienteActual.setNombreCliente(nombre + " " + apellidos);
                        String encript_password = DigestUtils.sha1Hex(contrasena);
                        clienteActual.setContrasenaCliente(encript_password);
                        clienteActual.setEstadoRegistro(Constantes.ESTADO_CONTRIBUYENTE_REGISTRADO);
                        clientesFacade.create(clienteActual);
                        mostrarMensaje("Cliente creado exitosamente");
                        return "login.xhtml?faces-redirect=true";
                    } else {
                        mostrarError("Ingrese un numero de cedula válido");
                    }
                } catch (Exception ex) {
                    mostrarError("No se pudo crear el usuario");
                }
            } else {
                mostrarError("Las contraseñas no coinciden");
                return "registro.xhtml";
            }
        }
        return "";
    }
    
    private boolean validarCedula(String cedula) {
        boolean cedulaCorrecta = false;
        
        try {
            
            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }
                    
                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            mostrarError("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }
        return cedulaCorrecta;
    }

    /**
     * Redireccionar pantalla
     */
    public void redireccionarALogin() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("contentHeight", "100%");
        options.put("contentWidth", "100%");
        options.put("height", "20vh");
        options.put("width", "30%");
        
        PrimeFaces.current().dialog().openDynamic("login.xhtml", options, null);
    }

    /*
     * Getter´s y Setter´s
     */
    public ControladorSesion getControladorSesion() {
        return controladorSesion;
    }
    
    public void setControladorSesion(ControladorSesion controladorSesion) {
        this.controladorSesion = controladorSesion;
    }
    
    public boolean isUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }
    
    public void setUsuarioSeleccionado(boolean usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    public boolean isSolicitudEnviada() {
        return solicitudEnviada;
    }
    
    public void setSolicitudEnviada(boolean solicitudEnviada) {
        this.solicitudEnviada = solicitudEnviada;
    }
    
    public String getRuc() {
        return ruc;
    }
    
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isYaRegistrado() {
        return yaRegistrado;
    }
    
    public void setYaRegistrado(boolean yaRegistrado) {
        this.yaRegistrado = yaRegistrado;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String getConfirmarContrasena() {
        return confirmarContrasena;
    }
    
    public void setConfirmarContrasena(String confirmarContrasena) {
        this.confirmarContrasena = confirmarContrasena;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
}
