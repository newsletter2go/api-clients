package com.newsletter2go.api.rest;

/**
 * Example class to store the result of a Newsletter2Go-API-request
 * 
 * @author Newsletter2Go-Dev-Team
 * 
 * Questions? => dev@newsletter2go.com
 *
 */

public class ApiResult {

  private int success;
  private String value;
  private int status;
  private String reason;

  // private constructor needed for gson
  private ApiResult() {};

  public int getSuccess() {
    return success;
  }

  public String getValue() {
    return value;
  }

  public int getStatus() {
    return status;
  }

  public String getReason() {
    return reason;
  }

  @Override
  public String toString() {
    return "ApiResult [\n\tsuccess=" + success + ",\n\tvalue=" + value + ",\n\tstatus=" + status
        + ",\n\treason=" + reason + "\n]";
  }

}
