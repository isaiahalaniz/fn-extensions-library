package robj.extension.dummy;

import robj.floating.notifications.Extension;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings.Global;
import android.util.Log;

public class ActionReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent)
	{
		try {
		    if (intent.getAction().equals(Extension.INTENT))
		    {
		    	switch(intent.getIntExtra(Extension.ACTION, -1))
				{
					case 0:
						Extension.remove(intent.getLongExtra(Extension.ID, -1), context);
						break;
					case 1:
						Extension.hideAll(intent.getLongExtra(Extension.ID, -1), context);
						break;
					case 2:
						Extension.hideAll(intent.getLongExtra(Extension.ID, -1), context);
						break;
					case 3:
						Extension.hideAll(intent.getLongExtra(Extension.ID, -1), context);
						break;
				}
		    } 
		  } catch (Exception e) { Log.e("BroadcastReceiver", "Some error occured.."); }
	}

}
