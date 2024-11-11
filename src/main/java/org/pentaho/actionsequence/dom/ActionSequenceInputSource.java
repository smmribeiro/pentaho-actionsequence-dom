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

import org.dom4j.Document;
import org.dom4j.Element;
import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

public class ActionSequenceInputSource implements IActionSequenceInputSource {

  Element sourceElement;
  IActionParameterMgr actionInputProvider;

  protected ActionSequenceInputSource( Element element, IActionParameterMgr actionInputProvider ) {
    sourceElement = element;
    this.actionInputProvider = actionInputProvider;
  }

  public void setOrigin( String origin ) {
    sourceElement.setName( origin );
    ActionSequenceDocument.fireIoChanged( getActionSequenceInput() );
  }

  public String getOrigin() {
    return sourceElement.getName();
  }

  public void setName( String name ) {
    sourceElement.setText( name );
    ActionSequenceDocument.fireIoChanged( getActionSequenceInput() );
  }

  public String getName() {
    return sourceElement.getText();
  }

  public IActionSequenceInput getActionSequenceInput() {
    ActionSequenceInput actionSequenceInput = null;
    if ( sourceElement != null ) {
      Element ancestorElement = sourceElement.getParent();
      if ( ancestorElement != null ) {
        ancestorElement = ancestorElement.getParent();
        if ( ancestorElement != null ) {
          actionSequenceInput = new ActionSequenceInput( ancestorElement, actionInputProvider );
        }
      }
    }
    return actionSequenceInput;
  }

  public void delete() {
    Document doc = sourceElement.getDocument();
    if ( doc != null ) {
      IActionSequenceInput actionSequenceInput = getActionSequenceInput();
      sourceElement.detach();
      ActionSequenceDocument.fireIoChanged( actionSequenceInput );
    }
  }
}
