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
 * itext-questions@lists.sourceforge.net
 */

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

public class Chap0607 {
    
    public static void main(String[] args) {
        
        System.out.println("Chapter 6 example 7: Scaling an Image");
        
        // step 1: creation of a document-object
        Document document = new Document();
        
        try {
            
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            
            PdfWriter.getInstance(document, new FileOutputStream("Chap0607.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4: we add content
            Image jpg1 = Image.getInstance("myKids.jpg");
            jpg1.scaleAbsolute(97, 101);
            document.add(new Paragraph("scaleAbsolute(97, 101)"));
            document.add(jpg1);
            Image jpg2 = Image.getInstance("myKids.jpg");
            jpg2.scalePercent(50);
            document.add(new Paragraph("scalePercent(50)"));
            document.add(jpg2);
            Image jpg3 = Image.getInstance("myKids.jpg");
            jpg3.scaleAbsolute(194, 101);
            document.add(new Paragraph("scaleAbsolute(194, 101)"));
            document.add(jpg3);
            Image jpg4 = Image.getInstance("myKids.jpg");
            jpg4.scalePercent(100, 50);
            document.add(new Paragraph("scalePercent(100, 50)"));
            document.add(jpg4);
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
