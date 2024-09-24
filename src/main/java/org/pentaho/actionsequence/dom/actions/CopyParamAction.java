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
import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputVariable;
import org.pentaho.actionsequence.dom.IActionOutput;
import org.pentaho.actionsequence.dom.IActionSequenceValidationError;

/**
 * @deprecated As of 2.0
 */
@SuppressWarnings( { "rawtypes", "unchecked" } )
public class CopyParamAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.UtilityComponent"; //$NON-NLS-1$
  public static final String COPY_FROM_ELEMENT = "copy-from"; //$NON-NLS-1$
  public static final String COPY_TO_ELEMENT = "copy-to"; //$NON-NLS-1$
  public static final String COPY_FROM_XPATH = "copy/from"; //$NON-NLS-1$
  public static final String COPY_RETURN_XPATH = "copy/return"; //$NON-NLS-1$
  public static final String COPY_PARAM_COMMAND = "copy"; //$NON-NLS-1$
  public static final String OUTPUT_COPY = "output-copy"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { COPY_FROM_ELEMENT };

  public CopyParamAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public CopyParamAction() {
    super( COMPONENT_NAME );
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition( COPY_FROM_XPATH, COPY_FROM_ELEMENT );
    setComponentDefinition( COPY_RETURN_XPATH, COPY_TO_ELEMENT );
  }

  public String[] getReservedInputNames() {
    String inputName = getComponentDefinitionValue( COPY_FROM_XPATH );
    if ( ( inputName == null ) || ( inputName.trim().length() == 0 ) ) {
      inputName = COPY_FROM_ELEMENT;
    }
    return new String[] { inputName };
  }

  public String[] getReservedOutputNames() {
    String outputName = getComponentDefinitionValue( COPY_RETURN_XPATH );
    if ( ( outputName == null ) || ( outputName.trim().length() == 0 ) ) {
      outputName = COPY_TO_ELEMENT;
    }
    return new String[] { outputName };
  }

  @SuppressWarnings( "deprecation" )
  public static boolean accepts( Element element ) {
    boolean accepts = false;
    if ( ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME ) ) {
      accepts =
          ( element.selectNodes( ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + COPY_PARAM_COMMAND ).size() == 1 ) //$NON-NLS-1$
              && ( element.selectSingleNode( ActionSequenceDocument.COMPONENT_DEF_NAME
                  + "/" + FormatMsgAction.FORMAT_MSG_COMMAND ) == null ) //$NON-NLS-1$
              && ( element.selectSingleNode( ActionSequenceDocument.COMPONENT_DEF_NAME
                  + "/" + PrintParamAction.PRINT_PARAMS_COMMAND ) == null ) //$NON-NLS-1$
              && ( element.selectSingleNode( ActionSequenceDocument.COMPONENT_DEF_NAME
                  + "/" + PrintMapValsAction.PRINT_MAP_VALS_COMMAND ) == null ); //$NON-NLS-1$
    }
    return accepts;
  }

  public void setCopyFrom( IActionInputVariable value ) {
    if ( !COPY_FROM_ELEMENT.equals( getComponentDefinitionValue( CopyParamAction.COPY_FROM_XPATH ) ) ) {
      setComponentDefinition( CopyParamAction.COPY_FROM_XPATH, COPY_FROM_ELEMENT, false );
    }
    setActionInputValue( COPY_FROM_ELEMENT, value );
    IActionOutput actionOutput = getOutputCopy();
    if ( actionOutput != null ) {
      actionOutput.setType( value.getType() );
    }
  }

  public IActionInput getCopyFrom() {
    String copyFromVarName = getComponentDefinitionValue( CopyParamAction.COPY_FROM_XPATH );
    if ( ( copyFromVarName == null ) || ( copyFromVarName.trim().length() == 0 ) ) {
      copyFromVarName = COPY_FROM_ELEMENT;
    }
    return getInput( copyFromVarName );
  }

  public void setOutputCopy( String publicOutputName ) {
    String privateName = getComponentDefinitionValue( COPY_RETURN_XPATH );
    if ( ( privateName == null ) || ( privateName.trim().length() == 0 ) ) {
      privateName = COPY_TO_ELEMENT;
    }
    ActionInput copyFrom = (ActionInput) getCopyFrom();
    IActionOutput actionOutput =
        setOutput( privateName, publicOutputName, copyFrom != null ? copyFrom.getType()
            : ActionSequenceDocument.STRING_TYPE );
    if ( actionOutput == null ) {
      setComponentDefinition( COPY_RETURN_XPATH, (String) null );
    } else {
      setComponentDefinition( COPY_RETURN_XPATH, privateName );
    }
  }

  public IActionOutput getOutputCopy() {
    String privateName = getComponentDefinitionValue( COPY_RETURN_XPATH );
    if ( ( privateName == null ) || ( privateName.trim().length() == 0 ) ) {
      privateName = COPY_TO_ELEMENT;
    }
    return getOutput( privateName );
  }

  public IActionSequenceValidationError[] validate() {
    String copyFromVarName = getComponentDefinitionValue( CopyParamAction.COPY_FROM_XPATH );
    if ( ( copyFromVarName == null ) || ( copyFromVarName.trim().length() == 0 ) ) {
      copyFromVarName = COPY_FROM_ELEMENT;
    }

    ArrayList errors = new ArrayList();

    ActionSequenceValidationError validationError = validateInput( copyFromVarName );
    if ( validationError != null ) {
      switch ( validationError.errorCode ) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing input parameter to copy from.";
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "'Copy from' input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "'Copy from' input parameter is uninitialized.";
          break;
      }
      errors.add( validationError );
    }

    String privateName = getComponentDefinitionValue( COPY_RETURN_XPATH );
    if ( ( privateName == null ) || ( privateName.trim().length() == 0 ) ) {
      privateName = COPY_TO_ELEMENT;
    }

    validationError = validateOutput( privateName );
    if ( validationError != null ) {
      if ( validationError.errorCode == ActionSequenceValidationError.OUTPUT_MISSING ) {
        validationError.errorMsg = "Missing output parameter to copy to.";
      }
      errors.add( validationError );
    }

    return (ActionSequenceValidationError[]) errors.toArray( new ActionSequenceValidationError[0] );
  }

  // public Object getValueToCopy() {
  // Object value = null;
  // ActionInput actionInput = (ActionInput)getCopyFrom();
  // if (actionInput != null) {
  // value = actionInput.getValue();
  // }
  // return value;
  // }
}
