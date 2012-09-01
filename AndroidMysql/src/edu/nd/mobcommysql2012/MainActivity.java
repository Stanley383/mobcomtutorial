package edu.nd.mobcommysql2012;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button btn;
	private EditText fullnameET, emailET, passET;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    private void initializeUI(){
    	fullnameET  = (EditText) findViewById(R.id.editText1);
    	emailET		= (EditText) findViewById(R.id.editText2);
    	passET		= (EditText) findViewById(R.id.editText3);
    	btn 		= (Button)   findViewById(R.id.button1);
    	btn.setOnClickListener(new OnClickListener(){
    		@Override
			public void onClick(View arg0) {
    			if (arg0.getId() == R.id.button1) {
    				if (editTextFieldsNotEmpty() ){
    					AsyncMysqlPostTask postServiceTask = new AsyncMysqlPostTask();
						postServiceTask.execute(fullnameET.getText().toString(), 
											    emailET.getText().toString(),
											    passET.getText().toString(),
											    this);
    				}
    			}
			}
    	});
    }
    private boolean editTextFieldsNotEmpty(){
    	if ( (fullnameET.getText().toString().isEmpty()) ||
    		 (emailET.getText().toString().isEmpty()) || 
    		 (passET.getText().toString().isEmpty())){
    		return false;
    	} else 
    		return true;
    }
}
