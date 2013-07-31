package robj.floating.notifications;

import robj.floating.notifications.Extension;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class EnabledReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.v("Extension", "Enabled called..");
		context = context.getApplicationContext();
	
	    if (intent.getAction().equals("robj.floating.notifications.extension.ENABLED"))
	    {
	    	Extension.setEnabled(context, true);
		} else if (intent.getAction().equals("robj.floating.notifications.extension.DISABLED")) {
			Extension.setEnabled(context, false);
		}
	}
	
}
