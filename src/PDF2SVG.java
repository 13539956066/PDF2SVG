/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import org.apache.batik.dom.*;
import org.apache.batik.svggen.*;
import org.w3c.dom.Document;
import java.awt.print.*;
import org.w3c.dom.DOMImplementation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.*;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import java.lang.Iterable;
/**
 *
 * @author Ahmed Assem
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PrinterException {

        try {
             String svgDir = args[1];
            //String svgDir = "D:\\test\\javaFirstApp\\svgOut";
           // File pdfFile = new File("D:\\test\\javaFirstApp\\intersection.pdf");
            File pdfFile = new File(args[0]);
            PDDocument document = PDDocument.load(pdfFile);
            DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
            //DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();

// Create an instance of org.w3c.dom.Document.
            String svgNS = "http://www.w3.org/2000/svg";
            Document svgDocument = domImpl.createDocument(svgNS, "svg", null);
            SVGGraphics2D svgGenerator = new SVGGraphics2D(svgDocument);

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                String svgFName = svgDir + "\\" + "page" + i + ".svg";
                (new File(svgFName)).createNewFile();
                Printable page = new PDFPrintable(document);

                page.print(svgGenerator, new PDFPageable(document).getPageFormat(i), i);
                svgGenerator.stream(svgFName);

            }
        } catch (IOException e) {
            // Do something here
        }
    }
}
