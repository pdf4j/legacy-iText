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

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

public class Chap0501 {

	public static void main(String[] args) {

		System.out.println("Chapter 5 example 1: My first table");

		// step 1: creation of a document-object
		Document document = new Document();

		try {

			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file

			PdfWriter.getInstance(document, new FileOutputStream("Chap0501.pdf"));

			// step 3: we open the document
			document.open();

			// step 4: we create a table and add it to the document
			Table table = new Table(3);
			table.setBorderWidth(1); 
			table.setBorderColor(new Color(0, 0, 255)); 
			table.setCellpadding(5); 
			table.setCellspacing(5); 
			Cell cell = new Cell("header"); 
			cell.setHeader(true); 
			cell.setColspan(3); 
			table.addCell(cell); 
			table.endHeaders(); 
			cell = new Cell("example cell with colspan 1 and rowspan 2"); 
			cell.setRowspan(2); 
			cell.setBorderColor(new Color(255, 0, 0)); 
			table.addCell(cell); 
			table.addCell("1.1"); 
			table.addCell("2.1"); 
			table.addCell("1.2"); 
			table.addCell("2.2"); 
			table.addCell("cell test1"); 
			cell = new Cell("big cell"); 
			cell.setRowspan(2); 
			cell.setColspan(2); 
			table.addCell(cell);
			table.addCell("cell test2");
			document.add(table);
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