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

/**
 * Interface to be implement by objects that wrap action sequence elements
 * 
 * @author Angelo Rodriguez
 * 
 */
public interface IActionSequenceElement {

  /**
   * @return the action sequence element wrapped by this object
   */
  Element getElement();

  /**
   * Removes the element from the action sequence
   */
  void delete();

  /**
   * @return an action sequence document that wraps the dom document containing the element wrapped by this object.
   */
  IActionSequenceDocument getDocument();
}
