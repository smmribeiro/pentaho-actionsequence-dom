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

/**
 * Classes that implement this interface can be notified of changes to an action sequence.
 * 
 * @author Angelo Rodriguez
 * 
 */
public interface IActionSequenceDocumentListener {

  void ioAdded( IAbstractIOElement io );

  void ioRemoved( Object parent, IAbstractIOElement io );

  void ioRenamed( IAbstractIOElement io );

  void ioChanged( IAbstractIOElement io );

  void resourceAdded( Object resource );

  void resourceRemoved( Object parent, Object resource );

  void resourceRenamed( Object resource );

  void resourceChanged( Object resource );

  void actionAdded( IActionDefinition action );

  void actionRemoved( Object parent, IActionDefinition action );

  void actionRenamed( IActionDefinition action );

  void actionChanged( IActionDefinition action );

  void controlStatementAdded( IActionControlStatement controlStatement );

  void controlStatementRemoved( Object parent, IActionControlStatement controlStatement );

  void controlStatementChanged( IActionControlStatement controlStatement );

  void headerChanged( IActionSequenceDocument actionSequenceDocument );
}
