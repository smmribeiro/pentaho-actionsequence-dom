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

public interface IActionSequenceValidationError {
  int INPUT_OK = 0;;
  int INPUT_MISSING = 1;
  int INPUT_REFERENCES_UNKNOWN_VAR = 2;
  int INPUT_UNINITIALIZED = 3;
  int OUTPUT_MISSING = 4;

  int getErrorCode();

  String getErrorMsg();

  IActionDefinition getActionDefinition();

  String getParameterName();
}
