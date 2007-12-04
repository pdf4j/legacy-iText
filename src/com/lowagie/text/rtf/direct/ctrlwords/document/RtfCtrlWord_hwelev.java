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
 
package com.lowagie.text.rtf.direct.ctrlwords.document;

import com.lowagie.text.rtf.direct.RtfParser;
import com.lowagie.text.rtf.direct.ctrlwords.basectrlwords.RtfCtrlWordBase_hwelev;

/**
 * Description:
 * 	,"This control word specifies whether applications should
 * 	assume that all characters in the Hangul Syllables Unicode
 * 	sub range (character values between 0xAC00 and 0xD7FF) are
 * 	of a single fixed width or shall use the characters? widths
 * 	defined by the font in use (typical for a proportional width
 * 	font).
 * 	Typically, applications shall retrieve the
 * 	character width for any character in a document from the
 * 	associated font, allowing each character to be of its own
 * 	width (a proportional width character).
 * 	This control
 * 	word specifies that applications shall instead assume a
 * 	single fixed width for all characters in the Hangul
 * 	Syllables sub range, by reading the width of Unicode
 * 	character 0x4E00 from the associated font and using that
 * 	width for all Hangul characters (or, if that character is
 * 	not present, the next available character in the font).
 *
 * 		Example: Consider an RTF document with three Hangul
 * 	characters:
 * 	The default presentation would have each of
 * 	those characters using the widths defined by the font (the
 * 	highlighting indicates that each character has its own
 * 	width):
 * 	However, if this control word is present, then
 * 	all three characters are forced to the fixed width of
 * 	character 0x4E00 from the font (or, in this case, the next
 * 	available character), resulting in the characters in the
 * 	font being forced to that fixed width, which results in the
 * 	following output:
 * 	Notice from the highlighting that the
 * 	characters have been compressed to the width of the single
 * 	character and displayed at that fixed width.
 * 	Note   This
 * 	control word is used to maintain compatibility with
 * 	documents created by Microsoft Office Word 2003.
 * Group:
 * 	Document Formatting Properties
 * Type:
 * 	Flag
 * Default Param:
 * 	0
 * Pass Default:
 * 	false
 * RTF Version:
 * 	
 */

public class RtfCtrlWord_hwelev extends RtfCtrlWordBase_hwelev {

	public RtfCtrlWord_hwelev(RtfParser rtfParser){
		super(rtfParser);
	}

}
