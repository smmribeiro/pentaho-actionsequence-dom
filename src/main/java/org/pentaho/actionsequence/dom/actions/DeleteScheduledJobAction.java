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

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;

public class DeleteScheduledJobAction extends AbstractJobSchedulerAction {

  public static final String JOB_SCHEDULER_COMMAND = "deleteJob"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { JOB_NAME_ELEMENT };

  public DeleteScheduledJobAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public DeleteScheduledJobAction() {
    super( COMPONENT_NAME );
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition( JOB_ACTION_ELEMENT, JOB_SCHEDULER_COMMAND );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public static boolean accepts( Element element ) {
    boolean result = false;
    if ( AbstractJobSchedulerAction.accepts( element ) ) {
      element =
          (Element) element.selectSingleNode( ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + JOB_ACTION_ELEMENT ); //$NON-NLS-1$
      result = ( element != null ) && element.getText().equals( JOB_SCHEDULER_COMMAND );
    }
    return result;
  }
}
