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
 * Convenience class used to distinguish action inputs from action outputs.
 * 
 * @author Angelo Rodriguez
 * 
 */
public class ActionInput extends AbstractActionIOElement implements IActionInput, IActionSequenceElement {

  public ActionInput( Element ioElement, IActionParameterMgr actionInputProvider ) {
    super( ioElement, actionInputProvider );
  }

  public Object getValue() {
    Object value = null;
    if ( actionInputProvider != null ) {
      value = actionInputProvider.getInputValue( this );
    }
    return value;
  }

  public Boolean getBooleanValue() {
    Boolean boolValue = null;
    String stringValue = getStringValue();
    if ( stringValue != null ) {
      boolValue = new Boolean( stringValue );
    }
    return boolValue;
  }

  public boolean getBooleanValue( boolean defaultValue ) {
    Boolean boolValue = getBooleanValue();
    return boolValue != null ? boolValue.booleanValue() : defaultValue;
  }

  public Integer getIntValue() {
    Integer intValue = null;
    String stringValue = getStringValue();
    if ( stringValue != null ) {
      intValue = Integer.parseInt( stringValue );
    }
    return intValue;
  }

  public int getIntValue( int defaultValue ) {
    Integer intValue = getIntValue();
    return intValue != null ? intValue.intValue() : defaultValue;
  }

  public String getStringValue() {
    return getStringValue( true );
  }

  public String getStringValue( boolean replaceParamReferences ) {
    return getStringValue( replaceParamReferences, null );
  }

  public String getStringValue( boolean replaceParamReferences, String defaultValue ) {
    Object theValue = getValue();
    if ( replaceParamReferences && ( actionInputProvider != null ) && ( theValue != null ) ) {
      theValue = actionInputProvider.replaceParameterReferences( theValue.toString() );
    }
    return theValue != null ? theValue.toString() : defaultValue;
  }

  public String getStringValue( String defaultValue ) {
    return getStringValue( true, defaultValue );
  }

  /**
   * @return the mapped name if it exists, otherwise the input/output name is returned.
   */
  public String getReferencedVariableName() {
    String mapping = getMapping();
    return ( ( mapping != null ) && ( mapping.trim().length() > 0 ) ) ? mapping.trim() : ioElement.getName();
  }
}
