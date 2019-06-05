package com.android2ee.formation.librairies.actionbar.compat.sample;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android2ee.formation.librairies.actionbar.compat.R;

public class ActionModeActivity extends AppCompatActivity {
	ActionMode mMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		 //You could also hide the action Bar
//	       getSupportActionBar().hide();
		setContentView(R.layout.activity_action_mode);
		
		// Show the Up button in the action bar.
		findViewById(R.id.start_actionmode).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				enableActionMode();
			}
		});
		findViewById(R.id.stop_actionmode).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mMode != null) {
					//To quit the ActionMode
					mMode.finish();
				}
			}
		});
	}

	private void enableActionMode() {
		mMode = startSupportActionMode(new Callback() {
			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				getMenuInflater().inflate(R.menu.action_mode, menu);
				return true;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				Toast.makeText(ActionModeActivity.this, "Got click: " + item, Toast.LENGTH_SHORT).show();
				mode.finish();
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_with_items, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
