/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import com.uce.entidades.Cargo;
import com.uce.persistencia.ejb.CargoFacade;
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
 * @author rpuente
 */
@Named("controladorAdministracionCargos")
@ViewScoped
public class ControladorAdministracionCargos extends ControladorBase implements Serializable {

    @Inject
    private ControladorSesion controladorSesion;

    @EJB
    private CargoFacade cargoFacade;

    private List<Cargo> listaCargo;
    private Cargo cargoSeleccionado;
    private boolean cargoAdministrador;

    @PostConstruct
    public void inicializar() {

        try {
            listaCargo = cargoFacade.obtenerListaCargos();
        } catch (Exception ex) {
            Logger.getLogger(ControladorAdministracionCargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void actualizarListaCargos() {
        try {
            listaCargo = cargoFacade.obtenerListaCargos();
            if (!listaCargo.isEmpty()) {
                cargoSeleccionado = listaCargo.get(0);
            }
        } catch (Exception e) {
            Logger.getLogger(ControladorGestionarFuncionarios.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void nuevoCargo() {
        this.cargoSeleccionado = new Cargo();
    }

    public void editarCargo(Cargo cargoSeleccionado) {
        this.cargoSeleccionado = cargoSeleccionado;
    }

    public void eliminarCargo(Cargo cargoSeleccionado) {
        cargoFacade.remove(cargoSeleccionado);
        actualizarListaCargos();
    }

    public void guardarCargo() {
        if (this.cargoSeleccionado.getCodigoCargo() != null) {
            cargoFacade.edit(cargoSeleccionado);
                mostrarMensaje("Cargo modificado exitosamente!");
                PrimeFaces.current().executeScript("PF('dlgCargo').hide();");
            actualizarListaCargos();
        } else {
            try {
                if (cargoAdministrador) {
                    cargoSeleccionado.setCargoAdministradorSistema(1);
                } else {
                    cargoSeleccionado.setCargoAdministradorSistema(0);
                }
                cargoSeleccionado = cargoFacade.create(cargoSeleccionado);
                mostrarMensaje("Cargo creado exitosamente!");
                PrimeFaces.current().executeScript("PF('dlgCargo').hide();");
                actualizarListaCargos();
            } catch (Exception ex) {
                mostrarError("No se pudo crear el cargo");
            }
        }
    }

    /*
     * Getters y Setters
     */
    public List<Cargo> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<Cargo> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public Cargo getCargoSeleccionado() {
        return cargoSeleccionado;
    }

    public void setCargoSeleccionado(Cargo cargoSeleccionado) {
        this.cargoSeleccionado = cargoSeleccionado;
    }

    public boolean isCargoAdministrador() {
        return cargoAdministrador;
    }

    public void setCargoAdministrador(boolean cargoAdministrador) {
        this.cargoAdministrador = cargoAdministrador;
    }
}
