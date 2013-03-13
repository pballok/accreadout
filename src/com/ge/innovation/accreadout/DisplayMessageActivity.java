package com.ge.innovation.accreadout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class DisplayMessageActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    Intent intent = getIntent();
    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

    TextView textView = new TextView(this);
    textView.setTextSize(40);
    textView.setText(message);

    setContentView(textView);

    // Show the Up button in the action bar.
    getActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
      }
    return super.onOptionsItemSelected(item);
  }
}


