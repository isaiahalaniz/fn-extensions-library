package robj.extension.dummy;

import robj.floating.notifications.Extension;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.os.IBinder;

public class mService extends Service
{

	@Override
	public void onCreate()
	{
		super.onCreate();
		Extension.addOrUpdate(resizeBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)), 
								"Hello", 0, 0, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), 
								BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), 
								BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), false, false, false, this);
		stopSelf();
	}
	
	public Bitmap resizeBitmap(Bitmap image)
	{
		Paint paint = new Paint();
		paint.setFilterBitmap(true);
		
		Bitmap result = Bitmap.createBitmap(200, 200, Config.ARGB_8888);
		float ratioX = 200 / (float) image.getWidth();
		float ratioY = 200 / (float) image.getHeight();
		Matrix scaleMatrix = new Matrix();
		scaleMatrix.setScale(ratioX, ratioY, 1, 1);
		
		Canvas canvas = new Canvas();
		canvas.setBitmap(result);
		canvas.setMatrix(scaleMatrix);
		canvas.drawBitmap(image, 0,0, paint);
		  
		return result;
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}