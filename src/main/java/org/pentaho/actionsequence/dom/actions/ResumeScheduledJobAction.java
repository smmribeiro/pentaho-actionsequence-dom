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

import org.dom4j.Element;
import org.pentaho.actionsequence.dom.ActionSequenceDocument;

public class ResumeScheduledJobAction extends AbstractJobSchedulerAction {

  public static final String RESUME_SCHED_JOB_CMND = "resumeJob"; //$NON-NLS-1$

  protected static final String[] EXPECTED_INPUTS = new String[] { JOB_NAME_ELEMENT };

  public ResumeScheduledJobAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
    // TODO Auto-generated constructor stub
  }

  public ResumeScheduledJobAction() {
    super( COMPONENT_NAME );
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition( JOB_ACTION_ELEMENT, RESUME_SCHED_JOB_CMND );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public static boolean accepts( Element element ) {
    boolean result = false;
    if ( AbstractJobSchedulerAction.accepts( element ) ) {
      element =
          (Element) element.selectSingleNode( ActionSequenceDocument.COMPONENT_DEF_NAME + "/" + JOB_ACTION_ELEMENT ); //$NON-NLS-1$
      result = ( element != null ) && element.getText().equals( RESUME_SCHED_JOB_CMND );
    }
    return result;
  }
}
