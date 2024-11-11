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

public class ReceiptAuditAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.ReceiptAuditComponent"; //$NON-NLS-1$
  public static final String MESSAGE_ELEMENT = "message"; //$NON-NLS-1$
  public static final String TIMESTAMP_ELEMENT = "dt"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { MESSAGE_ELEMENT, TIMESTAMP_ELEMENT };

  public ReceiptAuditAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public ReceiptAuditAction() {
    super( COMPONENT_NAME );
  }

  public static boolean accepts( Element element ) {
    return ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public void setMessage( IActionInputSource value ) {
    setActionInputValue( MESSAGE_ELEMENT, value );
  }

  public IActionInput getMessage() {
    return getInput( MESSAGE_ELEMENT );
  }

  public void setDt( IActionInputSource value ) {
    setActionInputValue( TIMESTAMP_ELEMENT, value );
  }

  public IActionInput getDt() {
    return getInput( TIMESTAMP_ELEMENT );
  }
}
