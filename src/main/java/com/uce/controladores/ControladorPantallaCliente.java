/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.controladores;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.uce.entidades.Clientes;
import com.uce.entidades.Ticket;
import com.uce.globales.Constantes;
import com.uce.persistencia.ejb.TicketFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Java
 */
@Named("controladorPantallaCliente")
@ViewScoped
public class ControladorPantallaCliente extends ControladorBase implements Serializable {

    private static final Font FUENTE_NORMAL = new Font(Font.FontFamily.HELVETICA, 11);
    private static final Font FUENTE_NEGRITA = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
    private static final Font FUENTE_SUBRAYADA = new Font(Font.FontFamily.HELVETICA, 11, Font.UNDERLINE);
    private static final double PORCENTAJE_IVA = 0.12d;

    @Inject
    private ControladorSesion controladorSesion;
    @EJB
    private TicketFacade ticketFacade;

    private List<Ticket> listaAutosRentadosRecientemente;
    private Ticket ticketSeleccionado;
    private Clientes cliente;
    private Date rentasDesde;
    private Date rentasHasta;
    private String rutaArchivo;
    private String nombreArchivo;

    @PostConstruct
    public void inicializar() {
        cliente = controladorSesion.getClienteSeleccionado();
        rentasDesde = new Date();
        rentasHasta = new Date();
    }

    public void consultarAutosRentados() {
        Long[] clientes = new Long[1];
        clientes[0] = cliente.getCedula();
        listaAutosRentadosRecientemente = ticketFacade.obtenerReporteTickets(rentasDesde, rentasHasta, null, null, clientes);
        nombreArchivo = "Renta_de_auto" + ticketSeleccionado.getIdTicket() + ".pdf";
        rutaArchivo = Constantes.RUTA_TICKETS_PDF + nombreArchivo;
    }

