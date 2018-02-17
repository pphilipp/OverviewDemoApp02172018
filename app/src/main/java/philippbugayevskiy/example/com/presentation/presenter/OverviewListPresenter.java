package philippbugayevskiy.example.com.presentation.presenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import philippbugayevskiy.example.com.presentation.view.overview.OverviewListView;

public class OverviewListPresenter implements Presenter  {
    private  OverviewListView view;
    private final CompositeDisposable disposables;

    @Inject
    public OverviewListPresenter() {
        disposables = new CompositeDisposable();
    }

    public void setView(OverviewListView view) {
        this.view = view;
    }

    public void fetchData(String pageNumber) {
        // TODO: 2/17/18 create UseCase
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        disposables.dispose();
        view = null;
    }
}
