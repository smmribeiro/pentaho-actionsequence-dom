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

import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

/**
 * A wrapper class for an action sequence input or output element.
 * 
 * @author Angelo Rodriguez
 * 
 */
public interface IAbstractIOElement extends IActionSequenceElement {

  String TYPE_NAME = "type"; //$NON-NLS-1$

  String getName();

  /**
   * Sets the name of the action sequence input/output
   * 
   * @param ioName
   *          the input/output name
   */
  void setName( String ioName );

  /**
   * @return the type of input/output
   */
  String getType();

  /**
   * Sets the type of the IO type.
   * 
   * @param ioType
   *          the io type
   */
  void setType( String ioType );

  IActionParameterMgr getParameterMgr();
}
