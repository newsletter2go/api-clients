package com.newsletter2go.api;

import java.util.HashMap;
import java.util.Map;

import com.newsletter2go.api.exceptions.MissingParamException;
import com.newsletter2go.api.rest.ApiResult;
import com.newsletter2go.api.rest.HttpsPostRequest;

/**
 * This is an example class, showing how to send an email using the Newsletter2Go API. Note: This is
 * just an example. There are many other ways to do this.
 * 
 * @author Newsletter2Go-Dev-Team
 * 
 * Questions? => dev@newsletter2go.com
 *
 */
public class Email {

  final String API_SEND_URL = "https://www.newsletter2go.de/de/api/send/email/";
  
  private final String apiKey;
  
  public Email(String apiKey) {
    this.apiKey = apiKey;
  }
  
  /**
   * required and optional parameters for sending an email
   */
  public enum params {
    TO, FROM, SUBJECT, HTML, TEXT, ID, DEBUG, REF, DATE, REPLY
  }

  /**
   * required parameters
   */
  private Map<String, String> requiredParameters = new HashMap<String, String>();

  /**
   * optional params
   */
  private Map<String, String> optionalParameters = new HashMap<String, String>();

  /**
   * Method to add a param to this request
   * 
   * @param param name of the paramter
   * @param value value of the parameter
   * @throws IllegalArgumentException if the param is not allowed
   */
  public void addParam(Email.params param, String value) throws IllegalArgumentException {
    if (param == params.TO || param == params.FROM || param == params.SUBJECT
        || param == params.HTML || param == params.TEXT) {
      requiredParameters.put(param.name(), value);
    } else {
      if (param == params.ID || param == params.DEBUG || param == params.REF
          || param == params.DATE || param == params.REPLY) {
        optionalParameters.put(param.name(), value);
      } else {
        // bad param
        throw new IllegalArgumentException(param.toString() + " is not known.");
      }
    }
  }

  /**
   * Sends this email via POST-request.
   * 
   * @return the result from Newsletter2Go API encapsulated in a result object ApiResult
   * @throws MissingParamException if at least 1 required param is missing
   */
  public ApiResult send() throws MissingParamException {
    // check if all required params are available:
    for (Map.Entry<String, String> entry : this.requiredParameters.entrySet()) {
      if (entry.getValue() == null || entry.getValue().isEmpty())
        throw new MissingParamException(entry.getKey() + " is missing.");
    }
    // else do the post request:
    return new HttpsPostRequest().doRequest(API_SEND_URL, this.apiKey, requiredParameters, optionalParameters);
  }

}
