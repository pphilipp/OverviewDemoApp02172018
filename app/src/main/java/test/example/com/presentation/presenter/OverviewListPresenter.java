package test.example.com.presentation.presenter;

import com.amatkivskiy.result.Result;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import test.example.com.R;
import test.example.com.data.entity.ResPropertyEntity;
import test.example.com.data.exception.MissingMetaDataException;
import test.example.com.domain.usecase.DefaultObserver;
import test.example.com.domain.usecase.GetPropertiesByPageNumberUseCase;
import test.example.com.presentation.view.overview.OverviewListView;

public class OverviewListPresenter implements Presenter {
    private OverviewListView view;
    private final CompositeDisposable disposables;
    private GetPropertiesByPageNumberUseCase getPropertiesByPageNumberUseCase;

    @Inject
    public OverviewListPresenter(GetPropertiesByPageNumberUseCase getPropertiesByPageNumberUseCase) {
        this.getPropertiesByPageNumberUseCase = getPropertiesByPageNumberUseCase;
        disposables = new CompositeDisposable();
    }

    public void setView(OverviewListView view) {
        this.view = view;
    }

    public void fetchData(int pageNumber, String order) {
        disposables.add(getPropertiesByPageNumberUseCase
                .lookForPageNumber(pageNumber)
                .lookForOrder(order)
                .getConfiguredObservable()
                .subscribeWith(new GetPropertiesByPageNumberObserver()));
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

    private final class GetPropertiesByPageNumberObserver extends DefaultObserver<Result<List<ResPropertyEntity>, Throwable>> {
        @Override
        public void onNext(Result<List<ResPropertyEntity>, Throwable> result) {
            super.onNext(result);

            if (!result.isSuccess()) {
                showError(result.error());
                return;
            }
            if (result.isEmpty()) {
                showError(new MissingMetaDataException(view.getContext().getString(R.string.no_property_available)));
                return;
            }

            List<ResPropertyEntity> resProperties = result.value();

            render(resProperties);
        }
    }

    private void render(List<ResPropertyEntity> resProperties) {
        view.renderUI(resProperties);
    }

    private void showError(Throwable error) {
        view.showError(error.getMessage());
    }
}
