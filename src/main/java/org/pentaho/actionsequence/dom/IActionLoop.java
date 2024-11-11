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
 * A wrapper class for an action loop.
 * 
 * @author Angelo Rodriguez
 * 
 */
public interface IActionLoop extends IActionControlStatement {

  /**
   * Set the name of the parameter that is being looped on.
   * 
   * @param loopOn
   *          the parameter name. If null the loop parameter is removed.
   */
  void setLoopOn( String loopOn );

  /**
   * @return loopOn the name of the parameter that is being looped on
   */
  String getLoopOn();

  /**
   * @return whether a peek operation will be performed to loop on a result set
   */
  Boolean getLoopUsingPeek();

  /**
   * Determines if a peek operation will be performed to loop on a result set. Only if the result set is scrollable that
   * is we CAN go back to the first record should we reset to the first record. This is to resolve multiple levels of
   * looping on resultset.
   * 
   * @param usePeek
   *          determines if a peek operation will be performed to loop on a result set
   */
  void setLoopUsingPeek( Boolean usePeek );

}
