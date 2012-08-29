package edu.nd.mobcom;

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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import android.util.Log;

public class UploadDataService{
	private static final String TAG = UploadDataService.class.getSimpleName();
	DefaultHttpClient httpClient;
	String returnedValue;

	HttpResponse response = null;
	HttpGet httpGet = null;
	StringBuilder builder = new StringBuilder();
	StringBuilder sb = null;
	InputStream is = null;
	String	userId, userName, userPass;
	
	private static final String phpServiceUrl = "http://192.168.1.148/neurobit/putuserregister.php";
	
	public UploadDataService(String nameStr, String mailStr, String passStr /* email id */){
		HttpParams myParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(myParams, 10000);
		httpClient = new DefaultHttpClient();
		userName = nameStr;
		userId = mailStr;
		userPass = passStr;
	}
	
	public String phpPut() { 
		boolean retVal=false;
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		//String newtime =  sdfDateTime.format(new Date(System.currentTimeMillis()));
		Log.i(TAG, "n:"+userName+",m:"+userId+",p:"+userPass);

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name",	userName));
		nameValuePairs.add(new BasicNameValuePair("email",	userId));
		nameValuePairs.add(new BasicNameValuePair("passwrd",userPass));
		nameValuePairs.add(new BasicNameValuePair("datecreated", sdfDateTime.format(new Date())));
		
		// http stuff
		try{
	        HttpPost httppost = new HttpPost(phpServiceUrl);
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
            returnedValue=sb.toString();
            Log.i(TAG	,"resut: "+returnedValue);
            retVal = true;
        }catch(Exception e){
            Log.e(TAG, "Error converting result "+e.toString());
        }

		
		return returnedValue;
	}
	/*
	public String webGet() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("email",userId));
		try{
	        //HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(phpServiceUrl);
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
            returnedValue=sb.toString();
            //Log.i(TAG	,"resut: "+returnedValue);
        }catch(Exception e){
            Log.e(TAG, "Error converting result "+e.toString());
        }
		/ *
		httpGet = new HttpGet(webServiceUrl);
		
		Log.e("WebGetURL: ",webServiceUrl);
		try {
			response = httpClient.execute(httpGet);
			returnedValue = EntityUtils.toString(response.getEntity());
			
		} catch (IOException e) {
			Log.e("WebService:", " Messaje " +  e.getMessage());
		} catch (Exception e) {
			Log.e("WebService:", " Messaje " + e.getMessage());
		}
		x
		return returnedValue;
	}*/
}