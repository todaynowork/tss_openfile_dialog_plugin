package com.ibm.esolution.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.ProgressDialog;

public class ProgressDialogPlugin extends CordovaPlugin {
	
	ProgressDialog mProgressDialog = null;
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException 
	{
		if (action.equals("show")) 
		{
			String title = args.getString(0);
			String text = args.getString(1);
			if ( mProgressDialog == null )
			{
				mProgressDialog = ProgressDialog.show(cordova.getActivity(), title, text, true, true);
			}
			else
			{
				mProgressDialog.setTitle(title);
				mProgressDialog.setMessage(text);
				mProgressDialog.show();
			}
			return true;
		} 
		else if (action.equals("hide")) 
		{
			if ( mProgressDialog != null )
			{
				mProgressDialog.dismiss();
			}
			return true;
		}
		return false;
	}
}

