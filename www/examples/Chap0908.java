/*
 * $Id$
 * $Name$
 *
 * This code is free software. It may only be copied or modified
 * if you include the following copyright notice:
 *
 * --> Copyright 2001 by Bruno Lowagie <--
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://www.lowagie.com/iText/tutorial/
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext@lowagie.com
 */
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfBarcode;
import com.lowagie.text.pdf.BaseFont;
public class Chap0908 {
    
    public static void main(String[] args) {
        
        System.out.println("Chapter 9 example 8: Barcodes with ttf");
        
        // step 1: creation of a document-object
        Document document = new Document();
        
        try {
            
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            
            PdfWriter.getInstance(document, new FileOutputStream("Chap0908.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4: we add content to the document
            document.add(new Paragraph(40, new PdfBarcode("c:\\winnt\\fonts\\CODE39.TTF", PdfBarcode.CODE39, 36, "0123456789")));
            document.add(new Paragraph(50, new PdfBarcode("c:\\winnt\\fonts\\UPC-A.TTF", PdfBarcode.UPCA, 36, "203489343822")));
            document.add(new Paragraph(60, new PdfBarcode("c:\\winnt\\fonts\\UPC-AHH.TTF", PdfBarcode.UPCA, 36, "203489343822")));
            document.add(new Paragraph(60, new PdfBarcode("c:\\winnt\\fonts\\EAN-13.TTF", PdfBarcode.EAN13, 36, "8010012529736")));
            document.add(new Paragraph(60, new PdfBarcode("c:\\winnt\\fonts\\EAN-13HH.TTF", PdfBarcode.EAN13, 48, "5400111151441")));
            document.add(new Paragraph(90, new PdfBarcode("c:\\winnt\\fonts\\EAN-13B.TTF", PdfBarcode.EAN13, 60, "8010012529736")));
            document.add(new Paragraph(90, new PdfBarcode("c:\\winnt\\fonts\\EAN-13BH.TTF", PdfBarcode.EAN13, 72, "5400111151441")));
            document.add(new Paragraph(60, new PdfBarcode("c:\\winnt\\fonts\\I2OF5.TTF", PdfBarcode.INTERLEAVED_2_OF_5, 48, "12345678900987654321")));
            document.add(new Paragraph(60, new PdfBarcode("c:\\winnt\\fonts\\I2OF5NT.TTF", PdfBarcode.INTERLEAVED_2_OF_5, 48, "2345678900987654321")));       
            // measuring the length of a string
            BaseFont bf = BaseFont.createFont("c:\\winnt\\fonts\\CODE39.TTF", BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            float points = bf.getWidthPoint("0123456789", 36f);
            float inches = points / 72f;
            float cm = inches * 2.54f;
            System.out.println("points: " + points + "; inches: " + inches + "; cm: " + cm);
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        
        // step 5: we close the document
        document.close();
    }
}