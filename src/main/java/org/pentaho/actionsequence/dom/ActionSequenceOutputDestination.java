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

import org.dom4j.Document;
import org.dom4j.Element;
import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

public class ActionSequenceOutputDestination implements IActionSequenceOutputDestination {

  Element destinationElement;
  IActionParameterMgr actionInputProvider;

  protected ActionSequenceOutputDestination( Element element, IActionParameterMgr actionInputProvider ) {
    destinationElement = element;
    this.actionInputProvider = actionInputProvider;
  }

  public void setDestination( String destination ) {
    destinationElement.setName( destination );
    ActionSequenceDocument.fireIoChanged( getActionSequenceOutput() );
  }

  public String getDestination() {
    return destinationElement.getName();
  }

  public void setName( String name ) {
    destinationElement.setText( name );
    ActionSequenceDocument.fireIoChanged( getActionSequenceOutput() );
  }

  public String getName() {
    return destinationElement.getText();
  }

  public IActionSequenceOutput getActionSequenceOutput() {
    ActionSequenceOutput actionSequenceOutput = null;
    if ( destinationElement != null ) {
      Element ancestorElement = destinationElement.getParent();
      if ( ancestorElement != null ) {
        ancestorElement = ancestorElement.getParent();
        if ( ancestorElement != null ) {
          actionSequenceOutput = new ActionSequenceOutput( ancestorElement, actionInputProvider );
        }
      }
    }
    return actionSequenceOutput;
  }

  public void delete() {
    Document doc = destinationElement.getDocument();
    if ( doc != null ) {
      IActionSequenceOutput actionSequenceOutput = getActionSequenceOutput();
      destinationElement.detach();
      ActionSequenceDocument.fireIoChanged( actionSequenceOutput );
    }
  }
}
