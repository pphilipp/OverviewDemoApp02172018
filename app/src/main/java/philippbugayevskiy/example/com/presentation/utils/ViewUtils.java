package philippbugayevskiy.example.com.presentation.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class ViewUtils {

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Resources r, int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * Converting dp to pixel
     */
    public static int pxToDp(Resources r, int px) {
        return Math.round(TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, px, r.getDisplayMetrics()));
    }
}
