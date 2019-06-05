package com.android2ee.formation.librairies.actionbar.compat.sample;

import android.os.Bundle;
import androidx.core.app.NavUtils;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBar.OnNavigationListener;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android2ee.formation.librairies.actionbar.compat.R;

public class NavListActivity extends AppCompatActivity implements OnNavigationListener {
	private SpinnerAdapter mSpinnerAdapter;
	 private TextView mSelected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nav_list);
		  mSelected = (TextView)findViewById(R.id.txv_nav_list);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.nav_list,
				android.R.layout.simple_spinner_dropdown_item);
		getSupportActionBar().setListNavigationCallbacks(mSpinnerAdapter, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see androidx.appcompat.app.ActionBar.OnNavigationListener#onNavigationItemSelected(int,
	 * long)
	 */
	@Override
	public boolean onNavigationItemSelected(int position, long itemId) {
		String item=(String) mSpinnerAdapter.getItem(position);
		mSelected.setText(mSelected.getText()+"\r\nNav Selection : "+item);
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nav_list, menu);
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
