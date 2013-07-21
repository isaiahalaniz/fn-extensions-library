package robj.floating.notifications;

import robj.floating.notificationsextensionlibrary.R;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Extension {
	
	public static String INTENT = "robj.floating.notifications.extension";
	
	private static final String WINDOWSTATECHANGED = "windowStateChanged";
	
	private static String PACKAGE = "0";
	
	private static String ACTIONONE = "1";
	private static String ACTIONTWO = "2";
	private static String ACTIONTHREE = "3";
	
	public static String ACTION = "4";
	public static String ID = "5";
	private static String MSG = "6";
	private static String IMG = "7";
	
	private static String PERSISTENT = "8";
	private static String STACK = "9";
	
	public static int ACTION_0 = 0;
	public static int ACTION_1 = 1;
	public static int ACTION_2 = 2;
	public static int ACTION_3 = 3;
	
	private static int ADDORUPDATE = 0;
	private static int HIDEALL = 1;
	private static int REMOVE = 2;
	
	public static void addOrUpdate(final Bitmap image, final String message, final long id,
	final Bitmap actionOne, final Bitmap actionTwo, final Bitmap actionThree, final boolean persistent, final boolean stack, Context context)
{
	if(image == null) {
		Log.e("Extension", "Image is null..");
	} else if(message == null) {
		Log.e("Extension", "Message is null..");
	} else if(image.getWidth() != 200 || image.getHeight() != 200) {
		Log.e("Extension", "Image needs to be 200 by 200..");
	} else {
					try {
						Log.v("Extension", "Notifying..");
						
						Intent intent = new Intent();
						intent.setAction(INTENT);
						intent.setPackage("robj.floating.notifications");
						intent.putExtra(PACKAGE, context.getPackageName());
						intent.putExtra(ACTION, ADDORUPDATE);
						
						intent.putExtra(ID, id);
						intent.putExtra(MSG, message);
						intent.putExtra(IMG, image);
						intent.putExtra(ACTIONONE, actionOne);
						intent.putExtra(ACTIONTWO, actionTwo);
						intent.putExtra(ACTIONTHREE, actionThree);
						intent.putExtra(PERSISTENT, persistent);
						intent.putExtra(STACK, stack);
						
						context.sendBroadcast(intent, "robj.floating.notifications.READ_DATA");			
					} catch (Exception e) {
						Log.e("Extension", "Unable to notify: " + e.toString());
						e.printStackTrace();
					}
	}
	}
	
	public static void remove(long id, Context context)
	{
		Intent intent = new Intent();
		intent.setAction(INTENT);
		intent.setPackage("robj.floating.notifications");
		intent.putExtra(PACKAGE, context.getPackageName());
		intent.putExtra(ACTION, REMOVE);
		intent.putExtra(ID, id);
		context.sendBroadcast(intent, "robj.floating.notifications.READ_DATA");//.sendBroadcast(intent);
	}
	
	public static void hideAll(long id, Context context)
	{
		Intent intent = new Intent();
		intent.setAction(INTENT);
		intent.setPackage("robj.floating.notifications");
		intent.putExtra(PACKAGE, context.getPackageName());
		intent.putExtra(ACTION, HIDEALL);
		intent.putExtra(ID, id);
		context.sendBroadcast(intent, "robj.floating.notifications.READ_DATA");
	}
	
	public static void replyOverlay(String edittextHint, String previousText, 
										Bitmap image, final onClickListener imageOnClick, final onClickListener sendOnClick, Context context, boolean lightTheme)
	{
		final WindowManager windowManager = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		final RelativeLayout view = (RelativeLayout) inflater.inflate(lightTheme ? R.layout.activity_reply : R.layout.activity_reply_dark, null);
		
		final EditText et = ((EditText) view.findViewById(R.id.etReply));
		et.setHint(edittextHint);

		TextView message = ((TextView) view.findViewById(R.id.tvMessage));
		message.setMovementMethod(new ScrollingMovementMethod());
		message.setText(previousText);
		
		((ImageView) view.findViewById(R.id.imgContact)).setImageBitmap(image); //image link to contact card
		((ImageView) view.findViewById(R.id.imgContact)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				if(imageOnClick != null) { imageOnClick.onClick(); windowManager.removeView(view);}
			}
		});

		((ImageView) view.findViewById(R.id.btnCancel)).setVisibility(View.VISIBLE);
		((ImageButton) view.findViewById(R.id.btnCancel)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				windowManager.removeView(view);
			}
		});

	((ImageButton) view.findViewById(R.id.btnSend)).setOnClickListener(
	new OnClickListener()
	{
		@Override
		public final void onClick(View v)
		{
			sendOnClick.onClick(((EditText) view.findViewById(R.id.etReply)).getText().toString());
			windowManager.removeView(view);
		}

	}
			
	);
	
	setOverlay(windowManager, view, context);
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void replyPopup(long id, String hint, String previousMsg, Bitmap image, Context context, Class reply, boolean lightTheme)
	{
		try { 
			Intent intent = new Intent(context, reply);
	  		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  		intent.putExtra("id", id);
	  		intent.putExtra("hint", hint);
	  		intent.putExtra("previous", previousMsg);
	  		intent.putExtra("image", image);
	  		intent.putExtra("lightTheme", lightTheme);
	  		context.startActivity(intent);
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	private static void setOverlay(WindowManager windowManager, final RelativeLayout view, final Context context)
	{
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(
	        WindowManager.LayoutParams.MATCH_PARENT,
	        WindowManager.LayoutParams.WRAP_CONTENT,
	        WindowManager.LayoutParams.TYPE_PHONE, //- TYPE_SYSTEM_ERROR for lockscreen
	        0, //WinndowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
	        PixelFormat.TRANSLUCENT);
		params.x = 0;
		params.y = 0;
		params.gravity = Gravity.BOTTOM | Gravity.LEFT;
		
		try {
			  windowManager.addView(view, params);
			  view.post(new Runnable(){
				@Override
				public void run() {
					InputMethodManager imm = (InputMethodManager) context.getSystemService(Service.INPUT_METHOD_SERVICE);
	    			imm.showSoftInput(view.findViewById(R.id.etReply), InputMethodManager.SHOW_IMPLICIT);
				}});
			  
		  } catch (Exception e) {}
	}

	public static class onClickListener 
	{
		
		public void onClick(String str)
		{
			
		}
		
		public void onClick()
		{
			
		}
	}
	
	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	public final static void setWindowStateChanged(Context context, String packageName)
	{
		SharedPreferences prefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_WORLD_READABLE | Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(WINDOWSTATECHANGED, packageName);
        editor.commit();
        Log.d("WindowStateChanged", packageName);
	}
	
	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	public final static String getWindowStateChanged(Context context)
	{
		SharedPreferences settings = context.getSharedPreferences(context.getPackageName(), Context.MODE_WORLD_READABLE | Context.MODE_MULTI_PROCESS);
    	String ret = "" + settings.getString(WINDOWSTATECHANGED, "");
    	return ret;
	}
}
