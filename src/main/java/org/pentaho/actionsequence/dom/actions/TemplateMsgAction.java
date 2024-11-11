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


package org.pentaho.actionsequence.dom.actions;

import java.net.URI;

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionInput;
import org.pentaho.actionsequence.dom.IActionInputSource;
import org.pentaho.actionsequence.dom.IActionInputVariable;
import org.pentaho.actionsequence.dom.IActionOutput;
import org.pentaho.actionsequence.dom.IActionResource;

public class TemplateMsgAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.TemplateComponent"; //$NON-NLS-1$
  public static final String TEMPLATE_ELEMENT = "template"; //$NON-NLS-1$
  public static final String OUTPUT_MSG_ELEMENT = "output-message"; //$NON-NLS-1$
  public static final String OUTPUT_STRING = "output-string"; //$NON-NLS-1$
  public static final String TEMPLATE_FILE = "template-file"; //$NON-NLS-1$
  public static final String MIME_TYPE = "mime-type"; //$NON-NLS-1$ 
  public static final String EXTENSION = "extension"; //$NON-NLS-1$
  public static final String TEMPLATE_RESOURCE = "template-resource"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { TEMPLATE_ELEMENT };
  protected static final String[] EXPECTED_RESOURCES = new String[] { TEMPLATE_ELEMENT };

  public TemplateMsgAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public TemplateMsgAction() {
    super( COMPONENT_NAME );
  }

  public static boolean accepts( Element element ) {
    return ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public String[] getReservedOutputNames() {
    String expectedOutput = OUTPUT_MSG_ELEMENT;
    if ( getOutput( expectedOutput ) == null ) {
      IActionOutput[] actionOutputs = getOutputs( ActionSequenceDocument.STRING_TYPE );
      if ( actionOutputs.length > 0 ) {
        expectedOutput = actionOutputs[0].getName();
      }
    }
    return new String[] { expectedOutput };
  }

  public String[] getReservedResourceNames() {
    return EXPECTED_RESOURCES;
  }

  protected void initNewActionDefinition() {
    setActionInputValue( TEMPLATE_ELEMENT, new ActionInputConstant( "", this.actionParameterMgr ) ); //$NON-NLS-1$
  }

  public IActionResource setTemplateResource( URI uri, String mimeType ) {
    IActionResource templateResource = setResourceUri( TEMPLATE_ELEMENT, uri, mimeType );
    // Cleaning up the template from input since we would be using
    // template based on resource
    if ( uri != null ) {
      setActionInputValue( TEMPLATE_ELEMENT, (IActionInputSource) null );
    }
    return templateResource;
  }

  public IActionResource getTemplateResource() {
    return getResource( TEMPLATE_ELEMENT );
  }

  public void setTemplate( IActionInputSource value ) {
    setActionInputValue( TEMPLATE_ELEMENT, value );

    // Cleaning up the resource since we would be using template based on input
    if ( ( value instanceof IActionInputVariable )
        || ( ( value != null ) && ( ( (ActionInputConstant) value ).getValue() != null ) ) ) {
      setTemplateResource( null, null );
    }
  }

  public IActionInput getTemplate() {
    return getInput( TEMPLATE_ELEMENT );
  }

  public IActionInput getMimeType() {
    return getInput( MIME_TYPE );
  }

  public void setMimeType( IActionInputSource value ) {
    setActionInputValue( MIME_TYPE, value );
  }

  public IActionInput getExtension() {
    return getInput( EXTENSION );
  }

  public void setExtension( IActionInputSource value ) {
    setActionInputValue( EXTENSION, value );
  }

  public void setOutputString( String publicOutputName ) {
    setOutput( OUTPUT_MSG_ELEMENT, publicOutputName, ActionSequenceDocument.STRING_TYPE );
  }

  public IActionOutput getOutputString() {
    return getOutput( OUTPUT_MSG_ELEMENT );
  }
}
