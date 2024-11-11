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

public class ActionSequenceDocumentAdapter implements IActionSequenceDocumentListener {

  public ActionSequenceDocumentAdapter() {
    super();
  }

  public void ioAdded( IAbstractIOElement io ) {
  }

  public void ioRemoved( Object parent, IAbstractIOElement io ) {
  }

  public void ioRenamed( IAbstractIOElement io ) {
  }

  public void ioChanged( IAbstractIOElement io ) {
  }

  public void resourceAdded( Object resource ) {
  }

  public void resourceRemoved( Object parent, Object resource ) {
  }

  public void resourceRenamed( Object resource ) {
  }

  public void resourceChanged( Object resource ) {
  }

  public void actionAdded( IActionDefinition action ) {
  }

  public void actionRemoved( Object parent, IActionDefinition action ) {
  }

  public void actionRenamed( IActionDefinition action ) {
  }

  public void actionChanged( IActionDefinition action ) {
  }

  public void controlStatementAdded( IActionControlStatement controlStatement ) {
  }

  public void controlStatementRemoved( Object parent, IActionControlStatement controlStatement ) {
  }

  public void controlStatementChanged( IActionControlStatement controlStatement ) {
  }

  public void headerChanged( IActionSequenceDocument actionSequenceDocument ) {
  }

}
