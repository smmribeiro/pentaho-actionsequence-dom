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
import org.pentaho.actionsequence.dom.messages.Messages;

/**
 * A wrapper class for an action sequence input or output element.
 * 
 * @author Angelo Rodriguez
 * 
 */
public abstract class AbstractIOElement implements IAbstractIOElement {

  Element ioElement;
  IActionParameterMgr actionInputProvider;

  protected AbstractIOElement( Element ioElement, IActionParameterMgr actionInputProvider ) {
    super();
    this.ioElement = ioElement;
    this.actionInputProvider = actionInputProvider;
  }

  /**
   * @return the name of the action sequence input/output
   */
  public String getName() {
    return ioElement.getName();
  }

  /**
   * Sets the name of the action sequence input/output
   * 
   * @param ioName
   *          the input/output name
   */
  public void setName( String ioName ) {
    ioName = ioName.trim();
    if ( ioName.split( "\\s+" ).length > 1 ) { //$NON-NLS-1$
      throw new IllegalArgumentException( Messages.getString( "ActionSequenceIO.NO_SPACES_IN_NAME" ) ); //$NON-NLS-1$
    }
    if ( !ioElement.getName().equals( ioName ) ) {
      ioElement.setName( ioName );
      ActionSequenceDocument.fireIoRenamed( this );
    }
  }

  /**
   * @return the type of input/output
   */
  public String getType() {
    return ioElement.attributeValue( TYPE_NAME );
  }

  /**
   * Sets the type of the IO type.
   * 
   * @param ioType
   *          the io type
   */
  public void setType( String ioType ) {
    if ( !ioType.equals( ioElement.attributeValue( TYPE_NAME ) ) ) {
      ioElement.addAttribute( TYPE_NAME, ioType );
      ActionSequenceDocument.fireIoChanged( this );
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#delete()
   */
  public void delete() {
    Document doc = ioElement.getDocument();
    if ( doc != null ) {
      ioElement.detach();
      ActionSequenceDocument.fireIoRemoved( new ActionSequenceDocument( doc, actionInputProvider ), this );
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getElement()
   */
  public Element getElement() {
    return ioElement;
  }

  public boolean equals( Object arg0 ) {
    boolean result = false;
    if ( arg0 != null ) {
      if ( arg0.getClass() == this.getClass() ) {
        AbstractIOElement io = (AbstractIOElement) arg0;
        result = ( io.ioElement != null ? io.ioElement.equals( this.ioElement ) : ( io == this ) );
      }
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.pentaho.designstudio.dom.IActionSequenceElement#getDocument()
   */
  public IActionSequenceDocument getDocument() {
    IActionSequenceDocument doc = null;
    if ( ( ioElement != null ) && ( ioElement.getDocument() != null ) ) {
      doc = new ActionSequenceDocument( ioElement.getDocument(), actionInputProvider );
    }
    return doc;
  }

  public IActionParameterMgr getParameterMgr() {
    return actionInputProvider;
  }
}
