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

public interface IActionSequenceOutputDestination {

  void setDestination( String destination );

  String getDestination();

  void setName( String name );

  String getName();

  IActionSequenceOutput getActionSequenceOutput();

  void delete();

}
