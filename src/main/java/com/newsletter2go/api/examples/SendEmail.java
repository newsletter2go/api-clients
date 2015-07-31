package com.newsletter2go.api.examples;

import com.newsletter2go.api.Email;
import com.newsletter2go.api.exceptions.MissingParamException;
import com.newsletter2go.api.rest.ApiResult;

/**
 * This is a test class to demonstrate how an email can be sent using the Newsletter2Go API and some
 * other classes. uses: Post_Request.java, SendEmail.java, ApiResult.java,
 * MissingParamException.java, Google Gson Lib
 * 
 * @author Newsletter2Go-Dev-Team. Questions? => dev@newsletter2go.com
 */

public class SendEmail {

  // zu finden unter Einstellungen->Schnittstellen
  static final String API_KEY = "your_api_key";
  
  public static void main(String[] args) {
    Email email = new Email(API_KEY);

    String html = "<html><body>Test</body></html>";

    String text =
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.";

    // required params:
    email.addParam(Email.params.TO, "support@newsletter2go.de");
    email.addParam(Email.params.FROM, "support@newsletter2go.de");
    email.addParam(Email.params.SUBJECT, "I Don't Like Mondays");
    email.addParam(Email.params.HTML, html);
    email.addParam(Email.params.TEXT, text);
    // some optionial params:
    email.addParam(Email.params.REF, "TellMeWhy");
    email.addParam(Email.params.ID, "4242");

    try {
      ApiResult result = email.send();
      // print the result or check success or do whatever u like:
      System.out.println(result);
    } catch (MissingParamException e) {
      e.printStackTrace();
    }
  }
}
