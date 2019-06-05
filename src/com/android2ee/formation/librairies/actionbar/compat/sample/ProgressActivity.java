package com.android2ee.formation.librairies.actionbar.compat.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import com.android2ee.formation.librairies.actionbar.compat.R;

public class ProgressActivity extends AppCompatActivity {
	int progressEnd,progressStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("ProgressActivity", "onCreate");
		// ThisRequestFeature has to be called before setContentView
		supportRequestWindowFeature(Window.FEATURE_PROGRESS);
		supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_progress);
		progressEnd=Window.PROGRESS_END;
		progressStart=Window.PROGRESS_START;
		Log.e("ProgressActivity", "progressEnd= "+progressEnd+", progressStart="+progressStart);
		/******************************************************************************************/
		/** Managing the Top ProgressBar **************************************************/
		/******************************************************************************************/
		// Hide the progressBar
		setSupportProgressBarVisibility(true);
		setSupportProgress(progressEnd/2);
		// Run the top progressBar
		findViewById(R.id.start_progress).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				progressVisible = true;
				// show the top progressbar
				setSupportProgressBarVisibility(true);
				// and hide the menuItem (else it will appear and it appears anyway in Froyo)
				setSupportProgressBarIndeterminateVisibility(false);
				mProgress = 0;
				mProgressRunner.run();
			}
		});
		// Stop the top progressBar
		findViewById(R.id.stop_progress).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				progressVisible = false;
			}
		});

		/******************************************************************************************/
		/** Managing the MenuItem IndeterminateProgress **************************************************/
		/******************************************************************************************/
		// hide the menuItem
		setSupportProgressBarIndeterminateVisibility(false);
		// Start the MenuItem indeterminate progress
		findViewById(R.id.start_progress_indeterminate).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Hack to hide the regular progress bar
				setSupportProgress(progressEnd);
				setSupportProgressBarIndeterminateVisibility(true);
			}
		});
		// Stop the MenuItem indeterminate progress
		findViewById(R.id.stop_progress_indeterminate).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setSupportProgressBarIndeterminateVisibility(false);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.progress, menu);
		return true;
	}

	/******************************************************************************************/
	/** Managing the progress thread **************************************************************************/
	/******************************************************************************************/
	private int mProgress = 100;
	private boolean progressVisible = false;
	Handler mHandler = new Handler();
	Runnable mProgressRunner = new Runnable() {
		@Override
		public void run() {
			if (progressVisible) {
				mProgress += 2;
				// Normalize our progress along the progress bar's scale
				int progress = (progressEnd - progressStart) / 100 * mProgress;
				setSupportProgress(progress);
				if (mProgress < 100) {
					// Causes the Runnable r to be added to the message queue, to be run after the
					// specified amount of time elapses. The runnable will be run on the thread to
					// which
					// this handler is attached.
					mHandler.postDelayed(mProgressRunner, 50);
				}
			} else {
				setSupportProgressBarVisibility(false);
			}
		}
	};
}
