/*
 * $Id$
 * $Name$
 *
 * Copyright 2001, 2002 by Bruno Lowagie.
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
 * LGPL license (the �GNU LIBRARY GENERAL PUBLIC LICENSE�), in which case the
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

package com.lowagie.text.pdf;

import java.net.URL;

/**
 * A <CODE>PdfAction</CODE> defines an action that can be triggered from a PDF file.
 *
 * @see		PdfDictionary
 *
 * @author  bruno@lowagie.com
 */

public class PdfAction extends PdfDictionary {
    
    /** A named action to go to the first page.
     */    
    public final static int FIRSTPAGE = 1;
    /** A named action to go to the previous page.
     */    
    public final static int PREVPAGE = 2;
    /** A named action to go to the next page.
     */    
    public final static int NEXTPAGE = 3;
    /** A named action to go to the last page.
     */    
    public final static int LASTPAGE = 4;
    // constructors
    
/**
 * Constructs a new <CODE>PdfAction</CODE> of Subtype URI.
 *
 * @param url the Url to go to
 */
    
    public PdfAction(URL url) {
        super(PdfName.ACTION);
        put(PdfName.S, PdfName.URI);
        put(PdfName.URI, new PdfString(url.toExternalForm()));
    }
    
/**
 * Constructs a new <CODE>PdfAction</CODE> of Subtype URI.
 *
 * @param url the url to go to
 */
    
    public PdfAction(String url) {
        super(PdfName.ACTION);
        put(PdfName.S, PdfName.URI);
        put(PdfName.URI, new PdfString(url));
    }
    
/**
 * Constructs a new <CODE>PdfAction</CODE> of Subtype GoTo.
 * @param destination the destination to go to
 */
    
    PdfAction(PdfIndirectReference destination) {
        super(PdfName.ACTION);
        put(PdfName.S, PdfName.GOTO);
        put(PdfName.D, destination);
    }
  
/**
 * Constructs a new <CODE>PdfAction</CODE> of Subtype GoToR.
 * @param filename the file name to go to
 * @param name the named destination to go to
 */
    
    public PdfAction(String filename, String name)
    {
        super(PdfName.ACTION);
        put(PdfName.S, PdfName.GOTOR);
        put(PdfName.F, new PdfString(filename));
        put(PdfName.D, new PdfString(name));
    }
    
/**
 * Constructs a new <CODE>PdfAction</CODE> of Subtype GoToR.
 * @param filename the file name to go to
 * @param page the page destination to go to
 */
    
    public PdfAction(String filename, int page)
    {
        super(PdfName.ACTION);
        put(PdfName.S, PdfName.GOTOR);
        put(PdfName.F, new PdfString(filename));
        put(PdfName.D, new PdfLiteral("[" + (page - 1) + " /FitH 10000]"));
    }
    
    /** Implements name actions. The action can be FIRSTPAGE, LASTPAGE,
     * NEXTPAGE and PREVPAGE.
     * @param named the named action
     */    
    public PdfAction(int named) {
        super(PdfName.ACTION);
        put(PdfName.S, PdfName.NAMED);
        switch (named) {
            case FIRSTPAGE:
                put(PdfName.N, PdfName.FIRSTPAGE);
                break;
            case LASTPAGE:
                put(PdfName.N, PdfName.LASTPAGE);
                break;
            case NEXTPAGE:
                put(PdfName.N, PdfName.NEXTPAGE);
                break;
            case PREVPAGE:
                put(PdfName.N, PdfName.PREVPAGE);
                break;
            default:
                throw new RuntimeException("Invalid named action.");
        }
    }
    
    /** Launchs an application or a document.
     * @param application the application to be launched or the document to be opened or printed.
     * @param parameters (Windows-specific) A parameter string to be passed to the application.
     * It can be <CODE>null</CODE>.
     * @param operation (Windows-specific) the operation to perform: "open" - Open a document,
     * "print" - Print a document.
     * It can be <CODE>null</CODE>.
     * @param defaultDir (Windows-specific) the default directory in standard DOS syntax.
     * It can be <CODE>null</CODE>.
     */    
    public PdfAction(String application, String parameters, String operation, String defaultDir) {
        super(PdfName.ACTION);
        put(PdfName.S, PdfName.LAUNCH);
        if (parameters == null && operation == null && defaultDir == null)
            put(PdfName.F, new PdfString(application));
        else {
            PdfDictionary dic = new PdfDictionary();
            dic.put(PdfName.F, new PdfString(application));
            if (parameters != null)
                dic.put(PdfName.P, new PdfString(parameters));
            if (operation != null)
                dic.put(PdfName.O, new PdfString(operation));
            if (defaultDir != null)
                dic.put(PdfName.D, new PdfString(defaultDir));
            put(PdfName.WIN, dic);
        }
    }
}
