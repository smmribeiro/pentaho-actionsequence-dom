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

public class SimpleActionInputVariable implements IActionInputVariable {

  public String name;
  public String type;

  public SimpleActionInputVariable() {
  }

  public SimpleActionInputVariable( String name, String type ) {
    this.name = name;
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public String getVariableName() {
    return name;
  }

  public void setType( String type ) {
    this.type = type;
  }

  public void setVariableName( String name ) {
    this.name = name;
  }

  public Boolean getBooleanValue() {
    return null;
  }

  public boolean getBooleanValue( boolean defaultValue ) {
    return defaultValue;
  }

  public Integer getIntValue() {
    return null;
  }

  public int getIntValue( int defaultValue ) {
    return defaultValue;
  }

  public String getStringValue() {
    return null;
  }

  public String getStringValue( boolean replaceParamReferences, String defaultValue ) {
    return defaultValue;
  }

  public String getStringValue( boolean replaceParamReferences ) {
    return null;
  }

  public String getStringValue( String defaultValue ) {
    return defaultValue;
  }

  public Object getValue() {
    return null;
  }

  public String getName() {
    return name;
  }

  public void setName( String ioName ) {
    name = ioName;

  }

  public void delete() {
  }

  public IActionSequenceDocument getDocument() {
    return null;
  }

  public Element getElement() {
    return null;
  }

  public IActionParameterMgr getParameterMgr() {
    return null;
  }

}
