/*
 * $Id$
 * $Name$
 *
 * Copyright 1999, 2000, 2001 by Bruno Lowagie.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Library General Public License as published
 * by the Free Software Foundation; either version 2 of the License, or any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Library general Public License for more
 * details.
 *
 * You should have received a copy of the GNU Library General Public License along
 * with this library; if not, write to the Free Foundation, Inc., 59 Temple Place,
 * Suite 330, Boston, MA 02111-1307 USA.
 *
 * If you didn't download this code from the following link, you should check if
 * you aren't using an obsolete version:
 * http://www.lowagie.com/iText/
 *
 * ir-arch Bruno Lowagie,
 * Adolf Baeyensstraat 121
 * 9040 Sint-Amandsberg
 * BELGIUM
 * tel. +32 (0)9 228.10.97
 * bruno@lowagie.com
 *
 */

package com.lowagie.text;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

/**
 * A <CODE>Cell</CODE> is a <CODE>Rectangle</CODE> containing other
 * <CODE>Element</CODE>s.
 * <P>
 * A <CODE>Cell</CODE> must be added to a <CODE>Table</CODE>.
 * The <CODE>Table</CODE> will place the <CODE>Cell</CODE> in
 * a <CODE>Row</CODE>.
 * <P>
 * Example:
 * <BLOCKQUOTE><PRE>
 * Table table = new Table(3);
 * table.setBorderWidth(1);
 * table.setBorderColor(new Color(0, 0, 255));
 * table.setCellpadding(5);
 * table.setCellspacing(5);
 * <STRONG>Cell cell = new Cell("header");</STRONG>
 * <STRONG>cell.setHeader(true);</STRONG>
 * <STRONG>cell.setColspan(3);</STRONG>
 * table.addCell(cell);
 * <STRONG>cell = new Cell("example cell with colspan 1 and rowspan 2");</STRONG>
 * <STRONG>cell.setRowspan(2);</STRONG>
 * <STRONG>cell.setBorderColor(new Color(255, 0, 0));</STRONG>
 * table.addCell(cell);
 * table.addCell("1.1");
 * table.addCell("2.1");
 * table.addCell("1.2");
 * table.addCell("2.2");
 * </PRE></BLOCKQUOTE>
 *
 * @see		Rectangle
 * @see		Element
 * @see		Table
 * @see		Row
 *
 * @author  bruno@lowagie.com
 */

public class Cell extends Rectangle implements TextElementArray {
    
    // static final membervariable
    
/** This constant can be used as empty cell. */
    public static final Cell EMPTY_CELL = new Cell();
    
    // membervariables
    
/** This is the <CODE>ArrayList</CODE> of <CODE>Element</CODE>s. */
    private ArrayList arrayList;
    
/** This is the horizontal alignment. */
    private int horizontalAlignment;
    
/** This is the vertical alignment. */
    private int verticalAlignment;
    
/** This is the colspan. */
    private int colspan = 1;
    
/** This is the rowspan. */
    private int rowspan = 1;
    
/** This is the leading. */
    float leading = -1;
    
/** Is this <CODE>Cell</CODE> a header? */
    private boolean header;
    
/** Will the element have to be wrapped? */
    private boolean noWrap;
    
    // constructors
    
/**
 * Constructs an empty <CODE>Cell</CODE>.
 */
    
    public Cell() {
        // creates a Rectangle with BY DEFAULT a border of 0.5
        super(0, 0, 0, 0);
        setBorder(BOX);
        setBorderWidth(0.5f);
        
        // initializes the arraylist and adds an element
        arrayList = new ArrayList();
        try {
            addElement(new Paragraph(0));
        }
        catch(BadElementException bee) {
            // this will never happen
        }
    }
    
/**
 * Constructs a <CODE>Cell</CODE> with a certain content.
 * <P>
 * The <CODE>String</CODE> will be converted into a <CODE>Paragraph</CODE>.
 *
 * @param	content		a <CODE>String</CODE>
 * @throws	BadElementException this can never happen with this creator
 */
    
    public Cell(String content) throws BadElementException {
        this(new Paragraph(content));
    }
    
/**
 * Constructs a <CODE>Cell</CODE> with a certain <CODE>Element</CODE>.
 * <P>
 * if the element is a <CODE>ListItem</CODE>, <CODE>Row</CODE> or
 * <CODE>Cell</CODE>, an exception will be thrown.
 *
 * @param	element		the element
 * @throws	BadElementException when the creator was called with a <CODE>ListItem</CODE>, <CODE>Row</CODE> or <CODE>Cell</CODE>
 */
    
