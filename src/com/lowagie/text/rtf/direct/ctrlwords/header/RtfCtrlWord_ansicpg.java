/* $Id$
 * $Name$
 *
 * Copyright 2007 by Howard Shank (hgshank@yahoo.com)
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
 * the Initial Developer are Copyright (C) 1999-2006 by Bruno Lowagie.
 * All Rights Reserved.
 * Co-Developer of the code is Paulo Soares. Portions created by the Co-Developer
 * are Copyright (C) 2000-2006 by Paulo Soares. All Rights Reserved.
 *
 * Contributor(s): all the names of the contributors are added in the source code
 * where applicable.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * LGPL license (the ?GNU LIBRARY GENERAL PUBLIC LICENSE?), in which case the
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
 
package com.lowagie.text.rtf.direct.ctrlwords.header;

import java.io.PushbackReader;

import com.lowagie.text.rtf.direct.RtfParser;
import com.lowagie.text.rtf.direct.ctrlwords.RtfCtrlWordData;
import com.lowagie.text.rtf.direct.ctrlwords.basectrlwords.RtfCtrlWordBase_ansicpg;

/**
 * Description:
 * 	,"This keyword represents the ANSI code page used to perform the Unicode to ANSI conversion when writing RTF text. N represents the code page in decimal. This is typically set to the default ANSI code page of the run-time environment (for example, \ansicpg1252 for U.S. Windows). The reader can use the same ANSI code page to convert ANSI text back to Unicode. Possible values include the following:
 * 	437 United States IBM
 * 	708 Arabic (ASMO 708)
 * 	709 Arabic (ASMO 449+, BCON V4)
 * 	710 Arabic (transparent Arabic)
 * 	711 Arabic (Nafitha Enhanced)
 * 	720 Arabic (transparent ASMO)
 * 	819 Windows 3.1 (United States and Western Europe)
 * 	850 IBM multilingual
 * 	852 Eastern European
 * 	860 Portuguese
 * 	862 Hebrew
 * 	863 French Canadian
 * 	864 Arabic
 * 	865 Norwegian
 * 	866 Soviet Union
 * 	874 Thai
 * 	932 Japanese
 * 	936 Simplified Chinese
 * 	949 Korean
 * 	950 Traditional Chinese
 * 	1250 Windows 3.1 (Eastern European)
 * 	1251 Windows 3.1 (Cyrillic)
 * 	1252 Western European
 * 	1253 Greek
 * 	1254 Turkish
 * 	1255 Hebrew
 * 	1256 Arabic
 * 	1257 Baltic
 * 	1258 Vietnamese
 * 	1361 Johab
 * 	This keyword should be emitted in the RTF header section right after the \ansi, \mac, \pc or \pca keyword.
 * Group:
 * 	Unicode RTF
 * Type:
 * 	Value
 * RTF Version:
 * 	
 */

public class RtfCtrlWord_ansicpg extends RtfCtrlWordBase_ansicpg {

	public RtfCtrlWord_ansicpg(RtfParser rtfParser){
		super(rtfParser);
		this.defaultParserState = RtfParser.PARSER_IN_HEADER;
	}
	/* (non-Javadoc)
	 * @see com.lowagie.text.rtf.direct.ctrlwords.KwdBase#handleControlWord()
	 */
	public boolean handleControlWord() {
		// if we're doing an import, let rtfDoc put the control word in the document.
		if(this.rtfParser.getConversionType() == RtfParser.TYPE_IMPORT_FULL) {
			return true;	// don't do anything because the rtfDoc handles this statically
		}
		// TODO Set the document code page here.
		// the code page value is in the parameter.
		
		return true;
	}
}
