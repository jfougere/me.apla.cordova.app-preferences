package me.apla.cordova;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class AppPreferencesActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction().replace(
			android.R.id.content, new AppPFragment()
		).commit();
	}

	public static class AppPFragment extends PreferenceFragment
	{
		Activity app_activity;
		Context app_context;

		@Override
		public void onCreate(final Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);

			if (app_context == null) {
				app_context = app_activity.getApplicationContext();
			}

			String packageName = app_context.getPackageName();

			int resId = app_context.getResources().getIdentifier("apppreferences", "xml", packageName);

			if (resId > 0) {
				addPreferencesFromResource(resId);
			}

		}

		@Override
		public void onAttach(Activity act) {
			super.onAttach(act);
			app_activity = act;
		}

		@Override
		public void onAttach(Context ctx) {
			super.onAttach(ctx);
			app_context = ctx;
		}

		@Override
		public void onDetach() {
			super.onDetach();
			app_context  = null;
			app_activity = null;
		}
	}

}