    public Cell(Element element) throws BadElementException {
        // creates a Rectangle with BY DEFAULT a border of 0.5
        super(0, 0, 0, 0);
        setBorder(BOX);
        setBorderWidth(0.5f);
        
        // initializes the arraylist and adds an element
        arrayList = new ArrayList();
        addElement(element);
    }
    
        /**
         * Returns a <CODE>Cell</CODE> that has been constructed taking in account
         * the value of some <VAR>attributes</VAR>.
         *
         * @param	attributes		Some attributes
         * @return	a <CODE>Cell</CODE>
         */
    
    public Cell(Properties attributes) {
        this();
        String value;
        if ((value = attributes.getProperty(ElementTags.HORIZONTALALIGN)) != null) {
            setHorizontalAlignment(value);
        }
        if ((value = attributes.getProperty(ElementTags.VERTICALALIGN)) != null) {
            setVerticalAlignment(value);
        }
        if ((value = attributes.getProperty(ElementTags.COLSPAN)) != null) {
            setColspan(Integer.parseInt(value));
        }
        if ((value = attributes.getProperty(ElementTags.ROWSPAN)) != null) {
            setRowspan(Integer.parseInt(value));
        }
        if ((value = attributes.getProperty(ElementTags.LEADING)) != null) {
            setLeading(Float.parseFloat(value + "f"));
        }
        if ((value = attributes.getProperty(ElementTags.HEADER)) != null) {
            setHeader(new Boolean(value).booleanValue());
        }
        if ((value = attributes.getProperty(ElementTags.NOWRAP)) != null) {
            setNoWrap(new Boolean(value).booleanValue());
        }
        if ((value = attributes.getProperty(ElementTags.BORDERWIDTH)) != null) {
            setBorderWidth(Float.parseFloat(value + "f"));
        }
        int border = 0;
        if ((value = attributes.getProperty(ElementTags.LEFT)) != null) {
            if (new Boolean(value).booleanValue()) border |= Rectangle.LEFT;
        }
        if ((value = attributes.getProperty(ElementTags.RIGHT)) != null) {
            if (new Boolean(value).booleanValue()) border |= Rectangle.RIGHT;
        }
        if ((value = attributes.getProperty(ElementTags.TOP)) != null) {
            if (new Boolean(value).booleanValue()) border |= Rectangle.TOP;
        }
        if ((value = attributes.getProperty(ElementTags.BOTTOM)) != null) {
            if (new Boolean(value).booleanValue()) border |= Rectangle.BOTTOM;
        }
        setBorder(border);
        if (attributes.getProperty(ElementTags.RED) != null &&
        attributes.getProperty(ElementTags.GREEN) != null &&
        attributes.getProperty(ElementTags.BLUE) != null) {
            setBorderColor(new Color(Integer.parseInt(attributes.getProperty(ElementTags.RED)),
            Integer.parseInt(attributes.getProperty(ElementTags.GREEN)),
            Integer.parseInt(attributes.getProperty(ElementTags.BLUE))));
        }
        if (attributes.getProperty(ElementTags.BGRED) != null &&
        attributes.getProperty(ElementTags.BGGREEN) != null &&
        attributes.getProperty(ElementTags.BGBLUE) != null) {
            setBackgroundColor(new Color(Integer.parseInt(attributes.getProperty(ElementTags.BGRED)),
            Integer.parseInt(attributes.getProperty(ElementTags.BGGREEN)),
            Integer.parseInt(attributes.getProperty(ElementTags.BGBLUE))));
        }
        if ((value = attributes.getProperty(ElementTags.GRAYFILL)) != null) {
            setGrayFill(Float.parseFloat(value + "f"));
        }
    }
    
    // implementation of the Element-methods
    
/**
 * Processes the element by adding it (or the different parts) to an
 * <CODE>ElementListener</CODE>.
 *
 * @param	listener	an <CODE>ElementListener</CODE>
 * @return	<CODE>true</CODE> if the element was processed successfully
 */
    
    public final boolean process(ElementListener listener) {
        try {
            return listener.add(this);
        }
        catch(DocumentException de) {
            return false;
        }
    }
    
/**
 * Gets the type of the text element.
 *
 * @return	a type
 */
    
    public final int type() {
        return Element.CELL;
    }
    
/**
 * Gets all the chunks in this element.
 *
 * @return	an <CODE>ArrayList</CODE>
 */
    
