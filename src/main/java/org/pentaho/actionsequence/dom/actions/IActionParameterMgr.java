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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionOutput;
import org.pentaho.actionsequence.dom.IActionResource;
import org.pentaho.commons.connection.IPentahoStreamSource;

public interface IActionParameterMgr {
  Object getInputValue( IActionInput actionInput );

  String replaceParameterReferences( String inputString );

  IPentahoStreamSource getDataSource( IActionResource actionResource ) throws FileNotFoundException;

  InputStream getInputStream( IActionResource actionResource ) throws FileNotFoundException;

  IPentahoStreamSource getDataSource( IActionInput actionInput );

  void setOutputValue( IActionOutput actionOutput, Object value );

  String getString( IActionResource actionResource ) throws IOException;
}
