/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package test.example.com.presentation.view;

import android.content.Context;

/**
 * Interface representing a View that will use to load data.
 */
public interface LoadDataView {
    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    void showRetry();

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    void hideRetry();

    /**
     * Show an error message as Toast
     *
     * @param message A string representing an error.
     */
    void showError(String message);

    /**
     * Show an error message as a SnackBarr
     *
     * @param message A string representing an error.
     */
    void showSnackBarError(String message);

    /**
     * Get a {@link android.content.Context}.
     */
    Context getContext();
}
