/*
 * $Id$
 * $Name$
 *
 * This code is free software. It may only be copied or modified
 * if you include the following copyright notice:
 *
 * --> Copyright 2001-2005 by Paulo Soares and Bruno Lowagie <--
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://www.lowagie.com/iText/tutorial/
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */
package com.lowagie.examples.objects.images;

import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
/**
 * Using the java.awt.Image object.
 */
public class AwtImage {
    /**
     * Uses a java.awt.Image object to construct a com.lowagie.text.Image object.
     * @param args no arguments needed
     */
    public static void main(String[] args) {
        
        System.out.println("Images using java.awt.image");
        
        // step 1: creation of a document-object
        Document document = new Document();
        
        try {
            
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("awt_image.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4: we add content to the document
            for (int i = 0; i < 300; i++) {
                document.add(new Phrase("Who is this? "));
            }
            PdfContentByte cb = writer.getDirectContent();
            Image image = Image.getInstance(Toolkit.getDefaultToolkit().createImage("H.gif"), null);
            image.setAbsolutePosition(100, 500);
            cb.addImage(image);
            Image img = Image.getInstance("H.gif");
            image.setAbsolutePosition(100, 200);
            cb.addImage(image);
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        
        // step 5: we close the document
        document.close();
        System.exit(0);
    }
}
