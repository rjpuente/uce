/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.globales;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Java
 */
public class Constantes {

    //Estado cuenta contribuyente
    public static final char ESTADO_CONTRIBUYENTE_NO_REGISTRADO = 'O';
    public static final char ESTADO_CONTRIBUYENTE_POR_REGISTRAR = 'P';
    public static final char ESTADO_CONTRIBUYENTE_REGISTRADO = 'R';

    //Administrador
    public static final String ID_ADMINISTRADOR = "123456789";

    //Estado de ticket
    public static final char POR_APROBAR = '0';
    public static final char APROBADO = '1';
    public static final char RECHAZADO = 'R';

    //Estado cuenta empleados
    public static final char EMPLEADO_ACTIVO = 'A';
    public static final char EMPLEADO_INACTIVO = 'I';

    //Ruta en la que se escriben los pdf
    public static final String RUTA_TICKETS_PDF = "c:/pub/";
    public static final String RUTA_LOGO_RENTACAR = "\\\\imagenes\\logo1.png";

    public static List<String> getListaEstadoTicket() {
        List<String> listaEstadosTicket = new ArrayList<>();
        listaEstadosTicket.add(traducirEstadosTicket(POR_APROBAR));
        listaEstadosTicket.add(traducirEstadosTicket(APROBADO));
        listaEstadosTicket.add(traducirEstadosTicket(RECHAZADO));

        return listaEstadosTicket;
    }

    public static String traducirEstadosTicket(char estado) {
        if (estado == '0') {
            return "Por aprobar";
        } else if (estado == '1') {
            return "Aprobado";
        } else if (estado == 'R') {
            return "Rechazado";
        } else {
            return "";
        }
    }

    public static Character traducirEstadoTicketAChar(String estado) {
        if (estado == "Por aprobar") {
            return POR_APROBAR;
        } else if (estado == "Aprobado") {
            return APROBADO;
        } else if (estado == "Rechazado") {
            return RECHAZADO;
        } else {
            return null;
        }
    }
}
