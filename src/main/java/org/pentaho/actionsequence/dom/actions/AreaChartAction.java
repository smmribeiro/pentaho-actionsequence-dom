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

public class AreaChartAction extends AbstractChartAction {

  public static final String CHART_TYPE = "AreaChart"; //$NON-NLS-1$

  public AreaChartAction( Element actionDefElement, IActionParameterMgr actionInputProvider ) {
    super( actionDefElement, actionInputProvider );
  }

  public AreaChartAction() {
    super( COMPONENT_NAME );
  }

  protected void initNewActionDefinition() {
    super.initNewActionDefinition();
    setChartType( CHART_TYPE );
  }

  public String[] getReservedInputNames() {
    return EXPECTED_INPUTS;
  }

  public static boolean accepts( Element element ) {
    boolean result = false;
    if ( AbstractChartAction.accepts( element ) ) {
      element =
          (Element) element.selectSingleNode( ActionSequenceDocument.COMPONENT_DEF_NAME
              + "/chart-attributes/chart-type" ); //$NON-NLS-1$
      result = ( element != null ) && element.getText().equals( CHART_TYPE );
    }
    return result;
  }
}
