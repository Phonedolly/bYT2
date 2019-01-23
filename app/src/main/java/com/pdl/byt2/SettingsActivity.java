/*
 * Copyright (C) 2017 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pdl.byt2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pdl.byt2.R;

import com.pdl.byt2.utils.IntentUtils;
import com.pdl.byt2.utils.NetworkSecurityPolicyUtils;
import com.pdl.byt2.utils.PrefsUtils;
import com.pdl.byt2.utils.UiUtils;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    public static class MyPreferenceFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstance) {
            super.onCreate(savedInstance);
            addPreferencesFromResource(R.xml.settings);

            PreferenceCategory securityCategory = (PreferenceCategory)
                    findPreference("category_security");


            Preference clearCookie = findPreference("key_cookie_clear");
            clearCookie.setOnPreferenceClickListener(preference -> {
                CookieManager.getInstance().removeAllCookies(null);
                Toast.makeText(getContext(), getString(R.string.pref_cookie_clear_done),
                        Toast.LENGTH_LONG).show();
                return true;
            });

            SwitchPreference reachMode = (SwitchPreference) findPreference(("key_reach_mode"));
            if (UiUtils.isTablet(getContext())) {
                getPreferenceScreen().removePreference(reachMode);
            } else {
                reachMode.setOnPreferenceClickListener(preference -> {
                    Intent intent = new Intent(IntentUtils.EVENT_CHANGE_UI_MODE);
                    intent.putExtra(IntentUtils.EVENT_CHANGE_UI_MODE, reachMode.isChecked());
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                    return true;
                });
            }

            SwitchPreference clearTextTraffic = (SwitchPreference)
                    findPreference("key_clear_text_traffic");
            if (NetworkSecurityPolicyUtils.isSupported()) {
                clearTextTraffic.setOnPreferenceChangeListener((preference, value) -> {
                    NetworkSecurityPolicyUtils.setCleartextTrafficPermitted((Boolean) value);
                    return true;
                });
            } else {
                securityCategory.removePreference(clearTextTraffic);
            }
        }


    }
}
