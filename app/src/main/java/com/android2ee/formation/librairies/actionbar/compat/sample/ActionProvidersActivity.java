package com.android2ee.formation.librairies.actionbar.compat.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.core.view.ActionProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android2ee.formation.librairies.actionbar.compat.R;

public class ActionProvidersActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_providers);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_providers, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// If this callback does not handle the item click, onPerformDefaultAction
		// of the ActionProvider is invoked. Hence, the provider encapsulates the
		// complete functionality of the menu item.
		Toast.makeText(this, "Handling in onOptionsItemSelected avoided", Toast.LENGTH_SHORT).show();
		return false;
	}

	/******************************************************************************************/
	/** ActionProvider Code **************************************************************************/
	/******************************************************************************************/
	public static class AirPlaneActionProvider extends ActionProvider {

		/** An intent for launching the system settings. */
		private static final Intent sAirPlaneIntent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
		/** An intent for launching the system settings. */
		private static final Intent sSettingIntent = new Intent(Settings.ACTION_SETTINGS);
		/** An intent for launching the system settings. */
		private static final Intent sDictionnaryIntent = new Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS);

		/** Context for accessing resources. */
		private final Context mContext;
		/**
		 * The layout that carries the others buttons
		 */
		LinearLayout lilOthersButtons;
		/**
		 * 
		 */
		ImageButton buttonExtend;
		/**
		 * 
		 */
		boolean isExtended=false;
		/**
		 * Creates a new instance.
		 * 
		 * @param context
		 *            Context for accessing resources.
		 */
		public AirPlaneActionProvider(Context context) {
			super(context);
			mContext = context;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public View onCreateActionView() {
			// Inflate the action view to be shown on the action bar.
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);
			View view = layoutInflater.inflate(R.layout.action_provider_view, null);
			 buttonExtend = (ImageButton) view.findViewById(R.id.button_action_provider_extend);
			ImageButton buttonAction1 = (ImageButton) view.findViewById(R.id.button_action_provider1);
			ImageButton buttonAction2 = (ImageButton) view.findViewById(R.id.button_action_provider2);
			ImageButton buttonAction3 = (ImageButton) view.findViewById(R.id.button_action_provider3);
			lilOthersButtons=(LinearLayout)view.findViewById(R.id.lil_other_action);
			// Attach a click listener for launching the system settings.
			buttonExtend.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					setVisible();
				}
			});
			buttonAction1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					action1();
				}
			});
			buttonAction2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					action2();
				}
			});
			buttonAction3.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					action3();
				}
			});
			return view;
		}
		
		/**
		 * The action 1
		 */
		private void action1() {
			mContext.startActivity(sAirPlaneIntent);
		}
		/**
		 * The action 2
		 */
		private void action2() {
			mContext.startActivity(sDictionnaryIntent);
		}
		/**
		 * The action 3
		 */
		private void action3() {
			mContext.startActivity(sSettingIntent);
		}

		/**
		 * SetTheVisibility of the rest of the linearLayout
		 */
		private void setVisible() {
			if(isExtended) {
				lilOthersButtons.setVisibility(View.GONE);
				buttonExtend.setBackgroundResource(R.drawable.ic_action_provider_extends);
			}else {
				lilOthersButtons.setVisibility(View.VISIBLE);
				buttonExtend.setBackgroundResource(R.drawable.ic_action_provider_collapse);
			}
			isExtended=!isExtended;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean onPerformDefaultAction() {
			// This is called if the host menu item placed in the overflow menu of the
			// action bar is clicked and the host activity did not handle the click.
			mContext.startActivity(sAirPlaneIntent);
			return true;
		}
	}

}
