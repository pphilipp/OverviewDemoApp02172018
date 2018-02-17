package philippbugayevskiy.example.com.presentation.view.overview;

import java.util.List;

import philippbugayevskiy.example.com.data.entity.ReEntity;
import philippbugayevskiy.example.com.presentation.view.LoadDataView;

public interface OverviewListView extends LoadDataView {

    void renderUI(List<ReEntity> properties);
}
