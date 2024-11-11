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
 * Convenience class used to distinguish action sequence inputs from action sequence outputs.
 * 
 * @author Angelo Rodriguez
 * 
 */
public interface IActionSequenceOutput extends IAbstractIOElement {

  String IS_OUTPUT_PARAM_ATTR = "is-output-parameter";

  IActionSequenceOutputDestination[] getDestinations();

  IActionSequenceOutputDestination addDestination( String destination, String name );

  boolean isOutputParameter();

  void setOutputParameter( boolean isOutputParameter );

}