    public final ArrayList getChunks() {
        ArrayList tmp = new ArrayList();
        for (Iterator i = arrayList.iterator(); i.hasNext(); ) {
            tmp.addAll(((Element) i.next()).getChunks());
        }
        return tmp;
    }
    
    // methods to set the membervariables
    
/**
 * Adds an element to this <CODE>Cell</CODE>.
 * <P>
 * Remark: you can't add <CODE>ListItem</CODE>s, <CODE>Row</CODE>s, <CODE>Cell</CODE>s,
 * <CODE>JPEG</CODE>s, <CODE>GIF</CODE>s or <CODE>PNG</CODE>s to a <CODE>Cell</CODE>.
 *
 * @param element The <CODE>Element</CODE> to add
 * @throws BadElementException if the method was called with a <CODE>ListItem</CODE>, <CODE>Row</CODE> or <CODE>Cell</CODE>
 */
    
    public final void addElement(Element element) throws BadElementException {
        switch(element.type()) {
            case Element.LISTITEM:
            case Element.ROW:
            case Element.CELL:
            case Element.TABLE:
            case Element.JPEG:
            case Element.GIF:
            case Element.PNG:
                throw new BadElementException("You can't add listitems, rows, tables, cells, jpgs, gifs or pngs to a cell.");
            case Element.ANCHOR:
            case Element.PARAGRAPH:
            case Element.PHRASE:
                if (leading < 0) {
                    leading = ((Phrase) element).leading();
                }
            case Element.LIST:
                if (leading < 0) {
                    leading = ((List) element).leading();
                }
                default:
                    arrayList.add(element);
        }
    }
    
/**
 * Add an <CODE>Object</CODE> to this cell.
 *
 * @param o the object to add
 * @return always <CODE>true</CODE>
 */
    
    public boolean add(Object o) {
        try {
            this.addElement((Element) o);
            return true;
        }
        catch(ClassCastException cce) {
            throw new ClassCastException("You can only add objects that implement the Element interface.");
        }
        catch(BadElementException bee) {
            throw new ClassCastException(bee.getMessage());
        }
    }
    
/**
 * Sets the leading.
 *
 * @param	value	the new value
 */
    
    public final void setLeading(float value) {
        leading = value;
    }
    
/**
 * Sets the horizontal alignment.
 *
 * @param	value	the new value
 */
    
    public final void setHorizontalAlignment(int value) {
        horizontalAlignment = value;
    }
    
/**
 * Sets the alignment of this paragraph.
 *
 * @param	alignment		the new alignment as a <CODE>String</CODE>
 */
    
    public final void setHorizontalAlignment(String alignment) {
        if (ElementTags.ALIGN_CENTER.equals(alignment)) {
            this.horizontalAlignment = Element.ALIGN_CENTER;
            return;
        }
        if (ElementTags.ALIGN_RIGHT.equals(alignment)) {
            this.horizontalAlignment = Element.ALIGN_RIGHT;
            return;
        }
        if (ElementTags.ALIGN_JUSTIFIED.equals(alignment)) {
            this.horizontalAlignment = Element.ALIGN_JUSTIFIED;
            return;
        }
        this.horizontalAlignment = Element.ALIGN_LEFT;
    }
    
/**
 * Sets the vertical alignment.
 *
 * @param	value	the new value
 */
    
    public final void setVerticalAlignment(int value) {
        verticalAlignment = value;
    }
    
/**
 * Sets the alignment of this paragraph.
 *
 * @param	alignment		the new alignment as a <CODE>String</CODE>
 */
    
    public final void setVerticalAlignment(String alignment) {
        if (ElementTags.ALIGN_TOP.equals(alignment)) {
            this.verticalAlignment = Element.ALIGN_TOP;
            return;
        }
        if (ElementTags.ALIGN_BOTTOM.equals(alignment)) {
            this.verticalAlignment = Element.ALIGN_BOTTOM;
            return;
        }
        if (ElementTags.ALIGN_BASELINE.equals(alignment)) {
            this.verticalAlignment = Element.ALIGN_BASELINE;
            return;
        }
        this.verticalAlignment = Element.ALIGN_MIDDLE;
    }
    
/**
 * Sets the colspan.
 *
 * @param	value	the new value
 */
    
    public final void setColspan(int value) {
        colspan = value;
    }
    
/**
 * Sets the rowspan.
 *
 * @param	value	the new value
 */
    
    public final void setRowspan(int value) {
        rowspan = value;
    }
    
/**
 * Sets header.
 *
 * @param	value	the new value
 */
    
