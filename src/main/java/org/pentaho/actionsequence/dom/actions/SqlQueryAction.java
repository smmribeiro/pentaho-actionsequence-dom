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
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IActionOutput;
import org.pentaho.actionsequence.dom.IActionSequenceValidationError;

@SuppressWarnings( { "rawtypes", "unchecked" } )
public class SqlQueryAction extends AbstractRelationalDbAction {

  public static final String QUERY_RESULT_OUTPUT_NAME = "query-result"; //$NON-NLS-1$

  public static final String COMPONENT_NAME = "org.pentaho.component.SQLLookupRule"; //$NON-NLS-1$
  public static final String DEFAULT_QUERY_RESULTS_NAME = "query_result"; //$NON-NLS-1$
  public static final String SQL_CONNECTION = "sql-connection"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { DRIVER_ELEMENT, CONNECTION_ELEMENT, USER_ID_ELEMENT,
    PASSWORD_ELEMENT, JNDI_ELEMENT, QUERY_ELEMENT, LIVE_CONNECTION_ELEMENT };

  public SqlQueryAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public SqlQueryAction() {
    super( COMPONENT_NAME );
  }

  public static boolean accepts( Element element ) {
    return ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME )
        && ( ( element.selectSingleNode( ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + QUERY_ELEMENT ) != null ) //$NON-NLS-1$
        || ( element.selectSingleNode( ActionSequenceDocument.ACTION_INPUTS_NAME + "/" + QUERY_ELEMENT ) != null ) ); //$NON-NLS-1$
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setJndi( new ActionInputConstant( "", this.actionParameterMgr ) ); //$NON-NLS-1$
    setQuery( new ActionInputConstant( "", this.actionParameterMgr ) ); //$NON-NLS-1$
    setOutputResultSet( DEFAULT_QUERY_RESULTS_NAME );
    setLive( new ActionInputConstant( true, this.actionParameterMgr ) );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public String[] getReservedOutputNames() {
    String expectedOutput = QUERY_RESULT_ELEMENT;
    String compDefVal = getComponentDefinitionValue( OUTPUT_NAME_ELEMENT );
    if ( compDefVal != null ) {
      expectedOutput = compDefVal;
    } else if ( getOutput( expectedOutput ) == null ) {
      IActionOutput[] actionOutputs = getOutputs( ActionSequenceDocument.RESULTSET_TYPE );
      if ( actionOutputs.length > 0 ) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[] { expectedOutput };
  }

  public IActionSequenceValidationError[] validate() {

    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateInput( CONNECTION_ELEMENT );
    if ( validationError == null ) {
      validationError = validateInput( DRIVER_ELEMENT );
      if ( validationError != null ) {
        switch ( validationError.errorCode ) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing database driver input parameter.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Database driver input parameter references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Database driver input parameter is uninitialized.";
            break;
        }
        errors.add( validationError );
      }

      validationError = validateInput( USER_ID_ELEMENT );
      if ( validationError != null ) {
        switch ( validationError.errorCode ) {
          case ActionSequenceValidationError.INPUT_MISSING:
            validationError.errorMsg = "Missing database login input parameter.";
            break;
          case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
            validationError.errorMsg = "Database login input parameter references unknown variable.";
            break;
          case ActionSequenceValidationError.INPUT_UNINITIALIZED:
            validationError.errorMsg = "Database login input parameter is uninitialized.";
            break;
        }
        errors.add( validationError );
      }
    } else if ( validationError.errorCode == ActionSequenceValidationError.INPUT_MISSING ) {
      validationError = validateInput( JNDI_ELEMENT );
      if ( validationError != null ) {
        if ( validationError.errorCode == ActionSequenceValidationError.INPUT_MISSING ) {
          validationError = validateInput( PREPARED_COMPONENT_ELEMENT );
          if ( validationError != null ) {
            switch ( validationError.errorCode ) {
              case ActionSequenceValidationError.INPUT_MISSING:
                validationError.errorMsg = "Missing database connection input parameter.";
                break;
              case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
                validationError.errorMsg = "Database connection input parameter references unknown variable.";
                break;
              case ActionSequenceValidationError.INPUT_UNINITIALIZED:
                validationError.errorMsg = "Database connection input parameter is uninitialized.";
                break;
            }
            errors.add( validationError );
          }
        } else if ( validationError.errorCode == ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR ) {
          validationError.errorMsg = "Database connection input parameter references unknown variable.";
          errors.add( validationError );
        } else if ( validationError.errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED ) {
          validationError.errorMsg = "Database connection input parameter is uninitialized.";
          errors.add( validationError );
        }
      }
    } else if ( validationError.errorCode == ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR ) {
      validationError.errorMsg = "Database connection input parameter references unknown variable.";
      errors.add( validationError );
    } else if ( validationError.errorCode == ActionSequenceValidationError.INPUT_UNINITIALIZED ) {
      validationError.errorMsg = "Database connection input parameter is uninitialized.";
      errors.add( validationError );
    } else {
      errors.add( validationError );
    }

    if ( validationError != null ) {
      switch ( validationError.errorCode ) {
        case ActionSequenceValidationError.INPUT_MISSING:
          validationError.errorMsg = "Missing query input parameter.";
          validationError = validateInput( QUERY_ELEMENT );
          break;
        case ActionSequenceValidationError.INPUT_REFERENCES_UNKNOWN_VAR:
          validationError.errorMsg = "Query input parameter references unknown variable.";
          break;
        case ActionSequenceValidationError.INPUT_UNINITIALIZED:
          validationError.errorMsg = "Query input parameter is uninitialized.";
          break;
      }
      errors.add( validationError );
    }

    validationError = validateOutput( PREPARED_COMPONENT_ELEMENT );
    if ( validationError != null ) {
      validationError = validateOutput( QUERY_RESULT_ELEMENT );
      if ( validationError != null ) {
        validationError.errorMsg = "Missing query results output parameter.";
        errors.add( validationError );
      }
    }

    return (ActionSequenceValidationError[]) errors.toArray( new ActionSequenceValidationError[0] );
  }

  public String getQueryType() {
    return ActionSequenceDocument.SQL_QUERY_TYPE;
  }
}
