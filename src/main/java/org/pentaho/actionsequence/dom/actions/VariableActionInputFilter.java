/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2028-08-13
 ******************************************************************************/

package org.pentaho.actionsequence.dom.actions;

import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.IActionInput;

public class VariableActionInputFilter implements IActionInputFilter {

  public boolean accepts( IActionInput actionInput ) {
    return ( actionInput instanceof ActionInput );
  }

}
