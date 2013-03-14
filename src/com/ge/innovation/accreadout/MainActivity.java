package com.ge.innovation.accreadout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivity extends Activity implements SensorEventListener
{
  private float         mLastX, mLastY, mLastZ;
  private boolean       mInitialized;
  private SensorManager mSensorManager;
  private Sensor        mAccelerometer;
  private final float   NOISE = (float) 2.0;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mInitialized = false;
  
    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
  }
  
  protected void onResume()
  {
    super.onResume();
    mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
  }
  
  protected void onPause()
  {
    super.onPause();
    mSensorManager.unregisterListener(this);
  }
  
  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {}
  
  @Override
  public void onSensorChanged(SensorEvent event)
  {
    TextView tvX = (TextView)findViewById(R.id.x_axis);
    TextView tvY = (TextView)findViewById(R.id.y_axis);
    TextView tvZ = (TextView)findViewById(R.id.z_axis);
    
    float x = event.values[0];
    float y = event.values[1];
    float z = event.values[2];
    
    boolean sendToServer = false;
    
    if (mInitialized)
    {
      if (Math.abs(mLastX - x) > NOISE)
      {
        mLastX = x;
        tvX.setText(Float.toString(mLastX));
        sendToServer = true;
      }

      if (Math.abs(mLastY - y) > NOISE)
      {
        mLastY = y;
        tvY.setText(Float.toString(mLastY));
        sendToServer = true;
      }

      if (Math.abs(mLastZ - z) > NOISE)
      {
        mLastZ = z;
        tvZ.setText(Float.toString(mLastZ));
        sendToServer = true;
      }
    } else
    {
      mLastX = x;
      mLastY = y;
      mLastZ = z;
      tvX.setText(Float.toString(mLastX));
      tvY.setText(Float.toString(mLastY));
      tvZ.setText(Float.toString(mLastZ));
      sendToServer = true;
      mInitialized = true;
    }
    
    if (sendToServer)
    {
      //Send to server
    }
  }
}

