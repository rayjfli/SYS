package com.example.sysinfo;

import java.io.IOException;
import java.io.RandomAccessFile;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView imei, ram;
	private Button getButton;
	TelephonyManager    telephonyManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imei = (TextView) findViewById(R.id.imeiLable);
		ram = (TextView) findViewById(R.id.ramLable);
		getButton = (Button) findViewById(R.id.button1);

		telephonyManager  =  ( TelephonyManager ) getSystemService( Context.TELEPHONY_SERVICE );
		
		getButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Display display = getWindowManager().getDefaultDisplay();
				DisplayMetrics metrics = new DisplayMetrics();
				display.getMetrics(metrics);
				Point size = new Point();
				display.getSize(size);
				int width = size.x;
				int height = size.y;
				
				
				imei.setText(telephonyManager.getDeviceId());
				//imei.setText(metrics.toString());
				
			    RandomAccessFile reader = null;
			    String load = null;
			    try {
			        reader = new RandomAccessFile("/proc/meminfo", "r");
			        load = reader.readLine();
			        reader.close();
			    } catch (IOException ex) {
			        ex.printStackTrace();
			    }
			    ram.setText(android.os.Build.SERIAL);
			}
			
		}
		);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
