package com.example.jorge.gymtrainer.Controllers;

import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


public class JSONParser {
    static InputStream is = null;
    static JSONObject jsonObj ;
    static String json = "";
    // default no argument constructor for jsonpaser class
    public JSONParser() {

    }


    public JSONObject makeHttpRequest(String url, String method,
                                      List<NameValuePair> params) {
        // Make HTTP request

        try {
            // checking request method
            if("POST".equals(method)){
                // now defaultHttpClient object
                //Base64 b = new Base64();
                //String encoding = b.encodeAsString(new String("sebaschilito@gmail.com:123456").getBytes());
                DefaultHttpClient httpClient = new DefaultHttpClient();
                Log.e("URL---",url);
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Accept", "application/json");
                //httpPost.setParams(params).;
                //httpPost.setHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("sebaschilito@gmail.com", "123456"), "UTF-8", false));
                //CredentialsProvider credsProvider = new BasicCredentialsProvider();
                //credsProvider.setCredentials(AuthScope.ANY,
                //new UsernamePasswordCredentials("sebaschilito@gmail.com", "123456"));

                //AuthCache authCache = new BasicAuthCache();
                // authCache.put(httpClient, new BasicScheme());
                try{
                    String json = "";

                    // 3. build jsonObject
                    JSONObject jsonObject = new JSONObject();
                    for (int i = 0; i < params.size(); i++){
                        jsonObject.accumulate(params.get(i).getName(), params.get(i).getValue());
                    }
                    // 4. convert JSONObject to JSON to String
                    json = jsonObject.toString();
                    Log.e("JSON A ENVIAR: ", json);
                    // ** Alternative way to convert Person object to JSON string usin Jackson Lib
                    // ObjectMapper mapper = new ObjectMapper();
                    // json = mapper.writeValueAsString(person);

                    // 5. set json to StringEntity
                    StringEntity se = new StringEntity(json);
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    // 6. set httpPost Entity
                    httpPost.setEntity(se);
                }catch (JSONException e){e.printStackTrace();}


                //httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                Log.e("Parameteres POST---",""+params.size());
                Log.e("TEST ---: ", httpPost.getRequestLine().toString());
                Header headers[] = httpPost.getAllHeaders();
                for(int i=0; i < httpPost.getAllHeaders().length; i++ ){
                    Log.e("HEADER ---: ", headers[i].toString());
                }

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }else if("GET".equals(method)) {

                if(params == null){
                    // request method is GET Without params
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(url);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                    System.out.println("GET URL JSONParser : " + url);

                }else{
                    // request method is GET
                    HttpClient httpClient = new DefaultHttpClient();
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    url += "?" + paramString;
                    HttpGet httpGet = new HttpGet(url);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                    System.out.println("URL GET PARAMS JSONParser : " + url);
                }

            } else if("PUT".equals(method)){

                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPut httpPut = new HttpPut(url);
                httpPut.setHeader("Accept", "application/json");
                try{
                    String json = "";
                    JSONObject jsonObject = new JSONObject();
                    for (int i = 0; i < params.size(); i++){
                        jsonObject.accumulate(params.get(i).getName(), params.get(i).getValue());
                    }
                    json = jsonObject.toString();
                    StringEntity se = new StringEntity(json);
                    httpPut.setEntity(se);
                }catch (JSONException e){e.printStackTrace();}
                HttpResponse httpResponse = httpClient.execute(httpPut);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            }else if("DELETE".equals(method)){
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpDelete httpDelete = new HttpDelete(url);
                HttpResponse httpResponse = httpClient.execute(httpDelete);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                System.out.println("URL DELETE PARAMS JSONParser : " + url);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder str = new StringBuilder();
            String strLine = null;
            while ((strLine = reader.readLine()) != null) {
                str.append(strLine + "\n");
            }
            is.close();
            json = str.toString();
            System.out.println(json);
        } catch (Exception e) {

        }
        try {
            jsonObj = new JSONObject(json);
        } catch (JSONException e) {
            System.err.println("Error json, JSONParser 170" + e.toString());
        }
        return jsonObj;
    }

}

