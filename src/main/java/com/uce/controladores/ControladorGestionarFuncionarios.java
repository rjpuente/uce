/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import com.uce.entidades.Cargo;
import com.uce.entidades.Empleados;
import com.uce.globales.Constantes;
import com.uce.persistencia.ejb.CargoFacade;
import com.uce.persistencia.ejb.EmpleadosFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Java
 */
@Named("controladorGestionarFuncionarios")
@ViewScoped
public class ControladorGestionarFuncionarios extends ControladorBase implements Serializable {

    private final static String PERMISO_ADMINISTRAR_FUNCIONARIOS = "ADMINISTRAR FUNCIONARIOS";

    @Inject
    private ControladorSesion controladorSesion;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private CargoFacade cargoFacade;

    private List<Empleados> listaEmpleados;
    private List<Empleados> listaEmpleadosDeshabilitados;
    private List<Cargo> cargos;
    private Empleados empleadoSeleccionado;
    private Integer codigoCargoSeleccionado;
    private boolean esNuevoEmpleado;

    @PostConstruct
    public void inicializar() {
        try {
            cargos = cargoFacade.obtenerListaCargos();
        } catch (Exception ex) {
            Logger.getLogger(ControladorGestionarFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizarListaEmpleados();
        esNuevoEmpleado = false;
    }

    private void actualizarListaEmpleados() {
        try {
            listaEmpleados = empleadosFacade.obtenerListaEmpelados();
            listaEmpleadosDeshabilitados = empleadosFacade.obtenerListaEmpleadosInactivos();
            if (!listaEmpleados.isEmpty()) {
                empleadoSeleccionado = listaEmpleados.get(0);
            }
            if (!listaEmpleadosDeshabilitados.isEmpty()) {
                empleadoSeleccionado = listaEmpleadosDeshabilitados.get(0);
            }
        } catch (Exception e) {
            Logger.getLogger(ControladorGestionarFuncionarios.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void nuevoEmpleado() {
        empleadoSeleccionado = new Empleados();
        esNuevoEmpleado = true;
    }

    public void editarFuncionario(Empleados empleados) {
        empleadoSeleccionado = empleados;
        if (empleadoSeleccionado.getEmpleadocargo() != null) {
            codigoCargoSeleccionado = empleadoSeleccionado.getEmpleadocargo().getCodigoCargo();
        } else {
            codigoCargoSeleccionado = null;
        }
        esNuevoEmpleado = false;
    }

    public void eliminarFuncionario(Empleados empleado) {
        empleadoSeleccionado = empleado;
        empleadoSeleccionado.setEstadoEmpelados(Constantes.EMPLEADO_INACTIVO);
        try {
            empleadosFacade.edit(empleado);
            mostrarMensaje("Empleado desactivado exitosamente");
            actualizarListaEmpleados();
        } catch (Exception ex) {
            Logger.getLogger(ControladorGestionarFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            mostrarError("No se pudo desactivar el empleado");
        }
    }

    public void activarFuncionario(Empleados empleadoDeshabilitado) {
        empleadoSeleccionado = empleadoDeshabilitado;
        empleadoSeleccionado.setEstadoEmpelados(Constantes.EMPLEADO_ACTIVO);
        try {
            empleadosFacade.edit(empleadoSeleccionado);
            mostrarMensaje("Empleado activado correctamente");
            actualizarListaEmpleados();
        } catch (Exception ex) {
            Logger.getLogger(ControladorGestionarFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            mostrarError("No se puede activar el usuairo");
        }
    }

    public void grabarUsuario() {
        try {
            Cargo cargo = cargoFacade.find(codigoCargoSeleccionado);
            empleadoSeleccionado.setEmpleadocargo(cargo);
            if (esNuevoEmpleado) {

                String cedula = empleadoSeleccionado.getIdEmpleado().toString();
                if (validarCedula(cedula)) {
                    empleadoSeleccionado.setContrasenaEmpleado(DigestUtils.sha1Hex(empleadoSeleccionado.getContrasenaEmpleado()));
                    empleadoSeleccionado.setEstadoEmpelados(Constantes.EMPLEADO_ACTIVO);
                    empleadoSeleccionado = empleadosFacade.create(empleadoSeleccionado);
                    mostrarMensaje("Funcionario creado exitosamente!");
                    actualizarListaEmpleados();
                    PrimeFaces.current().executeScript("PF('dlgFuncionarios').hide();");

                } else {
                    mostrarError("La cedula no es valida, porfavor ingrese un número de cédula valido.");
                }
            } else {
                empleadosFacade.edit(empleadoSeleccionado);
                mostrarMensaje("Fuinario editado exitosamente");
                PrimeFaces.current().executeScript("PF('dlgFuncionarios').hide();");
            }
        } catch (Exception ex) {
            mostrarError("No se puede grabar: " + ex.getMessage());
        }
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

    /*
     * Getter´s y Setter´s
     */
    public EmpleadosFacade getEmpleadosFacade() {
        return empleadosFacade;
    }

    public void setEmpleadosFacade(EmpleadosFacade empleadosFacade) {
        this.empleadosFacade = empleadosFacade;
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

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public Integer getCodigoCargoSeleccionado() {
        return codigoCargoSeleccionado;
    }

    public void setCodigoCargoSeleccionado(Integer codigoCargoSeleccionado) {
        this.codigoCargoSeleccionado = codigoCargoSeleccionado;
    }

    public boolean isEsNuevoEmpleado() {
        return esNuevoEmpleado;
    }

    public void setEsNuevoEmpleado(boolean esNuevoEmpleado) {
        this.esNuevoEmpleado = esNuevoEmpleado;
    }

    public List<Empleados> getListaEmpleadosDeshabilitados() {
        return listaEmpleadosDeshabilitados;
    }

    public void setListaEmpleadosDeshabilitados(List<Empleados> listaEmpleadosDeshabilitados) {
        this.listaEmpleadosDeshabilitados = listaEmpleadosDeshabilitados;
    }

}
