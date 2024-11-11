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
 * A wrapper class for an action if statement.
 * 
 * @author Angelo Rodriguez
 * 
 */
public class ActionIfStatement extends ActionControlStatement implements IActionIfStatement {

  private static final ActionSequenceValidationError[] EMPTY_ARRAY = new ActionSequenceValidationError[0];

  public ActionIfStatement( Element controlElement, IActionParameterMgr actionInputProvider ) {
    super( controlElement, actionInputProvider );
  }

  /**
   * Sets the if condition. The condition should be well formatted javascript.
   * 
   * @param condition
   *          the condition.
   */
  public void setCondition( String condition ) {
    Element conditionElement = controlElement.element( ActionSequenceDocument.CONDITION_NAME );
    if ( conditionElement == null ) {
      conditionElement = controlElement.addElement( ActionSequenceDocument.CONDITION_NAME );
    }
    conditionElement.clearContent();
    conditionElement.addCDATA( condition );
    ActionSequenceDocument.fireControlStatementChanged( this );
  }

  /**
   * @return the condition.
   */
  public String getCondition() {
    String condition = ""; //$NON-NLS-1$
    Element conditionElement = controlElement.element( ActionSequenceDocument.CONDITION_NAME );
    if ( conditionElement != null ) {
      condition = conditionElement.getText();
    }
    return condition;
  }

  protected IActionSequenceValidationError[] validateThis() {
    return EMPTY_ARRAY;
  }

}
