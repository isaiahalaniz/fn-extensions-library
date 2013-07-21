package robj.floating.notifications;

import robj.floating.notificationsextensionlibrary.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class Reply extends Activity {
	
	private long id;
	
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		id = getIntent().getLongExtra("id", -1);
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		final RelativeLayout view = (RelativeLayout) inflater.inflate(
				getIntent().getBooleanExtra("lightTheme", false)  ? R.layout.activity_reply : R.layout.activity_reply_dark, null);

		final EditText et = ((EditText) view.findViewById(R.id.etReply));
		et.setHint(getIntent().getStringExtra("hint"));

		TextView message = ((TextView) view.findViewById(R.id.tvMessage));
		message.setMovementMethod(new ScrollingMovementMethod());
		message.setText(getIntent().getStringExtra("previous"));

		((ImageView) view.findViewById(R.id.imgContact)).setImageBitmap((Bitmap) getIntent().getParcelableExtra("image")); //image link to contact card
		
		setContentView(view);
	}
	
	public long getId()
	{
		return id;
	}
	
	public void onReply(View v)
	{
		onReply(((EditText) findViewById(R.id.etReply)).getText().toString());
	}
	
	public abstract void onReply(String msg);
	
	public abstract void onImageClick(View v);
	
}
