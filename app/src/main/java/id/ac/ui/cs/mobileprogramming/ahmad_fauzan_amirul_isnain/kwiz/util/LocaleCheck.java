package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.util;

import android.util.Log;

import java.util.Locale;

public class LocaleCheck {
    public static boolean isLocaleIndonesia() {
        Log.d("HOHO", String.valueOf(Locale.getDefault().getDisplayLanguage().equals("Indonesia")));
        return Locale.getDefault().getDisplayLanguage().equals("Indonesia");
    }
}
