/*
 * $Id$
 * $Name$
 *
 * Copyright 2005 by Bruno Lowagie.
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the License.
 *
 * The Original Code is 'iText, a free JAVA-PDF library'.
 *
 * The Initial Developer of the Original Code is Bruno Lowagie. Portions created by
 * the Initial Developer are Copyright (C) 1999, 2000, 2001, 2002 by Bruno Lowagie.
 * All Rights Reserved.
 * Co-Developer of the code is Paulo Soares. Portions created by the Co-Developer
 * are Copyright (C) 2000, 2001, 2002 by Paulo Soares. All Rights Reserved.
 *
 * Contributor(s): all the names of the contributors are added in the source code
 * where applicable.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * LGPL license (the "GNU LIBRARY GENERAL PUBLIC LICENSE"), in which case the
 * provisions of LGPL are applicable instead of those above.  If you wish to
 * allow use of your version of this file only under the terms of the LGPL
 * License and not to allow others to use your version of this file under
 * the MPL, indicate your decision by deleting the provisions above and
 * replace them with the notice and other provisions required by the LGPL.
 * If you do not delete the provisions above, a recipient may use your version
 * of this file under either the MPL or the GNU LIBRARY GENERAL PUBLIC LICENSE.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the MPL as stated above or under the terms of the GNU
 * Library General Public License as published by the Free Software Foundation;
 * either version 2 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Library general Public License for more
 * details.
 *
 * If you didn't download this code from the following link, you should check if
 * you aren't using an obsolete version:
 * http://www.lowagie.com/iText/
 */
package com.lowagie.tools.plugins;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;
import com.lowagie.tools.arguments.FileArgument;
import com.lowagie.tools.arguments.ImageFilter;
import com.lowagie.tools.arguments.PdfFilter;
import com.lowagie.tools.arguments.ToolArgument;

/**
 * Converts a Tiff file to a PDF file.
 */
public class Tiff2Pdf extends AbstractTool {
	/**
	 * Constructs a Tiff2Pdf object.
	 */
	public Tiff2Pdf() {
		arguments.add(new FileArgument(this, "srcfile", "The file you want to encrypt", false, new ImageFilter(false, false, false, false, false, true)));
		arguments.add(new FileArgument(this, "destfile", "The file to which the encrypted PDF has to be written", true, new PdfFilter()));
	}

	/**
	 * @see com.lowagie.tools.plugins.AbstractTool#createFrame()
	 */
	protected void createFrame() {
		internalFrame = new JInternalFrame("Tiff2Pdf", true, true, true);
		internalFrame.setSize(300, 80);
		internalFrame.setJMenuBar(getMenubar());
	}

	/**
	 * @see com.lowagie.tools.plugins.AbstractTool#execute()
	 */
	public void execute() {
		try {
			if (getValue("srcfile") == null) throw new InstantiationException("You need to choose a sourcefile");
			File tiff_file = (File)getValue("srcfile");
			if (getValue("destfile") == null) throw new InstantiationException("You need to choose a destination file");
			File pdf_file = (File)getValue("destfile");
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_file));
			int pages = 0;
			document.open();
			PdfContentByte cb = writer.getDirectContent();
            RandomAccessFileOrArray ra = null;
            int comps = 0;
            ra = new RandomAccessFileOrArray(tiff_file.getAbsolutePath());
            comps = TiffImage.getNumberOfPages(ra);
            for (int c = 0; c < comps; ++c) {
                Image img = TiffImage.getTiffImage(ra, c + 1);
                if (img != null) {
                    if (img.scaledWidth() > 500 || img.scaledHeight() > 700) {
                        img.scaleToFit(500, 700);
                    }
                    img.setAbsolutePosition(20, 20);
                    document.add(new Paragraph(tiff_file + " - page " + (c + 1)));
                    cb.addImage(img);
                    document.newPage();
                    ++pages;
                }
            }
            ra.close();
            document.close();
		} catch (Exception e) {
        	JOptionPane.showMessageDialog(internalFrame,
        		    e.getMessage(),
        		    e.getClass().getName(),
        		    JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
		}
	}

	/**
	 * @see com.lowagie.tools.plugins.AbstractTool#valueHasChanged(com.lowagie.tools.arguments.ToolArgument)
	 */
	public void valueHasChanged(ToolArgument arg) {
		if (internalFrame == null) {
			// if the internal frame is null, the tool was called from the commandline
			return;
		}
		// represent the changes of the argument in the internal frame
	}

	
    /**
     * Converts a tiff file to PDF.
     * @param args
     */
	public static void main(String[] args) {
    	Tiff2Pdf tool = new Tiff2Pdf();
    	if (args.length < 2) {
    		System.err.println(tool.getUsage());
    	}
    	tool.setArguments(args);
        tool.execute();
	}
}