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
 * A wrapper class for an action if statement.
 * 
 * @author Angelo Rodriguez
 * 
 */
public interface IActionIfStatement extends IActionControlStatement {

  /**
   * Sets the if condition. The condition should be well formatted javascript.
   * 
   * @param condition
   *          the condition.
   */
  void setCondition( String condition );

  /**
   * @return the condition.
   */
  String getCondition();

}
