package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.util;

import java.util.Locale;

public class LocaleCheck {
    public static boolean isLocaleIndonesia() {
        return Locale.getDefault().getDisplayLanguage().equals("Indonesia");
    }
}
