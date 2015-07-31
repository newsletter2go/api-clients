package com.newsletter2go.api.examples;

import com.newsletter2go.api.EmailNewsletter;
import com.newsletter2go.api.rest.ApiResult;

/**
 * This is a test class to demonstrate how an email can be sent using the Newsletter2Go API and some
 * other classes. uses: Post_Request.java, SendEmail.java, ApiResult.java,
 * MissingParamException.java, Google Gson Lib
 * 
 * @author Newsletter2Go-Dev-Team
 *
 * Questions? => dev@newsletter2go.com
 */

public class CreateNewsletter {

  // zu finden unter Einstellungen->Schnittstellen
  static final String API_KEY = "your_api_key";
  
  public static void main(String[] args) {
    String newsletterName = "Aktionsmailing 2015/5";
    
    String html = "<html><body>HTML Body</body></html>";
    String text =
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.";
    String subject = "Aktionen im Mai";
    
    EmailNewsletter newsletter = new EmailNewsletter(API_KEY, newsletterName);

    newsletter.setHtml(html);
    newsletter.setText(text);
    newsletter.setSubject(subject);
    
    ApiResult result = newsletter.doCreate();
    
    System.out.println(result);
  }
}
