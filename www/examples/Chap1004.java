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
 * ir-arch Bruno Lowagie,
 * Adolf Baeyensstraat 121
 * 9040 Sint-Amandsberg
 * BELGIUM
 * tel. +32 (0)9 228.10.97
 * bruno@lowagie.com
 */

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;

public class Chap1004 {
    
    public static void main(String[] args) {
        
        System.out.println("Chapter 10 example 4: Templates");
        
        // step 1: creation of a document-object
        Document document = new Document();
        
        try {
            
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Chap1004.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4: we grab the ContentByte and do some stuff with it
            PdfContentByte cb = writer.getDirectContent();
            
            // we create a PdfTemplate
            PdfTemplate template = cb.createTemplate(50, 50);
            BaseFont bf = BaseFont.createFont("Helvetica", "Cp1252", false);
            // we add a number of pages
            int i;
            for (i = 1; i < 5; i++) {
                String text = "Page " + writer.getPageNumber() + " of ";
                float len = bf.getWidthPoint(text, 12);
                cb.beginText();
                cb.setFontAndSize(bf, 12);
                cb.setTextMatrix(280, 40);
                cb.showText(text);
                cb.endText();
                cb.addTemplate(template, 280 + len, 40);
                document.newPage();
            }
            template.beginText();
            template.setFontAndSize(bf, 12);
            template.showText(String.valueOf(writer.getPageNumber() - 1));
            template.endText();
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
