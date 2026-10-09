package com.empresa.flota.dao;

import com.empresa.flota.model.Vehiculo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class GestorReportesXML {

    /**
     * Genera el informe XML mediante DOM.
     */
    public static void generarXML(
            ArrayList<Vehiculo> vehiculos,
            String ruta) {

        try {

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document document = builder.newDocument();

            Element flota =
                    document.createElement("flota");

            document.appendChild(flota);

            for (Vehiculo vehiculo : vehiculos) {

                Element elementoVehiculo =
                        document.createElement("vehiculo");

                Element matricula =
                        document.createElement("matricula");
                matricula.setTextContent(
                        vehiculo.getMatricula());
                elementoVehiculo.appendChild(matricula);

                Element marca =
                        document.createElement("marca");
                marca.setTextContent(
                        vehiculo.getMarca());
                elementoVehiculo.appendChild(marca);

                Element modelo =
                        document.createElement("modelo");
                modelo.setTextContent(
                        vehiculo.getModelo());
                elementoVehiculo.appendChild(modelo);

                Element anio =
                        document.createElement("anio");
                anio.setTextContent(
                        String.valueOf(vehiculo.getAnio()));
                elementoVehiculo.appendChild(anio);

                Element kilometraje =
                        document.createElement("kilometraje");
                kilometraje.setTextContent(
                        String.valueOf(vehiculo.getKilometraje()));
                elementoVehiculo.appendChild(kilometraje);

                Element categoria =
                        document.createElement("categoria");
                categoria.setTextContent(
                        vehiculo.getCategoriaMantenimiento());
                elementoVehiculo.appendChild(categoria);

                flota.appendChild(elementoVehiculo);
            }

            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();

            Transformer transformer =
                    transformerFactory.newTransformer();

            transformer.setOutputProperty(
                    OutputKeys.INDENT, "yes");

            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount",
                    "4");

            DOMSource source =
                    new DOMSource(document);

            StreamResult result =
                    new StreamResult(new File(ruta));

            transformer.transform(source, result);

            System.out.println(
                    "Informe XML generado correctamente.");

        } catch (ParserConfigurationException |
                 TransformerException e) {

            System.out.println(
                    "Error al generar el XML: "
                    + e.getMessage());
        }
    }

    /**
     * Lee el XML mediante SAX y obtiene
     * las categorías de mantenimiento únicas.
     */
    public static Set<String> leerXML(String ruta) {

        Set<String> categorias =
                new HashSet<>();

        try {

            var reader =
                    XMLReaderFactory.createXMLReader();

            DefaultHandler handler =
                    new DefaultHandler() {

                private String elementoActual = "";

                @Override
                public void startElement(
                        String uri,
                        String localName,
                        String qName,
                        Attributes attributes) {

                    elementoActual = qName;
                }

                @Override
                public void characters(
                        char[] ch,
                        int start,
                        int length) {

                    String texto =
                            new String(ch, start, length).trim();

                    if (elementoActual.equals("categoria")
                            && !texto.isEmpty()) {

                        categorias.add(texto);
                    }
                }

                @Override
                public void endElement(
                        String uri,
                        String localName,
                        String qName) {

                    elementoActual = "";
                }
            };

            reader.setContentHandler(handler);

            reader.parse(ruta);

            System.out.println(
                    "XML leído correctamente.");

        } catch (SAXException | IOException e) {

            System.out.println(
                    "Error al leer el XML: "
                    + e.getMessage());
        }

        return categorias;
    }
}
