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
package com.lowagie.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Every iText tool has to implement this interface.
 */
public abstract class AbstractTool implements ActionListener {
	
	/** The internal frame of the tool. */
	protected JInternalFrame internalFrame;
	/** The list of arguments needed by the tool. */
	protected ArrayList arguments = new ArrayList();
	
	/**
	 * Gets the menubar.
	 * @return a menubar for this tool
	 */
	public JMenuBar getMenubar() {
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		JMenuItem usage = new JMenuItem("Usage");
		usage.setMnemonic(KeyEvent.VK_U);
		usage.addActionListener(this);
		file.add(usage);
		JMenuItem args = new JMenuItem("Arguments");
		args.setMnemonic(KeyEvent.VK_A);
		args.addActionListener(this);
		file.add(args);
		JMenuItem execute = new JMenuItem("Execute");
		execute.setMnemonic(KeyEvent.VK_E);
		execute.addActionListener(this);
		file.add(execute);
		JMenuItem close = new JMenuItem("Close");
		close.setMnemonic(KeyEvent.VK_C);
		close.addActionListener(this);
		file.add(close);
		JMenu params = new JMenu("Arguments");
		file.setMnemonic(KeyEvent.VK_T);
		JMenuItem item;
		ToolArgument argument;
		for (Iterator i = arguments.iterator(); i.hasNext(); ) {
			argument = (ToolArgument)i.next();
			item = new JMenuItem(argument.getName());
			item.setToolTipText(argument.getDescription());
			item.addActionListener(argument);
			params.add(item);
		}
		menubar.add(file);
		menubar.add(params);
		return menubar;
	}
	
	/**
	 * Gets the usage of the tool.
	 * @return a String describing how to use the tool.
	 */
	public String getUsage() {
		StringBuffer buf = new StringBuffer("java ");
		buf.append(getClass().getName());
		ToolArgument argument;
		for (Iterator i = arguments.iterator(); i.hasNext(); ) {
			argument = (ToolArgument) i.next();
			buf.append(" ");
			buf.append(argument.getName());
		}
		buf.append("\n");
		for (Iterator i = arguments.iterator(); i.hasNext(); ) {
			argument = (ToolArgument) i.next();
			buf.append(argument.getUsage());
		}
		return buf.toString();
	}

	/**
	 * Executes the tool (in most cases this generates a PDF file).
	 */
	public abstract void execute();
	
	/**
	 * Gets the current arguments of the tool.
	 * @return a String with the list of arguments and their values.
	 */
	public String getArgs() {
		StringBuffer buf = new StringBuffer("Current arguments:\n");
		ToolArgument argument;
		for (Iterator i = arguments.iterator(); i.hasNext(); ) {
			argument = (ToolArgument) i.next();
			buf.append("  ");
			buf.append(argument.getName());
			if (argument.getValue() == null) {
				buf.append(" = null\n");
			}
			else {
				buf.append(" = '");
				buf.append(argument.getValue());
				buf.append("'\n");
			}
		}
		return buf.toString();
	}
	
	/**
	 * Gets the value of a given argument.
	 * @param name the name of the argument
	 * @return the value of an argument as an Object.
	 * @throws InstantiationException
	 */
	public Object getValue(String name) throws InstantiationException {
		ToolArgument argument;
		for (Iterator i = arguments.iterator(); i.hasNext(); ) {
			argument = (ToolArgument) i.next();
			if (name.equals(argument.getName())) {
				return argument.getArgument();
			}
		}
		return null;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent evt) {
		if ("Close".equals(evt.getActionCommand())) {
			System.exit(0);
		}
		if ("Usage".equals(evt.getActionCommand())) {
			JOptionPane.showMessageDialog(internalFrame, getUsage());
		}
		if ("Arguments".equals(evt.getActionCommand())) {
			JOptionPane.showMessageDialog(internalFrame, getArgs());
		}
		if ("Execute".equals(evt.getActionCommand())) {
			this.execute();
		}
	}
	
	/**
	 * @return Returns the arguments.
	 */
	public ArrayList getArguments() {
		return arguments;
	}
	/**
	 * @param arguments The arguments to set.
	 */
	public void setArguments(ArrayList arguments) {
		this.arguments = arguments;
	}
	/**
	 * @return Returns the internalFrame.
	 */
	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}
	/**
	 * @param internalFrame The internalFrame to set.
	 */
	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}
}