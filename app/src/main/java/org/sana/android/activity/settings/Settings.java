
package org.sana.android.activity.settings;

import org.sana.R;
import org.sana.android.Constants;
import org.sana.android.app.UpdateManager;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

/**
 * Creates the settings window for specifying the Sana application. If a user
 * does not specify their own values, default values are used. Most of these are
 * stored in Constants. The default phone name is the phone's number. String
 * values are stored as preferences and can be retrieved as follows:
 * PreferenceManager.getDefaultSharedPreferences(c).getString("key name")
 * 
 * @author Sana Dev Team
 */
public class Settings extends PreferenceActivity {

    public static final String TAG = Settings.class.getSimpleName();

    /** {@inheritDoc} */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        initPreferences();
    }

    /** Sets the default values for the the preferences */
    private void initPreferences() {

        // Launches network preferences
        PreferenceScreen prefNetwork = (PreferenceScreen) findPreference("s_network_settings");
        if (prefNetwork != null) {
            prefNetwork.setIntent(new Intent(this, NetworkSettings.class));
        }

        // Launches resource preferences
        PreferenceScreen prefResource = (PreferenceScreen) findPreference("s_resource_settings");
        if (prefResource != null) {
            prefResource.setIntent(new Intent(this, ResourceSettings.class));
        }

        PreferenceScreen pref = (PreferenceScreen) findPreference(Constants.PREFERENCE_UPDATE);
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                UpdateManager.checkUpdate(getApplicationContext(), getString(R.string.key_update_secret));
                return true;
            }
        });
    }
}
