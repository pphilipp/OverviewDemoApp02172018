package philippbugayevskiy.example.com.presentation.debug;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import philippbugayevskiy.example.com.presentation.di.NetworkEnv;
import philippbugayevskiy.example.com.presentation.view.adapter.EnumAdapter;
import philippbugayevskiy.example.com.presentation.view.preferences.IntPreference;

public class DebugAppContainer {
    final IntPreference networkEnvPrefs;

    public DebugAppContainer(@NetworkEnv IntPreference networkEnvPrefs) {
        this.networkEnvPrefs = networkEnvPrefs;
    }

    public void setupNetworkSection(Activity activity, Spinner spinner) {
        final EnumAdapter<EnvironmentEndpoint> adapter = new EnumAdapter<>(activity, EnvironmentEndpoint.class);
        spinner.setAdapter(adapter);
        spinner.setSelection(networkEnvPrefs.get());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                networkEnvPrefs.set(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}
