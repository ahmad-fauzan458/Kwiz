package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;

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
                toast = Toast.makeText(context,
                        context.getResources().getString(R.string.connectivity_wifi), Toast.LENGTH_SHORT);
                toast.show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                toast = Toast.makeText(context,
                        context.getResources().getString(R.string.connectivity_mobile_data), Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            toast = Toast.makeText(context,
                    context.getResources().getString(R.string.connectivity_nothing), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
