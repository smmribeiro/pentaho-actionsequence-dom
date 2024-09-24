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

package org.pentaho.actionsequence.dom.actions;

import java.util.ArrayList;
import java.util.Arrays;

import org.pentaho.actionsequence.dom.ActionInput;
import org.pentaho.actionsequence.dom.ActionInputConstant;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;
import org.pentaho.actionsequence.dom.IActionInput;

@SuppressWarnings( { "rawtypes", "unchecked" } )
public class ActionInputTypeFilter implements IActionInputFilter {

  ArrayList types = new ArrayList();
  boolean includeConstants = false;

  public ActionInputTypeFilter( String[] types, boolean includeConstants ) {
    if ( types != null ) {
      this.types.addAll( Arrays.asList( types ) );
    }
    this.includeConstants = includeConstants;
  }

  public ActionInputTypeFilter( String[] types ) {
    this( types, false );
  }

  public ActionInputTypeFilter( String type ) {
    this( new String[] { type }, false );
  }

  public boolean accepts( IActionInput actionInput ) {
    boolean result = false;
    if ( includeConstants && ( actionInput instanceof ActionInputConstant ) ) {
      ActionInputConstant constant = (ActionInputConstant) actionInput;
      if ( types.contains( ActionSequenceDocument.STRING_TYPE ) ) {
        result = constant.getValue() instanceof String;
      } else if ( types.contains( ActionSequenceDocument.LONG_TYPE )
          || types.contains( ActionSequenceDocument.INTEGER_TYPE )
          || types.contains( ActionSequenceDocument.BIGDECIMAL_TYPE ) ) {
        if ( constant.getValue() instanceof String ) {
          try {
            Integer.parseInt( constant.getStringValue() );
            result = true;
          } catch ( Exception ex ) {
            result = false;
          }
        }
      }
    } else {
      result = ( actionInput instanceof ActionInput ) && ( types.contains( ( (ActionInput) actionInput ).getType() ) );
    }
    return result;
  }

}