    public void generarPDF() {
        //Generar el archivo PDF
        try {
            Paragraph parrafoHtml;

            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
            writer.setPageEvent(new Cabecera(ticketSeleccionado));
            document.open();

            document.addTitle("Ticket");
            document.addAuthor("Rent a Car S.A.");

            NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
            DecimalFormat formatoDolares = (DecimalFormat) nf;
            formatoDolares.setMinimumFractionDigits(2);
            formatoDolares.setMaximumFractionDigits(2);
            formatoDolares.setDecimalSeparatorAlwaysShown(true);
            formatoDolares.setGroupingUsed(true);

            document.add(new Paragraph("Quito, " + fechaTexto(new Date()), FUENTE_NORMAL));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Señor/a", FUENTE_NORMAL));
            document.add(new Paragraph(ticketSeleccionado.getCliente().getNombreCliente(), FUENTE_NEGRITA));
            document.add(new Paragraph("ATTN: " + ticketSeleccionado.getEmpleado().getNombreEmpleado(), FUENTE_NORMAL));
            document.add(new Paragraph("Dirección: " + ticketSeleccionado.getCliente().getDireccionCliente(), FUENTE_NORMAL));
            document.add(Chunk.NEWLINE);

            //Introducción
            try {
                parrafoHtml = new Paragraph();
                agregarHtml(document, "<p>Estimado cliente, le presentamos el detalle de la renta del auto con placa N°:" + ticketSeleccionado.getVehiculoPlaca().getNumeroPlaca().toString() + "</p>");
            } catch (IOException ioe) {
                Logger.getLogger(ControladorPantallaCliente.class.getName()).log(Level.SEVERE, null, ioe);
            }
            document.add(Chunk.NEWLINE);

            //Agregado del detalle
            PdfPTable tablaDetalle = new PdfPTable(1);
            tablaDetalle.setWidthPercentage(100);

            PdfPCell celdaDetalle = new PdfPCell();
            celdaDetalle.setBorder(Rectangle.TOP);
            celdaDetalle.addElement(Chunk.NEWLINE);

            celdaDetalle.addElement(new Paragraph("Placa " + ticketSeleccionado.getVehiculoPlaca().getNumeroPlaca(), FUENTE_NORMAL));

            PdfPTable tablaAuto = new PdfPTable(2);
            tablaAuto.setWidthPercentage(100);
            tablaAuto.setWidths(new float[]{15f, 85f});

            //Celda imagen
            PdfPCell celdaImagen = new PdfPCell();
            celdaImagen.setBorder(Rectangle.NO_BORDER);
            celdaImagen.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

            File archivoImagen = new File(ticketSeleccionado.getVehiculoPlaca().getImagenVehiculo());
            try {
                Image imagen = Image.getInstance(ticketSeleccionado.getVehiculoPlaca().getImagenVehiculo());

                //Escalar la imagen
                final float MAXIMO_ANCHO_ALTO = 50f;
                float ancho;
                float alto;
                if (imagen.getWidth() >= imagen.getHeight()) {
                    ancho = MAXIMO_ANCHO_ALTO;
                    alto = MAXIMO_ANCHO_ALTO * imagen.getHeight() / imagen.getWidth();
                } else {
                    alto = MAXIMO_ANCHO_ALTO;
                    ancho = MAXIMO_ANCHO_ALTO * imagen.getWidth() / imagen.getHeight();
                }
                imagen.scaleAbsolute(ancho, alto);

                // Colocar la imagen en la celda
                celdaImagen.addElement(imagen);
            } catch (BadElementException ex) {
                Logger.getLogger(ControladorPantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControladorPantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            tablaAuto.addCell(celdaImagen);

            //Celda de valores
            PdfPCell celdaValores = new PdfPCell();
            celdaValores.setBorder(Rectangle.NO_BORDER);

            PdfPTable tablaValores = new PdfPTable(4);
            tablaValores.setWidthPercentage(100);
            tablaValores.setWidths(new float[]{18f, 18f, 54f, 18f});

            // Títulos
            tablaValores.addCell(celda("Cantidad", FUENTE_NEGRITA, PdfPCell.ALIGN_CENTER));
            tablaValores.addCell(celda("V. Unitario", FUENTE_NEGRITA, PdfPCell.ALIGN_CENTER));
            tablaValores.addCell(celda("Total", FUENTE_NEGRITA, PdfPCell.ALIGN_RIGHT));

            tablaValores.addCell(celda(Integer.toString(1), FUENTE_NORMAL, PdfPCell.ALIGN_CENTER));
            tablaValores.addCell(celda(formatearDecimales(ticketSeleccionado.getPagoAlquiler().doubleValue(), 2), FUENTE_NORMAL, PdfPCell.ALIGN_CENTER));

            Integer dias = (int) ((ticketSeleccionado.getFechaSalida().getTime() - ticketSeleccionado.getFechaEntrega().getTime())) / 86400000;

            Double total = dias * ticketSeleccionado.getPagoAlquiler().doubleValue();
            tablaValores.addCell(celda(formatearDecimales(total, 2), FUENTE_NORMAL, PdfPCell.ALIGN_RIGHT));

            //Agregar la celda de valores
            celdaValores.addElement(tablaValores);

            tablaAuto.addCell(celdaValores);

            celdaDetalle.addElement(tablaAuto);

            document.add(tablaAuto);

            //Totales
            PdfPTable tablaContenedorTotales = new PdfPTable(1);
            tablaContenedorTotales.setWidthPercentage(100);
            PdfPCell celdaContenedorTotales = new PdfPCell();
            celdaContenedorTotales.setBorder(PdfPCell.TOP);

            PdfPTable tablaTotales = new PdfPTable(2);
            tablaTotales.setWidthPercentage(100);
            tablaTotales.setWidths(new float[]{85f, 15f});
            tablaTotales.addCell(celda("SUBTOTAL:", FUENTE_NEGRITA, PdfPCell.ALIGN_RIGHT));
            tablaTotales.addCell(celda(formatearDecimales(total, 2), FUENTE_NEGRITA, PdfPCell.ALIGN_RIGHT));
            tablaTotales.addCell(celda("IVA:", FUENTE_NEGRITA, PdfPCell.ALIGN_RIGHT));
            double iva = PORCENTAJE_IVA * total;
            tablaTotales.addCell(celda(formatearDecimales(iva, 2), FUENTE_NEGRITA, PdfPCell.ALIGN_RIGHT));
            tablaTotales.addCell(celda("TOTAL:", FUENTE_NEGRITA, PdfPCell.ALIGN_RIGHT));
            tablaTotales.addCell(celda(formatearDecimales(total + iva, 2), FUENTE_NEGRITA, PdfPCell.ALIGN_RIGHT));

            // Cierre
            document.add(Chunk.NEWLINE);
            try {
                agregarHtml(document, "<p>Muchas gracias por preferirnos</p>");
            } catch (IOException ex) {
                Logger.getLogger(ControladorPantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            document.close();

        } catch (Exception e) {
            Logger.getLogger(ControladorPantallaCliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    class Cabecera extends PdfPageEventHelper {

        private final Ticket ticket;

        public Cabecera(Ticket ticket) {
            this.ticket = ticket;
        }

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            try {
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                PdfPCell celdaTitulo = new PdfPCell(new Phrase("Ticket" + ticket.getIdTicket().toString()));
                celdaTitulo.setBorder(Rectangle.NO_BORDER);
                table.addCell(celdaTitulo);

                try {
                    Image logo = Image.getInstance(Constantes.RUTA_LOGO_RENTACAR);
                    logo.scalePercent(5f);
                    PdfPCell celdaLogo = new PdfPCell(logo, false);
                    celdaLogo.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                    celdaLogo.setBorder(Rectangle.NO_BORDER);
                    table.addCell(celdaLogo);
                } catch (BadElementException ex) {
                    Logger.getLogger(ControladorPantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorPantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                document.add(table);
            } catch (DocumentException ex) {
                Logger.getLogger(ControladorPantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String fechaTexto(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        String cadena = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        cadena += " de ";
        cadena += obtenerMesDesdeNumeroCalendario(cal.get(Calendar.MONTH));
        cadena += " de ";
        cadena += Integer.toString(cal.get(Calendar.YEAR));
        return cadena;
    }

    private String obtenerMesDesdeNumeroCalendario(int n) {
        switch (n) {
            case Calendar.JANUARY: {
                return "enero";
            }
            case Calendar.FEBRUARY: {
                return "febrero";
            }
            case Calendar.MARCH: {
                return "marzo";
            }
            case Calendar.APRIL: {
                return "abril";
            }
            case Calendar.MAY: {
                return "mayo";
            }
            case Calendar.JUNE: {
                return "junio";
            }
            case Calendar.JULY: {
                return "julio";
            }
            case Calendar.AUGUST: {
                return "agosto";
            }
            case Calendar.SEPTEMBER: {
                return "septiembre";
            }
            case Calendar.OCTOBER: {
                return "octubre";
            }
            case Calendar.NOVEMBER: {
                return "noviembre";
            }
            case Calendar.DECEMBER: {
                return "diciembre";
            }
        }
        return "";
    }

    private void agregarHtml(final Document document, String html) throws IOException {
        html = html.replace("<br>", "<br />");
        ElementList list = XMLWorkerHelper.parseToElementList(html, null);
        for (Element element : list) {
            try {
                document.add(element);
            } catch (DocumentException ex) {
                Logger.getLogger(ControladorPantallaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private PdfPCell celda(String texto, Font fuente, int alineacion) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setHorizontalAlignment(alineacion);
        celda.setBorder(Rectangle.NO_BORDER);
        return celda;
    }

    private String formatearDecimales(double valor, int numeroDecimales) {
        DecimalFormat formato = new DecimalFormat();
        formato.setMaximumFractionDigits(numeroDecimales);
        formato.setGroupingUsed(true);
        formato.setMinimumFractionDigits(numeroDecimales);
        formato.setMinimumIntegerDigits(1);
        return formato.format(valor);
    }
    

    /*
     * Getter´s y Setter´s
     */
    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Date getRentasDesde() {
        return rentasDesde;
    }

    public void setRentasDesde(Date rentasDesde) {
        this.rentasDesde = rentasDesde;
    }

    public Date getRentasHasta() {
        return rentasHasta;
    }

    public void setRentasHasta(Date rentasHasta) {
        this.rentasHasta = rentasHasta;
    }

    public List<Ticket> getListaAutosRentadosRecientemente() {
        return listaAutosRentadosRecientemente;
    }

    public void setListaAutosRentadosRecientemente(List<Ticket> listaAutosRentadosRecientemente) {
        this.listaAutosRentadosRecientemente = listaAutosRentadosRecientemente;
    }

    public Ticket getTicketSeleccionado() {
        return ticketSeleccionado;
    }

    public void setTicketSeleccionado(Ticket ticketSeleccionado) {
        this.ticketSeleccionado = ticketSeleccionado;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

}
