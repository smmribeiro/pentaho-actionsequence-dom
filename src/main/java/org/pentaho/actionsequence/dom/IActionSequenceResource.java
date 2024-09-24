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

package org.pentaho.actionsequence.dom;

import java.net.URI;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * A wrapper class for an action sequence resource element.
 * 
 * @author Angelo Rodriguez
 * 
 */
public interface IActionSequenceResource extends IAbstractIOElement {

  // Document Resources nodes
  String SOLUTION_FILE_RESOURCE_TYPE = "solution-file"; //$NON-NLS-1$
  String URL_RESOURCE_TYPE = "url"; //$NON-NLS-1$
  String FILE_RESOURCE_TYPE = "file"; //$NON-NLS-1$
  String XML_RESOURCE_TYPE = "xml"; //$NON-NLS-1$
  String STRING_RESOURCE_TYPE = "string"; //$NON-NLS-1$
  String RES_LOCATION_NAME = "location"; //$NON-NLS-1$
  String RES_MIME_TYPE_NAME = "mime-type"; //$NON-NLS-1$

  String SOLUTION_SCHEME = "solution"; //$NON-NLS-1$
  String FILE_SCHEME = "file"; //$NON-NLS-1$

  /**
   * @return the resource name
   */
  String getName();

  /**
   * Sets the resource name.
   * 
   * @param resourceName
   *          the resource name
   */
  void setName( String resourceName );

  /**
   * Sets the resource mime type.
   * 
   * @param mimeType
   *          the mime type
   */
  void setMimeType( String mimeType );

  /**
   * @return the resource mime type
   */
  String getMimeType();

  /**
   * Sets the resource URI
   * 
   * @param uri
   *          the resource URI
   */
  void setPath( String uri );

  /**
   * @return the resource URI
   */
  String getPath();

  /**
   * @return the resource file type
   */
  String getType();

  /**
   * Sets the resource file type
   * 
   * @param resourceType
   *          the resource file type
   */
  void setType( String resourceType );

  /*
   * (non-Javadoc)
   * 
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#delete()
   */
  void delete();

  /*
   * (non-Javadoc)
   * 
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getElement()
   */
  Element getElement();

  /*
   * (non-Javadoc)
   * 
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getDocument()
   */
  IActionSequenceDocument getDocument();

  URI getUri();

  void setUri( URI uri );

  String getString();

  void setString( String string );

  String getXml();

  void setXml( String xml ) throws DocumentException;
}
