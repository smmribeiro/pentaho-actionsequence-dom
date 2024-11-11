/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2029-07-20
 ******************************************************************************/


package org.pentaho.actionsequence.dom;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

/**
 * Convenience class used to distinguish action inputs from action outputs.
 * 
 * @author Angelo Rodriguez
 * 
 */
public class ActionOutput extends AbstractActionIOElement implements IActionOutput {

  public ActionOutput( Element ioElement, IActionParameterMgr actionInputProvider ) {
    super( ioElement, actionInputProvider );
  }

  /**
   * @return the mapped name if it exists, otherwise the input/output name is returned.
   */
  public String getPublicName() {
    String mapping = getMapping();
    return ( ( mapping != null ) && ( mapping.trim().length() > 0 ) ) ? mapping.trim() : ioElement.getName();
  }

  public String getVariableName() {
    return getPublicName();
  }

  public void setValue( Object value ) {
    if ( actionInputProvider != null ) {
      actionInputProvider.setOutputValue( this, value );
    }
  }

}
