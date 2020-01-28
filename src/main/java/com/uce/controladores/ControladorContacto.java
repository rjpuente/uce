/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;

/**
 *
 * @author Java
 */
@Named("controladorContatco")
@ViewScoped
public class ControladorContacto extends ControladorBase implements Serializable {

    private Double latitud;
    private Double longitud;
    private MapModel marcador;

    @PostConstruct
    public void inicializar() {
        latitud = (-0.2004);
        longitud = (-78.505);
        marcador = new DefaultMapModel();
        LatLng coord1 = new LatLng(latitud, longitud);
        marcador.addOverlay(new Marker(coord1, "Rent A Car"));
    }

    /*
     * Getter´s y Setter´s
     */

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public MapModel getMarcador() {
        return marcador;
    }

    public void setMarcador(MapModel marcador) {
        this.marcador = marcador;
    }

}
