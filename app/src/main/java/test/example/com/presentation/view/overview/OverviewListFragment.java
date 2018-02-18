package test.example.com.presentation.view.overview;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.example.com.R;
import test.example.com.data.datasource.Constants;
import test.example.com.data.entity.ResPropertyEntity;
import test.example.com.presentation.di.components.UserComponent;
import test.example.com.presentation.presenter.OverviewListPresenter;
import test.example.com.presentation.view.BaseFragment;
import test.example.com.presentation.view.adapter.EndlessRecyclerViewScrollListener;
import test.example.com.presentation.view.adapter.OverviewAdapter;

public class OverviewListFragment extends BaseFragment implements OverviewListView{
    @Inject OverviewListPresenter presenter;

    @BindView(R.id.rv) RecyclerView rvProperties;
    private OverviewAdapter adapter;
    private List<ResPropertyEntity> properties;
    private EndlessRecyclerViewScrollListener scrollListener;
    private String order = Constants.EMPTY_STRING;

    public OverviewListFragment() {
        setRetainInstance(true);
    }

    public static Fragment newInstance(Intent intent) {
        final OverviewListFragment fragment = new OverviewListFragment();
        final Bundle args = new Bundle();
        args.putString(Constants.EXTRA_ORDER_ID, intent.getStringExtra(Constants.EXTRA_ORDER_ID));

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

        order = getArguments().getString(Constants.EXTRA_ORDER_ID, Constants.EMPTY_STRING);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initialize();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvProperties.setHasFixedSize(true);
        rvProperties.setLayoutManager(linearLayoutManager);
        properties = new ArrayList<>();

        adapter = new OverviewAdapter(getActivity(), properties);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.fetchData(page, order);
            }
        };

        rvProperties.addOnScrollListener(scrollListener);
        rvProperties.setAdapter(adapter);
    }

    private void initialize() {
        getComponent(UserComponent.class).inject(this);
        init();

        presenter.fetchData(0, order);
    }

    private void init() {
        presenter.setView(this);
    }

    @Override
    public void renderUI(List<ResPropertyEntity> properties) {
        this.properties.addAll(properties);
        adapter.notifyDataSetChanged();
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