    public final void setHeader(boolean value) {
        header = value;
    }
    
/**
 * Set nowrap.
 *
 * @param	value	the new value
 */
    
    public final void setNoWrap(boolean value) {
        noWrap = value;
    }
    
    // methods to retrieve information
    
/**
 * Gets the number of <CODE>Element</CODE>s in the Cell.
 *
 * @return	a <CODE>size</CODE>.
 */
    
    public final int size() {
        return arrayList.size();
    }
    
/**
 * Checks if the <CODE>Cell</CODE> is empty.
 *
 * @return	<CODE>false</CODE> if there are non-empty <CODE>Element</CODE>s in the <CODE>Cell</CODE>.
 */
    
    public final boolean isEmpty() {
        switch(size()) {
            case 0:
                return true;
            case 1:
                Element element = (Element) arrayList.get(0);
                switch (element.type()) {
                    case Element.CHUNK:
                        return ((Chunk) element).isEmpty();
                    case Element.ANCHOR:
                    case Element.PHRASE:
                    case Element.PARAGRAPH:
                        return ((Phrase) element).isEmpty();
                    case Element.LIST:
                        return ((List) element).size() == 0;
                }
                return false;
                default:
                    return false;
        }
    }
    
/**
 * Gets an iterator of <CODE>Element</CODE>s.
 *
 * @return	an <CODE>Iterator</CODE>.
 */
    
    public final Iterator getElements() {
        return arrayList.iterator();
    }
    
/**
 * Gets the horizontal alignment.
 *
 * @return	a value
 */
    
    public final int horizontalAlignment() {
        return horizontalAlignment;
    }
    
/**
 * Gets the vertical alignment.
 *
 * @return	a value
 */
    
    public final int verticalAlignment() {
        return verticalAlignment;
    }
    
/**
 * Gets the colspan.
 *
 * @return	a value
 */
    
    public final int colspan() {
        return colspan;
    }
    
/**
 * Gets the rowspan.
 *
 * @return	a value
 */
    
    public final int rowspan() {
        return rowspan;
    }
    
/**
 * Gets the leading.
 *
 * @return	a value
 */
    
    public final float leading() {
        if (leading < 0) {
            return 16;
        }
        return leading;
    }
    
/**
 * Is this <CODE>Cell</CODE> a header?
 *
 * @return	a value
 */
    
    public boolean header() {
        return header;
    }
    
/**
 * Get nowrap.
 *
 * @return	a value
 */
    
    public boolean noWrap() {
        return noWrap;
    }
    
/**
 * Clears all the <CODE>Element</CODE>s of this <CODE>Cell</CODE>.
 */
    public final void clear() {
        arrayList.clear();
    }
    
/**
 * Checks if a given tag corresponds with this object.
 *
 * @param   tag     the given tag
 * @return  true if the tag corresponds
 */
    
    public static boolean isTag(String tag) {
        return ElementTags.CELL.equals(tag);
    }
    
/**
 * Returns a representation of this <CODE>Cell</CODE>.
 *
 * @return	a <CODE>String</CODE>
 */
    
    public String toString() {
        
        StringBuffer buf = new StringBuffer("<").append(ElementTags.CELL).append(" ").append(ElementTags.HORIZONTALALIGN).append("=\"");
        buf.append(ElementTags.getAlignment(horizontalAlignment));
        buf.append("\" ").append(ElementTags.VERTICALALIGN).append("=\"");
        buf.append(ElementTags.getAlignment(verticalAlignment));
        if (colspan != 1) {
            buf.append(" ").append(ElementTags.COLSPAN).append("=\"").append(colspan).append("\"");
        }
        if (rowspan != 1) {
            buf.append(" ").append(ElementTags.ROWSPAN).append("=\"").append(rowspan).append("\"");
        }
        if (header) {
            buf.append(" ").append(ElementTags.HEADER).append("=\"").append(true).append("\"");
        }
        if (noWrap) {
            buf.append(" ").append(ElementTags.NOWRAP).append("=\"").append(true).append("\"");
        }
        if (leading != -1) {
            buf.append(" ").append(ElementTags.LEADING).append("=\"").append(leading).append("\"");
        }
        buf.append(">");
        for (Iterator i = arrayList.iterator(); i.hasNext(); ) {
            buf.append(((Element) i.next()).toString());
        }
        buf.append("</").append(ElementTags.CELL).append(">\n");
        return buf.toString();
    }
}
