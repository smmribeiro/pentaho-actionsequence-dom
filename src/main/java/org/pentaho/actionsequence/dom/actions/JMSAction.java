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

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputSource;

public class JMSAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "com.pentaho.component.JMSComponent"; //$NON-NLS-1$
  public static final String SOLUTION_NAME_ELEMENT = "solution-name"; //$NON-NLS-1$
  public static final String ACTION_PATH_ELEMENT = "action-path"; //$NON-NLS-1$
  public static final String ACTION_NAME_ELEMENT = "action-name"; //$NON-NLS-1$
  public static final String QUEUE_NAME_ELEMENT = "jms-queue-name"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { SOLUTION_NAME_ELEMENT, ACTION_PATH_ELEMENT,
    ACTION_NAME_ELEMENT, QUEUE_NAME_ELEMENT };

  public JMSAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public JMSAction() {
    super( COMPONENT_NAME );
  }

  public static boolean accepts( Element element ) {
    return ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public void setSolutionName( IActionInputSource value ) {
    setActionInputValue( SOLUTION_NAME_ELEMENT, value );
  }

  public IActionInput getSolutionName() {
    return getInput( ACTION_NAME_ELEMENT );
  }

  public void setActionPath( IActionInputSource value ) {
    setActionInputValue( ACTION_PATH_ELEMENT, value );
  }

  public IActionInput getActionPath() {
    return getInput( ACTION_NAME_ELEMENT );
  }

  public void setActionName( IActionInputSource value ) {
    setActionInputValue( ACTION_NAME_ELEMENT, value );
  }

  public IActionInput getActionName() {
    return getInput( ACTION_NAME_ELEMENT );
  }

  public void setJmsQueueName( IActionInputSource value ) {
    setActionInputValue( QUEUE_NAME_ELEMENT, value );
  }

  public IActionInput getJmsQueueName() {
    return getInput( QUEUE_NAME_ELEMENT );
  }

}
