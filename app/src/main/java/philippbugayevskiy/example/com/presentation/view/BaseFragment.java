/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package philippbugayevskiy.example.com.presentation.view;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.widget.TextView;
import android.widget.Toast;

import philippbugayevskiy.example.com.presentation.di.HasComponent;

/**
 * Base {@link android.app.Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment extends Fragment {
    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT)
             .show();
    }

    /**
     * Shows a {@link android.support.design.widget.Snackbar} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showSnackBarMessage(String message) {
        Snackbar snackbar = Snackbar.make(this.getView(),
                message, Snackbar.LENGTH_SHORT);

        TextView text = snackbar.getView()
                                .findViewById( android.support.design.R.id.snackbar_text);
        text.setTextColor(Color.CYAN);
        snackbar.show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    public Context context() {
        return getActivity();
    }
}
