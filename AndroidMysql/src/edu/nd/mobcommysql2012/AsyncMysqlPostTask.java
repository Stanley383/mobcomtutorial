package edu.nd.mobcommysql2012;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncMysqlPostTask extends AsyncTask<Object, Integer, String> {
	private static final String TAG = AsyncMysqlPostTask.class.getSimpleName();
//	private final ProgressDialog dialog = ProgressDialog.show(sendToServerActivity, "", 
//            "Fetching Data. Please wait...", true);
	MainActivity callerActivity;
	
	protected void onProgressUpdate(Integer... progress) {
        setProgressPercent(progress[0]);
    }

    private void setProgressPercent(Integer integer) {
		// TODO Auto-generated method stub
		
	}

	protected void onPostExecute(String result) {
        if (!isCancelled()){
        	Log.d(TAG, result);
        	
        	//Log.d("All Products: ", result.toString());
        	//final String strings = "[{\"0\":\"-63.1446546073,1.0\"}]";
//            final int[] ints = new int[strings.length];
//            for (int i=0; i < strings.length; i++) {
//                ints[i] = Integer.parseInt(strings[i]);
//            }
        	
        	//Log.i(TAG,"result: "+ result.substring(0, 32));
        	
        	
        	//TODO: Need to parse data an plot it
        	//callerActivity.txtNbrOfTests.setText(result);
        	//callerActivity.drawTestFFT();
//        	var data = JSON.parse(result);
//        	for (var i=0; i<10; i++) {
//        		Log.i(TAG,"result: "+ data[i]);
//        	}
        
        	
        	//Toast.makeText(callerActivity.getBaseContext(), ""+result,Toast.LENGTH_SHORT).show();
        }
        	
    }

	private void showDialog(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doInBackground(Object... params) {
		
		String userName 	= (String) params[0];
		String userEmail 	= (String) params[1];
		String userPass 	= (String) params[2];
		//callerActivity = (MainActivity) params[3];

		PostDataService webService = new PostDataService(userName, userEmail, userPass);
		//return webService.cloudGetAvgFFt().toString();
//		JSONObject json = webService.phpPut();
		
		//return json;
		return webService.phpPut();
	}
}
/* Refernced work:
 * http://www.androidhive.info/2012/05/how-to-connect-android-with-php-mysql/
 *
 */