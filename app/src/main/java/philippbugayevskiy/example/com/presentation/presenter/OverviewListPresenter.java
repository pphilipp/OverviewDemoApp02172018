package philippbugayevskiy.example.com.presentation.presenter;

import com.amatkivskiy.result.Result;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import philippbugayevskiy.example.com.R;
import philippbugayevskiy.example.com.data.entity.ReEntity;
import philippbugayevskiy.example.com.data.exception.MissingMetaDataException;
import philippbugayevskiy.example.com.domain.usecase.DefaultObserver;
import philippbugayevskiy.example.com.domain.usecase.GetPropertiesByPageNumberUseCase;
import philippbugayevskiy.example.com.presentation.view.overview.OverviewListView;

public class OverviewListPresenter implements Presenter  {
    private  OverviewListView view;
    private final CompositeDisposable disposables;
    GetPropertiesByPageNumberUseCase getPropertiesByPageNumberUseCase;

    @Inject
    public OverviewListPresenter(GetPropertiesByPageNumberUseCase getPropertiesByPageNumberUseCase) {
        this.getPropertiesByPageNumberUseCase = getPropertiesByPageNumberUseCase;
        disposables = new CompositeDisposable();
    }

    public void setView(OverviewListView view) {
        this.view = view;
    }

    public void fetchData(int pageNumber) {
        disposables.add(getPropertiesByPageNumberUseCase
                .lookForPageNumber(pageNumber)
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

    private final class GetPropertiesByPageNumberObserver extends DefaultObserver<Result<List<ReEntity>, Throwable>> {
        @Override
        public void onNext(Result<List<ReEntity>, Throwable> result) {
            super.onNext(result);

            if (!result.isSuccess()) {
                showError(result.error());
                return;
            }
            if (result.isEmpty()) {
                showError(new MissingMetaDataException(view.getContext().getString(R.string.no_property_available)));
                return;
            }

            List<ReEntity> resProperties = result.value();

            render(resProperties);
        }
    }

    private void render(List<ReEntity> resProperties) {
        // TODO: 2/17/18 renderList
    }

    private void showError(Throwable error) {
        view.showError(error.getMessage());
    }
}
