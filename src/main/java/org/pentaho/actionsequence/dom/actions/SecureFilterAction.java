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


package org.pentaho.actionsequence.dom.actions;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputSource;

public class SecureFilterAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.SecureFilterComponent"; //$NON-NLS-1$
  public static final String TARGET_ELEMENT = "target"; //$NON-NLS-1$
  public static final String XSL_ELEMENT = "xsl"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { TARGET_ELEMENT };

  public SecureFilterAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public SecureFilterAction() {
    super( COMPONENT_NAME );
  }

  public static boolean accepts( Element element ) {
    return ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public void setTarget( IActionInputSource value ) {
    setActionInputValue( TARGET_ELEMENT, value );
  }

  public IActionInput getTarget() {
    return getInput( TARGET_ELEMENT );
  }

  public void setXsl( IActionInputSource value ) {
    setActionInputValue( XSL_ELEMENT, value );
  }

  public IActionInput getXsl() {
    return getInput( XSL_ELEMENT );
  }
}
