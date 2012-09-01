package edu.nd.mobcommysql2012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class PostDataService{
	private static final String TAG = PostDataService.class.getSimpleName();
	DefaultHttpClient httpClient;
	private String 	  json = "";
	
	HttpResponse response = null;
	//HttpGet httpGet = null;
	StringBuilder builder = new StringBuilder();
	StringBuilder sb = null;
	InputStream is = null;
	String	userName, userEmail, userPass;
	
	//private static final String webServiceUrl 	= "http://spike.cse.nd.edu/neurobit/getdbstats.php";
	private static final String phpUrl = "http://192.168.1.148/neurobit/putuserregister.php";
	
	public PostDataService(String name, String email, String passwrd){
		HttpParams myParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(myParams, 10000);
		httpClient = new DefaultHttpClient();
		userName  = name;
		userEmail = email;
		userPass  = passwrd;
	}
	public String phpPut() { 
		boolean retVal=false;
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		String newtime =  sdfDateTime.format(new Date(System.currentTimeMillis()));
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name",userName));
		nameValuePairs.add(new BasicNameValuePair("email",userEmail));
		nameValuePairs.add(new BasicNameValuePair("passwrd",userPass));
		nameValuePairs.add(new BasicNameValuePair("datecreated",sdfDateTime.format(new Date())));
		
		Log.i(TAG,""+userName+","+userEmail+","+userPass);
		Log.i(TAG,""+sdfDateTime.format(new Date()));
		
		// http stuff
		try{
	        HttpPost httppost = new HttpPost(phpUrl);
	        //String paramString = URLEncodedUtils.format(nameValuePairs, "utf-8");
	        //HttpPost httpPost = new HttpPost(phpUrl+"?"+paramString);
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,HTTP.UTF_8));
	        response = httpClient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        //InputStream is = EntityUtils.toString(response.getEntity());
	        //returnedVecValues = EntityUtils.toByteArray(response.getEntity());
	        //returnedValue = EntityUtils.toString(response.getEntity());
	        //Log.e("log_tag", ""+returnedValue);
	        
		} catch (IOException e) {
			Log.e("WebService:", " Message: " +  e.getMessage());
		}catch(Exception e){	
	        Log.e(TAG, "Error in http connection "+e.toString());
		}
		//convert response to string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json=sb.toString();
            Log.i(TAG	,"resut: "+json);
            retVal = true;
        }catch(Exception e){
            Log.e(TAG, "Error converting result "+e.toString());
        }
		return json;
		
//        // try parse the string to a JSON object
//        try {
//            jObj = new JSONObject(json);
//            Log.i(TAG	,"try parse the string to a JSON object");
//        } catch (JSONException e) {
//            Log.e("JSON Parser", "Error parsing data " + e.toString());
//        }
// 
//        // return JSON String
//        return jObj;
	}
}	
	