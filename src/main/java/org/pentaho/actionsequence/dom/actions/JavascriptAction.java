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

import java.util.ArrayList;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputSource;
import org.pentaho.actionsequence.dom.IActionSequenceValidationError;

@SuppressWarnings( { "rawtypes", "unchecked" } )
public class JavascriptAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.JavascriptRule"; //$NON-NLS-1$
  public static final String SCRIPT_ELEMENT = "script"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { SCRIPT_ELEMENT };

  public JavascriptAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public JavascriptAction() {
    super( COMPONENT_NAME );
  }

  public static boolean accepts( Element element ) {
    return ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public void setScript( IActionInputSource value ) {
    setActionInputValue( SCRIPT_ELEMENT, value );
  }

  public IActionInput getScript() {
    return getInput( SCRIPT_ELEMENT );
  }

  public IActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInput( SCRIPT_ELEMENT );
    if ( validationError != null ) {
      switch ( validationError.errorCode ) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing javascript input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Javascript input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Javascript input parameter is uninitialized.";
          break;
      }
      errors.add( validationError );
    }

    if ( getOutputs().length == 0 ) {
      validationError = new ActionSequenceValidationError();
      validationError.errorCode = ActionSequenceValidationError.OUTPUT_MISSING;
      validationError.errorMsg = "Missing javascript output parameter.";
      validationError.actionDefinition = this;
      errors.add( validationError );
    }

    return (ActionSequenceValidationError[]) errors.toArray( new ActionSequenceValidationError[0] );
  }
}
