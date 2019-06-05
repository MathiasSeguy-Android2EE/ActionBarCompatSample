package com.android2ee.formation.librairies.actionbar.compat.sample;

import android.os.Bundle;
import androidx.core.app.NavUtils;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.MenuItemCompat.OnActionExpandListener;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android2ee.formation.librairies.actionbar.compat.R;

public class ActionViewActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_view);
	}
	MenuItem menuItemActionView;
	LinearLayout lilActionView;
	EditText edtActionView;
	ImageButton btnActionView;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionview, menu);
		menuItemActionView = menu.findItem(R.id.menu_item_actionview);
		lilActionView = (LinearLayout) MenuItemCompat.getActionView(menuItemActionView);
		edtActionView = (EditText) lilActionView.findViewById(R.id.edtActionView);
		btnActionView = (ImageButton) lilActionView.findViewById(R.id.btnActionView);
		btnActionView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				actionOfTheActionView();
			}
		});
		// When using the support library, the setOnActionExpandListener() method is
		// static and accepts the MenuItem object as an argument
		MenuItemCompat.setOnActionExpandListener(menuItemActionView, new OnActionExpandListener() {
			@Override
			public boolean onMenuItemActionCollapse(MenuItem item) {
				// Do something when collapsed
				Log.e("ActionViewActivity", "menu_item_actionview collapsing");
				return true; // Return true to collapse action view
			}

			@Override
			public boolean onMenuItemActionExpand(MenuItem item) {
				// Do something when expanded
				Log.e("ActionViewActivity", "menu_item_actionview extending");
				return true; // Return true to expand action view
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Handling Action from the btnActionView contained by the ActionView
	 */
	private void actionOfTheActionView() {
		Log.e("ActionViewActivity", "ActionView edt "+edtActionView.getText().toString());
		Toast.makeText(this, "ActionView edt = "+edtActionView.getText().toString(), Toast.LENGTH_SHORT).show();
		MenuItemCompat.collapseActionView(menuItemActionView);
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
		case R.id.menu_item_actionview:
			// Because the system expands the action view when the user selects the action, you do
			// not need to respond to the item in the onOptionsItemSelected() callback. The system
			// still calls onOptionsItemSelected(), but if you return true (indicating you've
			// handled the event instead), then the action view will not expand.
			Log.e("ActionViewActivity", "menu_item_actionview get");
			return false;
		}
		return super.onOptionsItemSelected(item);
	}
}
