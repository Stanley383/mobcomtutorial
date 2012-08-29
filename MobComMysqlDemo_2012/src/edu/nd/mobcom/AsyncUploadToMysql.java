package edu.nd.mobcom;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import edu.nd.mobcom.MainActivity;

public class AsyncUploadToMysql extends AsyncTask<Object, Integer, String> {
	private static final String TAG = AsyncUploadToMysql.class.getSimpleName();

	public MainActivity callerActivity;
	
	protected void onProgressUpdate(Integer... progress) {
        setProgressPercent(progress[0]);
    }

    private void setProgressPercent(Integer integer) {
		// TODO Auto-generated method stub
		
	}
//    protected void onPreExecute(String key, String value) {
//        //store the pair of values into the ArrayList 
//        //nameValuePairs.add(new BasicNameValuePair(key,value));
//    }
	protected void onPostExecute(String result) {
        
        if (!isCancelled()){
        	//callerActivity.drawTestFFT();
        	Log.i(TAG,"Result: "+result);
        	if ( result.isEmpty() ) {
        		try{
        			callerActivity.showToast();
        		}catch(Exception e){

        		Log.d("Error: ", " "+e.getMessage());

        		}


        	}
        		
        	
        	// Load a test record here:
        }
        super.onPostExecute(result);	
    }

	@Override
	protected String doInBackground(Object... params) {
		String userName 	= (String) params[0];
		String userEmail 	= (String) params[1];
		String userPass 	= (String) params[2];
		callerActivity = (MainActivity) params[3];
		
		Log.i(TAG,"n:"+userName+",e:"+userEmail+",p:"+userPass);
		UploadDataService phpService = new UploadDataService(userName, userEmail, userPass );
		return phpService.phpPut();   
	}
}
