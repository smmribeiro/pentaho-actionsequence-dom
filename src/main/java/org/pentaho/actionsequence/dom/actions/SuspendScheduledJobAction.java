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

public class SuspendScheduledJobAction extends AbstractJobSchedulerAction {

  public static final String SUSPEND_SCHED_JOB_CMND = "suspendJob"; //$NON-NLS-1$
  protected static final String[] EXPECTED_INPUTS = new String[] { JOB_NAME_ELEMENT };

  public SuspendScheduledJobAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public SuspendScheduledJobAction() {
    super( COMPONENT_NAME );
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition( JOB_ACTION_ELEMENT, SUSPEND_SCHED_JOB_CMND );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public static boolean accepts( Element element ) {
    boolean result = false;
    if ( AbstractJobSchedulerAction.accepts( element ) ) {
      element =
          (Element) element.selectSingleNode( ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + JOB_ACTION_ELEMENT ); //$NON-NLS-1$
      result = ( element != null ) && element.getText().equals( SUSPEND_SCHED_JOB_CMND );
    }
    return result;
  }
}
