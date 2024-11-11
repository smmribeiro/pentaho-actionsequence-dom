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

import java.util.ArrayList;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

/**
 * A wrapper class for an action loop.
 * 
 * @author Angelo Rodriguez
 * 
 */
@SuppressWarnings( { "rawtypes", "unchecked" } )
public class ActionLoop extends ActionControlStatement implements IActionLoop {

  public ActionLoop( Element loopElement, IActionParameterMgr actionInputProvider ) {
    super( loopElement, actionInputProvider );
  }

  /**
   * Set the name of the parameter that is being looped on.
   * 
   * @param loopOn
   *          the parameter name. If null the loop parameter is removed.
   */
  public void setLoopOn( String loopOn ) {
    Attribute attr = controlElement.attribute( ActionSequenceDocument.LOOP_ON_NAME );
    if ( loopOn == null ) {
      if ( attr != null ) {
        attr.detach();
        ActionSequenceDocument.fireControlStatementChanged( this );
      }
    } else {
      loopOn = loopOn.trim();
      if ( attr == null ) {
        controlElement.addAttribute( ActionSequenceDocument.LOOP_ON_NAME, loopOn );
        attr = controlElement.attribute( ActionSequenceDocument.LOOP_ON_NAME );
        ActionSequenceDocument.fireControlStatementChanged( this );
      } else if ( !loopOn.equals( attr.getValue() ) ) {
        attr.setValue( loopOn );
        ActionSequenceDocument.fireControlStatementChanged( this );
      }
    }
  }

  /**
   * @return loopOn the name of the parameter that is being looped on
   */
  public String getLoopOn() {
    return controlElement.attributeValue( ActionSequenceDocument.LOOP_ON_NAME );
  }

  protected IActionSequenceValidationError[] validateThis() {
    ArrayList errors = new ArrayList();
    String loopOn = getLoopOn();
    if ( loopOn.trim().length() == 0 ) {
      errors.add( "Missing loop variable." );
    } else {
      IActionInputVariable[] actionVariables = getDocument().getAvailInputVariables( this );
      boolean isValid = false;
      for ( int i = 0; ( i < actionVariables.length ) && !isValid; i++ ) {
        isValid = actionVariables[i].getVariableName().equals( loopOn );
      }
      if ( !isValid ) {
        errors.add( "Loop references unknown variable." );
      }
    }
    return (ActionSequenceValidationError[]) errors.toArray( new ActionSequenceValidationError[0] );
  }

  public Boolean getLoopUsingPeek() {
    return Boolean.parseBoolean( controlElement.attributeValue( ActionSequenceDocument.PEEK_ONLY_NAME ) );
  }

  public void setLoopUsingPeek( Boolean usePeek ) {

    Attribute attr = controlElement.attribute( ActionSequenceDocument.PEEK_ONLY_NAME );
    if ( usePeek == null ) {
      if ( attr != null ) {
        attr.detach();
        ActionSequenceDocument.fireControlStatementChanged( this );
      }
    } else {
      if ( attr == null ) {
        controlElement.addAttribute( ActionSequenceDocument.PEEK_ONLY_NAME, usePeek.toString() );
        attr = controlElement.attribute( ActionSequenceDocument.PEEK_ONLY_NAME );
        ActionSequenceDocument.fireControlStatementChanged( this );
      } else if ( !usePeek.toString().equals( attr.getValue() ) ) {
        attr.setValue( usePeek.toString() );
        ActionSequenceDocument.fireControlStatementChanged( this );
      }
    }
  }

}
