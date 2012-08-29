package edu.nd.mobcom;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private EditText firstlastName, userEmail, userPassWrd;
	private Button   registerUserBtn;
	private static final String TAG = MainActivity.class.getSimpleName();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // initialize widgets
        firstlastName 	= (EditText)findViewById(R.id.editText1);
        userEmail 		= (EditText)findViewById(R.id.editText2);
        userPassWrd 	= (EditText)findViewById(R.id.editText3);
        // set click listener
        registerUserBtn = (Button)findViewById(R.id.button1);
        registerUserBtn.setOnClickListener(this);
        
    }
    public void showToast(){
    	Toast.makeText(this, "Push to Mysql: Success", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		if ( v.getId() == R.id.button1){ 
			String name, email, passwd;
			name = firstlastName.getText().toString();
			email = userEmail.getText().toString();
			passwd = userPassWrd.getText().toString();
			Log.i(TAG,"n:"+name+",e:"+email+",p:"+passwd);
			
			AsyncUploadToMysql phpServiceTask = new AsyncUploadToMysql();
			phpServiceTask.execute(name,email,passwd,this);
			//Toast.makeText(this.getBaseContext(), ""+phpServiceTask.getStatus(), Toast.LENGTH_SHORT).show();
		}
		
	}
}
