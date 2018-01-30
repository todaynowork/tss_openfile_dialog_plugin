package com.ibm.esolution.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.ibm.esolution.mobile.client.MTSSActivity;

public class PushPlugin extends CordovaPlugin {
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException 
	{
		System.out.println("#### Enter PushPlugin execute() function!");
		if ("fetchPushIds".equals(action))
		{
			MTSSActivity mainActity = (MTSSActivity)cordova.getActivity();
			System.out.println("#### MTSS_MAPP_4SSR.pushUserId = " + mainActity.pushUserId);
			System.out.println("#### MTSS_MAPP_4SSR.pushChannelId = " + mainActity.pushChannelId);
			if ( mainActity.pushUserId == null || mainActity.pushChannelId == null )
			{
				PushManager.startWork(cordova.getActivity(), PushConstants.LOGIN_TYPE_API_KEY, mainActity.getPushAPIKey());
				callbackContext.error("Can't fetch Push IDs.");
				return false;
			}
			else
			{
				callbackContext.success(mainActity.pushUserId + "|" + mainActity.pushChannelId);
				return true;
			}
		} else {
			return false;
		}
	}
}