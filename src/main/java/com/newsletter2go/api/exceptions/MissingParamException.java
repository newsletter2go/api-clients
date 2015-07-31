package com.newsletter2go.api.exceptions;

/**
 * Exception if a required Parameter is missing.
 * 
 * @author Newsletter2Go-Dev-Team
 * 
 * Questions? => dev@newsletter2go.com
 *
 */

public class MissingParamException extends Exception {

  private static final long serialVersionUID = 1L;

  public MissingParamException(String msg) {
    super(msg);
  }

}
