/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.util;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Java
 */
public class ObjetoGuardarBlobEnFicheroTemporal {

    public static String guardarBlobEnFicheroTemporal(byte[] bytes, String nombreArchivo) {
        String ubicacionImagen = null;
        ServletContext servletContext = (ServletContext) FacesContext
                .getCurrentInstance().getExternalContext().getContext();

        char[] context = servletContext.getRealPath(" ").toCharArray();
        String pat = "";

        for (int i = 0; i < context.length; i++) {
            if (context[i] == 't' && context[i + 1] == 'a' && context[i + 2] == 'r' ) {
                break;
            }
            pat += Character.toString(context[i]);
        }

        String path = pat + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "resources" + File.separatorChar + "imagenes" + File.separatorChar + nombreArchivo;

        try {

            FileUtils.writeByteArrayToFile(new File(path), bytes);

            ubicacionImagen = "imagenes/" + nombreArchivo;
        } catch (Exception e) {
            Logger.getLogger(ObjetoGuardarBlobEnFicheroTemporal.class.getName()).log(Level.SEVERE, null, e);
        }
        return ubicacionImagen;
    }

}
