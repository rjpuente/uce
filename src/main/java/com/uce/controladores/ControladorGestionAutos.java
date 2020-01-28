/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import com.uce.entidades.Color;
import com.uce.entidades.Marcas;
import com.uce.entidades.TipoCombustible;
import com.uce.entidades.TiposVehiculos;
import com.uce.entidades.Vehiculos;
import com.uce.persistencia.ejb.ColorFacade;
import com.uce.persistencia.ejb.MarcasFacade;
import com.uce.persistencia.ejb.TipoCombustibleFacade;
import com.uce.persistencia.ejb.TiposVehiculosFacade;
import com.uce.persistencia.ejb.VehiculosFacade;
import com.uce.util.EstadoVehiculo;
import com.uce.util.ObjetoGuardarBlobEnFicheroTemporal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Java
 */
@Named("controladorGestionAutos")
@ViewScoped
public class ControladorGestionAutos extends ControladorBase implements Serializable {

    @EJB
    private VehiculosFacade vehiculosFacade;
    @EJB
    private ColorFacade colorFacade;
    @EJB
    private TiposVehiculosFacade tiposVehiculosFacade;
    @EJB
    private MarcasFacade marcasFacade;
    @EJB
    private TipoCombustibleFacade tipoCombustibleFacade;

    private List<Vehiculos> listaVehiculos;
    private List<Color> listaColores;
    private List<TiposVehiculos> listaTiposVehiculos;
    private List<Marcas> listaMarcas;
    private List<TipoCombustible> listaCombustibles;
    private List<Character> listaEstados;
    private Vehiculos vehiculoSeleccionado;
    private Integer codigoMarca;
    private Integer codigoCombustible;
    private Integer codigoTipoVehiculo;
    private Integer codigoColor;
    private boolean esNuevo;
    private String imagenAuto;
    private String imagenAuto2;
    private String imagenAuto3;
    private Integer numeroImagen;

