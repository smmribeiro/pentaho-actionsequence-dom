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

public class ResumeSchedulerAction extends ActionDefinition {

  public static final String COMPONENT_NAME = "org.pentaho.component.SchedulerAdminComponent"; //$NON-NLS-1$
  public static final String SCHEDULER_ACTION_ELEMENT = "schedulerAction"; //$NON-NLS-1$
  public static final String RESUME_SCHEDULER_CMND = "resumeScheduler"; //$NON-NLS-1$

  public ResumeSchedulerAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public ResumeSchedulerAction() {
    super( COMPONENT_NAME );
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setComponentDefinition( SCHEDULER_ACTION_ELEMENT, RESUME_SCHEDULER_CMND );
  }

  public static boolean accepts( Element element ) {
    boolean result = false;
    if ( ActionDefinition.accepts( element ) && hasComponentName( element, COMPONENT_NAME ) ) {
      element =
          (Element) element.selectSingleNode( ActionSequenceDocument.COMPONENT_DEF_NAME
              + "/" + SCHEDULER_ACTION_ELEMENT ); //$NON-NLS-1$
      result = ( element != null ) && element.getText().equals( RESUME_SCHEDULER_CMND );
    }
    return result;
  }
}
