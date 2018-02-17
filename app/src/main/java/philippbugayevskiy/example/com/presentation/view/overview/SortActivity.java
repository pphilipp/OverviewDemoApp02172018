package philippbugayevskiy.example.com.presentation.view.overview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import butterknife.ButterKnife;
import philippbugayevskiy.example.com.R;
import philippbugayevskiy.example.com.data.datasource.Constants;
import philippbugayevskiy.example.com.presentation.navigation.Navigator;
import philippbugayevskiy.example.com.presentation.view.BaseActivity;

public class SortActivity extends BaseActivity {
    private String order = Constants.RADIO_PRICE_DESCENDING;

    public static Intent createStartIntent(Context context) {
        return new Intent(context, SortActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_sort);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigateToSortedOverview(order);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_price_descending:
                if (checked)
                    order = Constants.RADIO_PRICE_DESCENDING;
                    break;
            case R.id.radio_price_ascending:
                if (checked)
                    order = Constants.RADIO_PRICE_ASCENDING;
                    break;
            case R.id.radio_beds_descending:
                if (checked)
                    order = Constants.RADIO_BEDS_DESCENDING;
                    break;
            case R.id.radio_beds_ascending:
                if (checked)
                    order = Constants.RADIO_BEDS_ASCENDING;
                    break;
        }
    }

    private void navigateToSortedOverview(String order) {
        Navigator.navigateToOverviewListActivity(this, order);
    }
}