    @PostConstruct
    public void inicializar() {
        try {
            listaVehiculos = vehiculosFacade.obtenerListaVehiculos();
            listaColores = colorFacade.obtenerListaColores();
            listaTiposVehiculos = tiposVehiculosFacade.obtenerTiposVehiculos();
            listaMarcas = marcasFacade.obtenerListaMarcas();
            listaCombustibles = tipoCombustibleFacade.obtenerListaCombustible();
            listaEstados = EstadoVehiculo.lista();
            vehiculoSeleccionado = new Vehiculos();
            esNuevo = true;
            imagenAuto = "imagenes/tmp/imagenEnBlanco.png";
            imagenAuto2 = "imagenes/tmp/imagenEnBlanco.png";
            imagenAuto3 = "imagenes/tmp/imagenEnBlanco.png";
            numeroImagen = 0;
        } catch (Exception ex) {
            Logger.getLogger(ControladorGestionAutos.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizarListaAutos();
    }

    private void actualizarListaAutos() {
        try {
            listaVehiculos = vehiculosFacade.obtenerListaVehiculos();

            if (!listaVehiculos.isEmpty()) {
                vehiculoSeleccionado = listaVehiculos.get(0);
            }
        } catch (Exception ex) {
            Logger.getLogger(ControladorGestionAutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nuevoAuto() {
        this.vehiculoSeleccionado = new Vehiculos();
        limpiarCodigos();
        esNuevo = true;
        imagenAuto = " ";
        imagenAuto2 = " ";
        imagenAuto3 = " ";
        numeroImagen = 0;
    }

    public void editarAuto(Vehiculos vehiculo) {
        this.vehiculoSeleccionado = vehiculo;
        codigoColor = vehiculoSeleccionado.getColor().getIdColor();
        codigoCombustible = vehiculoSeleccionado.getTiposCombustible().getIdTipoCombustible();
        codigoMarca = vehiculoSeleccionado.getMarcas().getIdMarcas();
        codigoTipoVehiculo = vehiculoSeleccionado.getTiposVehiculos().getIdTiposVehiculos();
        esNuevo = false;
    }

    public void eliminarAuto(Vehiculos vehiculo) {
        try {
            vehiculosFacade.remove(vehiculo);
            mostrarMensaje("Vehiculo eliminado exitosamente");
            actualizarListaAutos();
        } catch (Exception ex) {
            Logger.getLogger(ControladorGestionAutos.class.getName()).log(Level.SEVERE, null, ex);
            mostrarError("No se pudo eliminar el vehiculo, " + ex.getMessage());
        }
    }

    public void guardarAuto() {
        if (!esNuevo) {

            if (vehiculoSeleccionado.getImagenVehiculo().length() <= 50 && vehiculoSeleccionado.getImagenVehiculo2().length() <= 50 && vehiculoSeleccionado.getImagenVehiculo3().length() <= 50) {
                try {
                    vehiculoSeleccionado.setColor(colorFacade.find(codigoColor));
                    vehiculoSeleccionado.setMarcas(marcasFacade.find(codigoMarca));
                    vehiculoSeleccionado.setTiposCombustible(tipoCombustibleFacade.find(codigoCombustible));
                    vehiculoSeleccionado.setTiposVehiculos(tiposVehiculosFacade.find(codigoTipoVehiculo));
                    vehiculosFacade.edit(vehiculoSeleccionado);
                    mostrarMensaje("Vehiculo modificado exitosamente!");
                    actualizarListaAutos();
                    PrimeFaces.current().executeScript("PF('dlgNuevoAuto').hide();");
                } catch (Exception ex) {
                    Logger.getLogger(ControladorGestionAutos.class.getName()).log(Level.SEVERE, null, ex);
                    mostrarMensaje("No se pudo modificar el vehiculo, " + ex.getMessage());
                }
            } else {
                mostrarError("Nombre de archivo demasiado grande");
            }

        } else {
            if (vehiculoSeleccionado.getImagenVehiculo().length() <= 50 && vehiculoSeleccionado.getImagenVehiculo2().length() <= 50 && vehiculoSeleccionado.getImagenVehiculo3().length() <= 50) {
                try {
                    vehiculoSeleccionado.setEstadoVehiculo(EstadoVehiculo.AUTO_DISPONIBLE);
                    vehiculoSeleccionado.setColor(colorFacade.find(codigoColor));
                    vehiculoSeleccionado.setMarcas(marcasFacade.find(codigoMarca));
                    vehiculoSeleccionado.setTiposCombustible(tipoCombustibleFacade.find(codigoCombustible));
                    vehiculoSeleccionado.setTiposVehiculos(tiposVehiculosFacade.find(codigoTipoVehiculo));
                    vehiculoSeleccionado = vehiculosFacade.create(vehiculoSeleccionado);
                    mostrarMensaje("Auto agregado exitosamente");
                    PrimeFaces.current().executeScript("PF('dlgNuevoAuto').hide();");
                    actualizarListaAutos();
                } catch (Exception ex) {
                    Logger.getLogger(ControladorGestionAutos.class.getName()).log(Level.SEVERE, null, ex.getMessage());
                    mostrarError("No se pudo agregar el auto");
                }
            } else {
                mostrarError("Nombre de archivo demasiado grande");
            }

        }
    }

    public String traducirEstadoAuto(char disponibilidad) {
        if (disponibilidad == 'D') {
            return "Disponible";
        } else if (disponibilidad == '0') {
            return "En Revision";
        } else {
            return "Rentado";
        }
    }

    private void limpiarCodigos() {
        codigoColor = 0;
        codigoCombustible = 0;
        codigoMarca = 0;
        codigoTipoVehiculo = 0;
    }

    public void subirImagen(FileUploadEvent event) {

        try {
            if (numeroImagen == 0) {
                imagenAuto = ObjetoGuardarBlobEnFicheroTemporal.guardarBlobEnFicheroTemporal(event.getFile().getContents(), event.getFile().getFileName());
                if (imagenAuto.length() <= 50) {

                    vehiculoSeleccionado.setImagenVehiculo(imagenAuto);
                    mostrarMensaje("Imagen agregada exitosamente");
                    numeroImagen++;
                } else {
                    imagenAuto = " ";
                    mostrarError("Nombre de imagen demasiado grande");
                }
            } else if (numeroImagen == 1) {
                imagenAuto2 = ObjetoGuardarBlobEnFicheroTemporal.guardarBlobEnFicheroTemporal(event.getFile().getContents(), event.getFile().getFileName());
                if (imagenAuto2.length() <= 50) {
                    vehiculoSeleccionado.setImagenVehiculo2(imagenAuto2);
                    mostrarMensaje("Imagen agregada exitosamente");
                    numeroImagen++;
                } else {
                    imagenAuto2 = " ";
                    mostrarError("Nombre de imagen demasiado grande");
                }
            } else if (numeroImagen == 2) {
                imagenAuto3 = ObjetoGuardarBlobEnFicheroTemporal.guardarBlobEnFicheroTemporal(event.getFile().getContents(), event.getFile().getFileName());
                if (imagenAuto3.length() <= 50) {
                    vehiculoSeleccionado.setImagenVehiculo3(imagenAuto3);
                    mostrarMensaje("Imagen agregada exitosamente");
                    numeroImagen++;
                } else {
                    imagenAuto3 = " ";
                    mostrarError("Nombre de imagen demasiado grande");
                }
            } else {
                mostrarError("Solo se permiten 3 imagenes");

            }
        } catch (Exception ex) {
            Logger.getLogger(ControladorGestionAutos.class
                    .getName()).log(Level.SEVERE, null, ex.getMessage());
            mostrarError("No se pudo agregar la imagen del auto");
        }
    }

    /*
     * Getter`s y Setter`s
     */
    public List<Vehiculos> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculos> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public List<Color> getListaColores() {
        return listaColores;
    }

    public void setListaColores(List<Color> listaColores) {
        this.listaColores = listaColores;
    }

    public List<TiposVehiculos> getListaTiposVehiculos() {
        return listaTiposVehiculos;
    }

    public void setListaTiposVehiculos(List<TiposVehiculos> listaTiposVehiculos) {
        this.listaTiposVehiculos = listaTiposVehiculos;
    }

    public List<Marcas> getListaMarcas() {
        return listaMarcas;
    }

    public void setListaMarcas(List<Marcas> listaMarcas) {
        this.listaMarcas = listaMarcas;
    }

    public List<TipoCombustible> getListaCombustibles() {
        return listaCombustibles;
    }

    public void setListaCombustibles(List<TipoCombustible> listaCombustibles) {
        this.listaCombustibles = listaCombustibles;
    }

    public Vehiculos getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }

    public void setVehiculoSeleccionado(Vehiculos vehiculoSeleccionado) {
        this.vehiculoSeleccionado = vehiculoSeleccionado;
    }

    public Integer getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(Integer codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public Integer getCodigoCombustible() {
        return codigoCombustible;
    }

    public void setCodigoCombustible(Integer codigoCombustible) {
        this.codigoCombustible = codigoCombustible;
    }

    public Integer getCodigoTipoVehiculo() {
        return codigoTipoVehiculo;
    }

    public void setCodigoTipoVehiculo(Integer codigoTipoVehiculo) {
        this.codigoTipoVehiculo = codigoTipoVehiculo;
    }

    public Integer getCodigoColor() {
        return codigoColor;
    }

    public void setCodigoColor(Integer codigoColor) {
        this.codigoColor = codigoColor;
    }

    public List<Character> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Character> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public String getImagenAuto() {
        return imagenAuto;
    }

    public void setImagenAuto(String imagenAuto) {
        this.imagenAuto = imagenAuto;
    }

    public String getImagenAuto2() {
        return imagenAuto2;
    }

    public void setImagenAuto2(String imagenAuto2) {
        this.imagenAuto2 = imagenAuto2;
    }

    public String getImagenAuto3() {
        return imagenAuto3;
    }

    public void setImagenAuto3(String imagenAuto3) {
        this.imagenAuto3 = imagenAuto3;
    }

}
