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
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.pentaho.actionsequence.dom.actions.IActionParameterMgr;

/**
 * Convenience class used to distinguish action sequence inputs from action sequence outputs.
 * 
 * @author Angelo Rodriguez
 * 
 */
@SuppressWarnings( { "rawtypes", "unchecked" } )
public class ActionSequenceOutput extends AbstractIOElement implements IActionSequenceOutput {

  protected ActionSequenceOutput( Element outputElement, IActionParameterMgr actionInputProvider ) {
    super( outputElement, actionInputProvider );
  }

  public IActionSequenceOutputDestination[] getDestinations() {
    ArrayList outputDestinations = new ArrayList();
    List destinationElements = ioElement.selectNodes( ActionSequenceDocument.OUTPUTS_DESTINATIONS_NAME + "/*" ); //$NON-NLS-1$
    for ( Iterator iter = destinationElements.iterator(); iter.hasNext(); ) {
      outputDestinations.add( new ActionSequenceOutputDestination( (Element) iter.next(), actionInputProvider ) );
    }
    return (IActionSequenceOutputDestination[]) outputDestinations.toArray( new ActionSequenceOutputDestination[0] );
  }

  public IActionSequenceOutputDestination addDestination( String destination, String name ) {
    Element destinationParent =
        DocumentHelper.makeElement( ioElement, ActionSequenceDocument.OUTPUTS_DESTINATIONS_NAME );
    Element newDestinationElement = destinationParent.addElement( destination );
    newDestinationElement.setText( name );
    IActionSequenceOutputDestination actionSequenceOutputDestination =
        new ActionSequenceOutputDestination( newDestinationElement, actionInputProvider );
    ActionSequenceDocument.fireIoChanged( this );
    return actionSequenceOutputDestination;
  }

  public boolean isOutputParameter() {
    List<Attribute> attribs = ioElement.attributes();
    for ( Attribute attrib : attribs ) {
      if ( attrib.getName().equals( IS_OUTPUT_PARAM_ATTR ) ) {
        String outParamTxt = attrib.getValue();
        return Boolean.parseBoolean( outParamTxt );
      }
    }
    // default if not present
    return true;
  }

  public void setOutputParameter( boolean isOutputParameter ) {
    List<Attribute> attribs = ioElement.attributes();
    for ( Attribute attrib : attribs ) {
      if ( attrib.getName().equals( IS_OUTPUT_PARAM_ATTR ) ) {
        attrib.setValue( Boolean.toString( isOutputParameter ) );
        ActionSequenceDocument.fireIoChanged( this );
        return;
      }
    }
    // not found, create new
    ioElement.addAttribute( IS_OUTPUT_PARAM_ATTR, Boolean.toString( isOutputParameter ) );
    ActionSequenceDocument.fireIoChanged( this );
  }

}
