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

public interface IActionIOElement extends IAbstractIOElement {
  String getMapping();

  /**
   * Sets the name to which the input/output is mapped.
   * 
   * @param mapping
   *          the mapped name. If null any existing mapping is removed.
   */
  void setMapping( String mapping );

  /**
   * @return the action definition to which this input/output belongs.
   */
  IActionDefinition getActionDefinition();
}
