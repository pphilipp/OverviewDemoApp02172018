package philippbugayevskiy.example.com.presentation.view.overview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import philippbugayevskiy.example.com.R;

import philippbugayevskiy.example.com.presentation.di.HasComponent;
import philippbugayevskiy.example.com.presentation.di.components.DaggerUserComponent;
import philippbugayevskiy.example.com.presentation.di.components.UserComponent;
import philippbugayevskiy.example.com.presentation.navigation.Navigator;
import philippbugayevskiy.example.com.presentation.view.BaseActivity;

public class OverviewListActivity extends BaseActivity implements HasComponent<UserComponent>{
    private UserComponent userComponent;

    boolean doubleBackToExitPressedOnce = false;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_title) TextView tvSort;

    public static Intent createStartIntent(Context context) {
        return new Intent(context, OverviewListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_list);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initializeActivity(savedInstanceState);
        initializeInjector();
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, OverviewListFragment.newInstance(getIntent()));
        }
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public UserComponent getComponent() {
        return this.userComponent;
    }

    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
            super.onBackPressed();
            this.finishAffinity();
        } else {
            doubleBackToExitFlag();
        }
    }

    @OnClick(R.id.toolbar_title)
    public void sort(View view) {
        Navigator.navigateToSortActivity(this);
        finish();
    }

    private void doubleBackToExitFlag() {
        doubleBackToExitPressedOnce = true;

        Toast.makeText(this, getResources()
                .getString(R.string.double_click_to_back), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
