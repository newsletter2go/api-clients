package com.newsletter2go.api;

import java.util.HashMap;
import java.util.Map;

import com.newsletter2go.api.rest.ApiResult;
import com.newsletter2go.api.rest.HttpsPostRequest;

public class SmsNewsletter {

  final String API_CREATE_NEWSLETTER_URL = "https://www.newsletter2go.de/de/api/create/newsletter/";
  
  /*
   * required Parameters
   */
  private String apiKey;
  private String name;
  private String category;
  
  /*
   * optional Parameters
   */
  private String sms;
  private String from;
  
  public SmsNewsletter(String apiKey, String name, String category) throws IllegalArgumentException {
    if(apiKey == null || apiKey.isEmpty()) {
      throw new IllegalArgumentException("API Key must not be null or empty.");
    }
    if(name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Newsletter name must not be null or empty.");
    }
    if(category == null || category.isEmpty()) {
      throw new IllegalArgumentException("SMS category must not be null or empty.");
    } else if (!(category.equals("basic") || category.equals("plus"))) {
      throw new IllegalArgumentException("SMS category must be basic or plus.");
    }
    
    this.apiKey = apiKey;
    this.name = name;
    this.category = category;
  }
  
  public ApiResult doCreate() {
    Map<String, String> requiredParameters = this.buildRequiredParameters();
    Map<String, String> optionalParameters = this.buildOptionalParameters();
    
    return new HttpsPostRequest().doRequest(API_CREATE_NEWSLETTER_URL, this.apiKey, requiredParameters, optionalParameters);
  }
  
  private Map<String, String> buildRequiredParameters() {
    Map<String, String> requiredParameters = new HashMap<String, String>();
    
    requiredParameters.put("name", this.name);
    requiredParameters.put("type", "sms");
    requiredParameters.put("category", this.category);
    
    return requiredParameters;
  }
  
  private Map<String, String> buildOptionalParameters() {
    Map<String, String> optionalParameters = new HashMap<String, String>();
    
    addOptionalParameter("sms", this.sms, optionalParameters);
    addOptionalParameter("from", this.from, optionalParameters);
    
    return optionalParameters;
  }
  
  private void addOptionalParameter(String key, String value, Map<String, String> optionalParameters) {
    if(value != null && !value.isEmpty()) {
      optionalParameters.put(key, value);
    }
  }

  public void setSms(String sms) {
    this.sms = sms;
  }

  public void setFrom(String from) {
    this.from = from;
  }
}
