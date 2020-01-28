/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Java
 */
public class EstadoVehiculo implements Serializable {

    public static char AUTO_DISPONIBLE = 'D';
    public static char AUTO_RENTADO = 'R';
    public static char AUTO_EN_REVISION = '0';

    public static List<Character> lista() {
        List<Character> ret = new ArrayList<>();

        ret.add(AUTO_DISPONIBLE);
        ret.add(AUTO_RENTADO);
        ret.add(AUTO_EN_REVISION);

        return ret;
    }
}
