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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import org.dom4j.Element;
import org.pentaho.commons.connection.IPentahoStreamSource;

public interface IActionResource extends IActionIOElement {

  /**
   * The Resource is a solution file
   */
  int SOLUTION_FILE_RESOURCE = 1;

  /**
   * The resource is a URL
   */
  int URL_RESOURCE = 2;

  /**
   * The resource is an arbitrary file
   */
  int FILE_RESOURCE = 3;

  /**
   * The resource type is unknown
   */
  int UNKNOWN_RESOURCE = 4;

  /**
   * The resource type is an embedded string
   */
  int STRING = 5;

  /**
   * The resource type is embedded xml
   */
  int XML = 6;

  URI getUri();

  void setURI( URI uri );

  String getMimeType();

  void setMimeType( String mimeType );

  IPentahoStreamSource getDataSource() throws FileNotFoundException;

  InputStream getInputStream() throws FileNotFoundException;

  void delete();

  void setMapping( String publicName );

  void setName( String resourceName );

  void setType( String ioType );

  Element getElement();

  String getMapping();

  String getName();

  String getPublicName();

  String getType();

}
