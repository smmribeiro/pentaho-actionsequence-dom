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
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.ActionSequenceValidationError;
import org.pentaho.actionsequence.dom.IAbstractIOElement;
import org.pentaho.actionsequence.dom.IActionOutput;
import org.pentaho.actionsequence.dom.IActionSequenceValidationError;

@SuppressWarnings( { "rawtypes", "unchecked" } )
public class XQueryConnectionAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.XQueryLookupRule"; //$NON-NLS-1$
  public static final String PREPARED_COMPONENT_ELEMENT = "prepared_component"; //$NON-NLS-1$
  public static final String DEFAULT_CONNECTION_NAME = "shared_xquery_connection"; //$NON-NLS-1$

  protected static final String[] EXPECTED_OUTPUTS = new String[] { PREPARED_COMPONENT_ELEMENT, };

  public XQueryConnectionAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public XQueryConnectionAction() {
    super( COMPONENT_NAME );
  }

  public static boolean accepts( Element element ) {
    boolean result = false;
    if ( ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME ) ) {
      Element connectionOutput =
          (Element) element.selectSingleNode( ActionSequenceDocument.ACTION_OUTPUTS_NAME
              + "/" + PREPARED_COMPONENT_ELEMENT ); //$NON-NLS-1$
      result =
          ( connectionOutput != null )
              && ActionSequenceDocument.XQUERY_CONNECTION_TYPE.equals( connectionOutput
                  .attributeValue( IAbstractIOElement.TYPE_NAME ) );
    }
    return result;
  }

  public String[] getReservedInputNames() {
    return new String[0];
  }

  public String[] getReservedOutputNames() {
    return EXPECTED_OUTPUTS;
  }

  public String[] getReservedResourceNames() {
    return new String[0];
  }

  public void setOutputConnection( String publicOutputName ) {
    setOutput( PREPARED_COMPONENT_ELEMENT, publicOutputName, ActionSequenceDocument.XQUERY_CONNECTION_TYPE );
  }

  public IActionOutput getOutputConnection() {
    return getOutput( PREPARED_COMPONENT_ELEMENT );
  }

  public IActionSequenceValidationError[] validate() {
    ArrayList errors = new ArrayList();
    ActionSequenceValidationError validationError = validateOutput( PREPARED_COMPONENT_ELEMENT );
    if ( validationError != null ) {
      if ( validationError.errorCode == ActionSequenceValidationError.OUTPUT_MISSING ) {
        validationError.errorMsg = "Missing output connection name.";
      }
      errors.add( validationError );
    }

    return (ActionSequenceValidationError[]) errors.toArray( new ActionSequenceValidationError[0] );

  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setOutputConnection( DEFAULT_CONNECTION_NAME );
  }

}
