package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private Toast toast;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (this.toast != null){
            toast.cancel();
        }

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                toast = Toast.makeText(context, "Wifi enabled", Toast.LENGTH_LONG);
                toast.show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                toast = Toast.makeText(context, "Mobile data enabled", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            toast = Toast.makeText(context, "No internet is available", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
