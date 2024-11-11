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

public interface IActionInput {
  ActionInputConstant NULL_INPUT = new ActionInputConstant( (Object) null, null );

  Object getValue();

  String getStringValue();

  String getStringValue( String defaultValue );

  String getStringValue( boolean replaceParamReferences );

  String getStringValue( boolean replaceParamReferences, String defaultValue );

  Boolean getBooleanValue();

  boolean getBooleanValue( boolean defaultValue );

  Integer getIntValue();

  int getIntValue( int defaultValue );

  String getName();
}
