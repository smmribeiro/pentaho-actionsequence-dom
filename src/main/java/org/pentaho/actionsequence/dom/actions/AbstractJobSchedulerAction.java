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

import java.util.ArrayList;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputSource;
import org.pentaho.actionsequence.dom.IActionSequenceValidationError;

@SuppressWarnings( { "rawtypes", "unchecked" } )
public abstract class AbstractJobSchedulerAction extends ActionDefinition {
  public static final String COMPONENT_NAME = "org.pentaho.component.JobSchedulerComponent"; //$NON-NLS-1$
  public static final String JOB_NAME_ELEMENT = "jobName"; //$NON-NLS-1$
  public static final String JOB_ACTION_ELEMENT = "jobAction"; //$NON-NLS-1$
  public static final String JOB_NAME = "job-name"; //$NON-NLS-1$

  protected AbstractJobSchedulerAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  protected AbstractJobSchedulerAction( String componentName ) {
    super( componentName );
  }

  public void setJobName( IActionInputSource value ) {
    setActionInputValue( JOB_NAME_ELEMENT, value );
  }

  public IActionInput getJobName() {
    return getInput( JOB_NAME_ELEMENT );
  }

  public IActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInput( JOB_ACTION_ELEMENT );
    if ( validationError != null ) {
      switch ( validationError.errorCode ) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing job action input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Job action input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Job action input is uninitialized.";
          break;
      }
      errors.add( validationError );
    }

    validationError = validateInput( JOB_NAME_ELEMENT );
    if ( validationError != null ) {
      switch ( validationError.errorCode ) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing job name input parameter.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Job name input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Job name input is uninitialized.";
          break;
      }
      errors.add( validationError );
    }

    return (ActionSequenceValidationError[]) errors.toArray( new ActionSequenceValidationError[0] );
  }

  public static boolean accepts( Element element ) {
    return ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME );
  }
}
