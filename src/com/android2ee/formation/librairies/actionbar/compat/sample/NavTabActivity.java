package com.android2ee.formation.librairies.actionbar.compat.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android2ee.formation.librairies.actionbar.compat.R;

public class NavTabActivity extends AppCompatActivity implements TabListener {
	   private TextView mSelected;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nav_tab);
		  mSelected = (TextView)findViewById(R.id.txv_nav_tab);

	        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	        for (int i = 1; i <= 3; i++) {
	            ActionBar.Tab tab = getSupportActionBar().newTab();
	            tab.setText("Tab " + i);
	            tab.setTabListener(this);
	            getSupportActionBar().addTab(tab);
	        }
	}
	/******************************************************************************************/
	/** Managing TabNavigation **************************************************************************/
	/******************************************************************************************/

	
	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBar.TabListener#onTabReselected(android.support.v7.app.ActionBar.Tab, android.support.v4.app.FragmentTransaction)
	 */
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		mSelected.setText(mSelected.getText()+"\r\nTabReselected "+arg0.getText());
		
	}

	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBar.TabListener#onTabSelected(android.support.v7.app.ActionBar.Tab, android.support.v4.app.FragmentTransaction)
	 */
	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {

		mSelected.setText(mSelected.getText()+"\r\nTabSelected "+arg0.getText());
	}

	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBar.TabListener#onTabUnselected(android.support.v7.app.ActionBar.Tab, android.support.v4.app.FragmentTransaction)
	 */
	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	/******************************************************************************************/
	/** Managing Menu **************************************************************************/
	/******************************************************************************************/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nav_tab, menu);
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
