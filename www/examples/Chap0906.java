/*
 * $Id$
 * $Name$
 *
 * This code is free software. It may only be copied or modified
 * if you include the following copyright notice:
 *
 * --> Copyright 2001 by Paulo Soares, Bruno Lowagie <--
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

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class Chap0906 {
    
    public static void main(String[] args) {
        
        System.out.println("Chapter 9 example 6: FontFactory");
        
        FontFactory.register("c:\\winnt\\fonts\\comicbd.ttf");
        FontFactory.register("c:\\winnt\\fonts\\comic.ttf");
        FontFactory.register("c:\\winnt\\fonts\\msgothic.ttc");
        System.out.println("These fonts were registered at the FontFactory");
        for (Iterator i = FontFactory.getRegisteredFonts().iterator(); i.hasNext(); ) {
            System.out.println((String) i.next());
        }
        
        // step 1: creation of a document-object
        Document document = new Document();
        
        try {
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            PdfWriter.getInstance(document, new FileOutputStream("Chap0906.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4: we add content to the document
            Font font0 = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.WINANSI, 12);
            String text0 = "This is the quite popular built in font '" + BaseFont.HELVETICA + "'.";
            document.add(new Paragraph(text0, font0));
            Font font1 = FontFactory.getFont("ComicSansMS", BaseFont.WINANSI, 12);
            String text1 = "This is the quite popular True Type font 'ComicSansMS'.";
            document.add(new Paragraph(text1, font1));
            Font font2 = FontFactory.getFont("ComicSansMS-Bold", BaseFont.WINANSI, 12);
            String text2 = "This is the quite popular True Type font 'ComicSansMS-Bold'.";
            document.add(new Paragraph(text2, font2));
            Font font3= FontFactory.getFont("MS-PGothic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
            String text3 = "\u5951\u7d04\u8005\u4f4f\u6240\u30e9\u30a4\u30f3\uff11";
            document.add(new Paragraph(text3, font3));
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
