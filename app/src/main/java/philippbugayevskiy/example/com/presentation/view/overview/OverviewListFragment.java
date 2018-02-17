package philippbugayevskiy.example.com.presentation.view.overview;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import philippbugayevskiy.example.com.R;
import philippbugayevskiy.example.com.presentation.di.components.UserComponent;
import philippbugayevskiy.example.com.presentation.model.Property;
import philippbugayevskiy.example.com.presentation.presenter.OverviewListPresenter;
import philippbugayevskiy.example.com.presentation.view.BaseFragment;
import philippbugayevskiy.example.com.presentation.view.adapter.OverViewAdapter;

public class OverviewListFragment extends BaseFragment implements OverviewListView{
    @Inject OverviewListPresenter presenter;

    OverViewAdapter adapter;
    List<Property> properties;

    public OverviewListFragment() {
        setRetainInstance(true);
    }

    public static Fragment newInstance(Intent intent) {
        final OverviewListFragment fragment = new OverviewListFragment();
        final Bundle args = new Bundle();

        // TODO: 2/17/18  handle parameters of sorting

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_overview_list, container, false);
        ButterKnife.bind(this, fragmentView);

        initRecyclerView();

        return fragmentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null == getArguments())
            return;

        // TODO: 2/17/18 handle  getArguments()
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        getComponent(UserComponent.class).inject(this);
        init();

        presenter.fetchData(0);
    }

    private void init() {
        presenter.setView(this);
    }

    private void initRecyclerView() {
        // TODO: 2/17/18 impl RecyclerView.
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        presenter.destroy();
    }

    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}

    @Override
    public void showRetry() {}

    @Override
    public void hideRetry() {}

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public void showSnackBarError(String message) {
        showSnackBarMessage(message);
    }
}
