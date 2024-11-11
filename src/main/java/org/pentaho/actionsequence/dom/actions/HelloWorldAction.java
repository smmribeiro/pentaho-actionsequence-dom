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

public class HelloWorldAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.HelloWorldComponent"; //$NON-NLS-1$
  public static final String QUOTE_ELEMENT = "quote"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { QUOTE_ELEMENT };

  public HelloWorldAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public HelloWorldAction() {
    super( COMPONENT_NAME );
  }

  public static boolean accepts( Element element ) {
    return ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public void setQuote( IActionInputSource value ) {
    setActionInputValue( QUOTE_ELEMENT, value );
  }

  public IActionInput getQuote() {
    return getInput( QUOTE_ELEMENT );
  }

}
