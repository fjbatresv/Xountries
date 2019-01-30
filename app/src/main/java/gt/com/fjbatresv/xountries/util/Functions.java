package gt.com.fjbatresv.xountries.util;

import android.content.Context;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Functions {

    private static final DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM - HH:mm");

    public static int calculateNoOfColumns(Context context, int width) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / width);
        return noOfColumns;
    }

}
