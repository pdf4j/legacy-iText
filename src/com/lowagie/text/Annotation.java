/*
 * $Id$
 * $Name$
 *
 * Copyright 1999, 2000, 2001, 2002 by Bruno Lowagie.
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

package com.lowagie.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

/**
 * An <CODE>Annotation</CODE> is a little note that can be added to a page
 * on a document.
 *
 * @see		Element
 * @see		Anchor
 */

public class Annotation implements Element {
    
    // membervariables
    
/** This is the title of the <CODE>Annotation</CODE>. */
    private String title;
    
/** This is the content of the <CODE>Annotation</CODE>. */
    private String text;
    
    // constructors
    
/**
 * Constructs an <CODE>Annotation</CODE> with a certain title and some text.
 *
 * @param	title	the title of the annotation
 * @param	text	the content of the annotation
 */
    
    public Annotation(String title, String text) {
        this.title = title;
        this.text = text;
    }
    
/**
 * Returns an <CODE>Annotation</CODE> that has been constructed taking in account
 * the value of some <VAR>attributes</VAR>.
 *
 * @param	attributes		Some attributes
 * @return	an <CODE>Annotation</CODE>
 */
    
    public Annotation(Properties attributes) {
        title = attributes.getProperty(ElementTags.TITLE);
        text = attributes.getProperty(ElementTags.CONTENT);
        if (title == null) {
            title = "";
        }
        if (text == null) {
            text = "";
        }
    }
    
    // implementation of the Element-methods
    
/**
 * Gets the type of the text element.
 *
 * @return	a type
 */
    
    public final int type() {
        return Element.ANNOTATION;
    }
    
    // methods
    
/**
 * Processes the element by adding it (or the different parts) to an
 * <CODE>ElementListener</CODE>.
 *
 * @param	listener 	an <CODE>ElementListener</CODE>
 * @return	<CODE>true</CODE> if the element was processed successfully
 */
    
    public boolean process(ElementListener listener) {
        try {
            return listener.add(this);
        }
        catch(DocumentException de) {
            return false;
        }
    }
    
/**
 * Gets all the chunks in this element.
 *
 * @return	an <CODE>ArrayList</CODE>
 */
    
    public ArrayList getChunks() {
        return new ArrayList();
    }
    
    // methods to retrieve information
    
/**
 * Returns the title of this <CODE>Annotation</CODE>.
 *
 * @return	a name
 */
    
    public final String title() {
        return title;
    }
    
/**
 * Gets the content of this <CODE>Annotation</CODE>.
 *
 * @return	a reference
 */
    
    public final String content() {
        return text;
    }
    
/**
 * Checks if a given tag corresponds with this object.
 *
 * @param   tag     the given tag
 * @return  true if the tag corresponds
 */
    
    public static boolean isTag(String tag) {
        return ElementTags.ANNOTATION.equals(tag);
    }
}
