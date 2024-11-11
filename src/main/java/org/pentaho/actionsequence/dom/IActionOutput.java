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

/**
 * Convenience class used to distinguish action inputs from action outputs.
 * 
 * @author Angelo Rodriguez
 * 
 */
public interface IActionOutput extends IActionInputVariable, IActionIOElement {

  /**
   * @return the mapped name if it exists, otherwise the input/output name is returned.
   */
  String getPublicName();

  String getVariableName();

  void setValue( Object value );

}
