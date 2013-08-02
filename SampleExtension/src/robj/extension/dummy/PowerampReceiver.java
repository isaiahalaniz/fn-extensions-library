package robj.extension.dummy;

import robj.floating.notifications.Extension;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.maxmpz.poweramp.player.PowerampAPI;

public class PowerampReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.v("PowerAmpExtension", "Receiver called..");
		context = context.getApplicationContext();
	
		if(Extension.isEnabled(context))
		{
			if(intent.getAction().equals(PowerampAPI.ACTION_AA_CHANGED)) {
				Bitmap albumArt = intent.getParcelableExtra(PowerampAPI.ALBUM_ART_BITMAP);
				Log.e("ALBUMART", albumArt == null ? "null" : "not null");
			}
		}
	}
	
//	private Bitmap getAlbumArt() {
//		String directAAPath = getStringExtra(PowerampAPI.ALBUM_ART_PATH);
//		
//		if(!TextUtils.isEmpty(directAAPath)) {
//			Log.w(TAG, "has AA, albumArtPath=" + directAAPath);			
//			
//			((ImageView)findViewById(R.id.album_art)).setImageURI(Uri.parse(directAAPath));
//		} else if(mAAIntent.hasExtra(PowerampAPI.ALBUM_ART_BITMAP)) {
//			
//			Bitmap albumArtBitmap = mAAIntent.getParcelableExtra(PowerampAPI.ALBUM_ART_BITMAP);
//			if(albumArtBitmap != null) {
//				Log.w(TAG, "has AA, bitmap");
//				((ImageView)findViewById(R.id.album_art)).setImageBitmap(albumArtBitmap);
//			}
//		} else {
//			Log.w(TAG, "no AA");
//			((ImageView)findViewById(R.id.album_art)).setImageBitmap(null);
//		}
//	}
	
}
