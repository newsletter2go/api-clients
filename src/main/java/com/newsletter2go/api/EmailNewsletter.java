package com.newsletter2go.api;

import java.util.HashMap;
import java.util.Map;

import com.newsletter2go.api.rest.ApiResult;
import com.newsletter2go.api.rest.HttpsPostRequest;

public class EmailNewsletter {

  final String API_CREATE_NEWSLETTER_URL = "https://www.newsletter2go.de/de/api/create/newsletter/";
  
  /*
   * required Parameters
   */
  private String apiKey;
  private String name;
  
  /*
   * optional Parameters
   */
  private String subject;
  private String html;
  private String text;
  private String sms;
  private String from;
  private String reply;
  private String reference;
  private String rewriteurl;
  
  public EmailNewsletter(String apiKey, String name) throws IllegalArgumentException {
    if(apiKey == null || apiKey.isEmpty()) {
      throw new IllegalArgumentException("API Key must not be null or empty.");
    }
    if(name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Newsletter name must not be null or empty.");
    }
    
    this.apiKey = apiKey;
    this.name = name;
  }
  
  public ApiResult doCreate() {
    Map<String, String> requiredParameters = this.buildRequiredParameters();
    Map<String, String> optionalParameters = this.buildOptionalParameters();
    
    return new HttpsPostRequest().doRequest(API_CREATE_NEWSLETTER_URL, this.apiKey, requiredParameters, optionalParameters);
  }
  
  private Map<String, String> buildRequiredParameters() {
    Map<String, String> requiredParameters = new HashMap<String, String>();
    
    requiredParameters.put("name", this.name);
    requiredParameters.put("type", "email");
    
    return requiredParameters;
  }
  
  private Map<String, String> buildOptionalParameters() {
    Map<String, String> optionalParameters = new HashMap<String, String>();
    
    addOptionalParameter("subject", this.subject, optionalParameters);
    addOptionalParameter("html", this.html, optionalParameters);
    addOptionalParameter("text", this.text, optionalParameters);
    addOptionalParameter("sms", this.sms, optionalParameters);
    addOptionalParameter("from", this.from, optionalParameters);
    addOptionalParameter("reply", this.reply, optionalParameters);
    addOptionalParameter("reference", this.reference, optionalParameters);
    addOptionalParameter("rewriteurl", this.rewriteurl, optionalParameters);
    
    return optionalParameters;
  }
  
  private void addOptionalParameter(String key, String value, Map<String, String> optionalParameters) {
    if(value != null && !value.isEmpty()) {
      optionalParameters.put(key, value);
    }
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setHtml(String html) {
    this.html = html;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public void setRewriteurl(String rewriteurl) {
    this.rewriteurl = rewriteurl;
  }
}
