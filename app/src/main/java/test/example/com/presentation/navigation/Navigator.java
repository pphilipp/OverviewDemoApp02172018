/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.example.com.presentation.navigation;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import test.example.com.data.datasource.Constants;
import test.example.com.presentation.view.overview.OverviewListActivity;
import test.example.com.presentation.view.sort.SortActivity;


@Singleton
public class Navigator {

    @Inject
    public Navigator() {}

    public static void navigateToOverviewListActivity(@NonNull Context context, String order){
        context.startActivity(OverviewListActivity.createStartIntent(context)
                                                  .putExtra(Constants.EXTRA_ORDER_ID, order));
    }

    public static void navigateToSortActivity(@NonNull Context context){
        context.startActivity(SortActivity.createStartIntent(context));
    }
}
