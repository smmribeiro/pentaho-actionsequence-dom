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

public interface IActionSequenceInputSource {

  void setOrigin( String origin );

  String getOrigin();

  void setName( String name );

  String getName();

  IActionSequenceInput getActionSequenceInput();

  void delete();

}
