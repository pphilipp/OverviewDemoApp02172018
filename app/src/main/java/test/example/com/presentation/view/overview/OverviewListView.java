package test.example.com.presentation.view.overview;

import java.util.List;

import test.example.com.data.entity.ResPropertyEntity;
import test.example.com.presentation.view.LoadDataView;

public interface OverviewListView extends LoadDataView {

    void renderUI(List<ResPropertyEntity> properties);
}
