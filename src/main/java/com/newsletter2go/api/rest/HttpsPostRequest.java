package com.newsletter2go.api.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import com.google.gson.Gson;

/**
 * This class provides a method to do https-post-requests using the java URL class
 * 
 * @author Newsletter2Go-Dev-Team
 * 
 * Questions? => dev@newsletter2go.com
 */
public class HttpsPostRequest {

  /*
   * You may want to use a Lib for that, e.g. Apache's HTTPClient
   */
  public ApiResult doRequest(String endpointUrl, String apiKey, Map<String, String> requiredParameters,
      Map<String, String> optionalParameters) {

    try {
      // Construct the post data
      StringBuilder data = new StringBuilder();
      
      // add auth info:
      data.append(URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(apiKey, "UTF-8"));      
      data.append("&");
      
      // add required params:
      for (Map.Entry<String, String> entry : requiredParameters.entrySet()) {
        // make param keys toLowerCase, cause API requires
        data.append(URLEncoder.encode(entry.getKey().toString().toLowerCase(), "UTF-8") + "="
            + URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
        data.append("&");
      }
      
      // add optional params, if available
      for (Map.Entry<String, String> entry : optionalParameters.entrySet()) {
        if (entry.getValue() != null) {
          // make param keys toLowerCase, cause API requires
          data.append(URLEncoder.encode(entry.getKey().toString().toLowerCase(), "UTF-8") + "="
              + URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
          data.append("&");
        }
      }
      // trim last ampersand
      data.deleteCharAt(data.length() - 1);

      // Send data via post
      URL url = new URL(endpointUrl);
      URLConnection conn = url.openConnection();
      conn.setDoOutput(true);
      OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
      wr.write(data.toString());
      wr.flush();
      
      // Get the response:
      BufferedReader rd =
          new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String json = "";
      String line;
      while ((line = rd.readLine()) != null) {
        // get JSON result from Newsletter2Go API:
        json += line;
      }

      // decode json to an ApiResult object
      Gson gson = new Gson();
      ApiResult apiResult = gson.fromJson(json, ApiResult.class);
      
      // close streams:
      wr.close();
      rd.close();
      
      return apiResult;
    } catch (Exception e) {
      e.printStackTrace();
      // TODO you better shouldnt do this in a productive environment
      return null;
    }

  }

}
