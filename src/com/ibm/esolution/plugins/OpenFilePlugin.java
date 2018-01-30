package com.ibm.esolution.plugins;

import java.io.File;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class OpenFilePlugin extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

		try {
			Context context = cordova.getActivity().getApplicationContext();
			
			// 文件路径
			String path = args.getString(0).toLowerCase();
			if ( path.startsWith("file://") )
			{
				path = path.substring(7);
			}
			int len = path.length();
			String lastThree = path.substring(len - 3, len);
			String lastFour = path.substring(len - 4, len);

			// 判断文件类型
			// doc
			if (lastThree.equals("doc") || lastFour.equals("docx")) 
			{
				Intent i = this.getWordFileIntent(path);
				context.startActivity(i);
			}

			// excel
			else if (lastThree.equals("xls") || lastFour.equals("xlsx")) 
			{
				Intent i = this.getExcelFileIntent(path);
				context.startActivity(i);
			}

			// ppt
			else if (lastThree.equals("ppt") || lastFour.equals("pptx")) 
			{
				Intent i = this.getPptFileIntent(path);
				context.startActivity(i);
			}

			// pdf
			else if (lastThree.equals("pdf")) 
			{
				Intent i = this.getPdfFileIntent(path);
				context.startActivity(i);
			}

			// 图片
			else if (lastThree.equals("jpg") || lastThree.equals("png")
					|| lastThree.equals("gif") || lastThree.equals("bmp")
					|| lastFour.equals("jpeg")) 
			{
				Intent i = this.getImageFileIntent(path);
				context.startActivity(i);
			}

			// 文本
			else if (lastThree.equals("txt")) 
			{
				Intent i = this.getTextFileIntent(path, false);
				context.startActivity(i);
			}

			// html
			else if (lastThree.equals("htm") || lastFour.equals("html")) 
			{
				Intent i = this.getHtmlFileIntent(path);
				context.startActivity(i);
			}

			// chm
			else if (lastThree.equals("chm")) 
			{
				Intent i = this.getChmFileIntent(path);
				context.startActivity(i);
			}

			// 音频
			else if (lastThree.equals("mp3") || lastThree.equals("wav")
					|| lastThree.equals("wma") || lastThree.equals("ogg")
					|| lastThree.equals("ape") || lastThree.equals("acc")) 
			{
				Intent i = this.getAudioFileIntent(path);
				context.startActivity(i);
			}

			// 视频
			else if (lastThree.equals("avi") || lastThree.equals("mov")
					|| lastThree.equals("asf") || lastThree.equals("wmv")
					|| lastThree.equals("navi") || lastThree.equals("3gp")
					|| lastThree.equals("ram") || lastThree.equals("mkv")
					|| lastThree.equals("flv") || lastThree.equals("mp4")
					|| lastFour.equals("rmvb") || lastThree.equals("mpg")) 
			{
				Intent i = this.getVideoFileIntent(path);
				context.startActivity(i);
			}
			
			// 视频
			else if (lastThree.equals("apk")) 
			{
				Intent i = this.getApkFileIntent(path);
				context.startActivity(i);
			}

			else 
			{
				callbackContext.success("无法打开该文件！");
			}

			Intent i = getExcelFileIntent(path);
			context.startActivity(i);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	// android获取一个用于打开Excel文件的intent
	public static Intent getExcelFileIntent(String param) 
	{
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-excel");
		return intent;
	}

	// android获取一个用于打开HTML文件的intent
	public static Intent getHtmlFileIntent(String param) 
	{
		Uri uri = Uri.parse(param).buildUpon()
				.encodedAuthority("com.android.htmlfileprovider")
				.scheme("content").encodedPath(param).build();
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.setDataAndType(uri, "text/html");
		return intent;
	}

	// android获取一个用于打开图片文件的intent
	public static Intent getImageFileIntent(String param) 
	{
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "image/*");
		return intent;
	}

	// android获取一个用于打开PDF文件的intent
	public static Intent getPdfFileIntent(String param) 
	{
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/pdf");
		return intent;
	}

	// android获取一个用于打开文本文件的intent
	public static Intent getTextFileIntent(String param, boolean paramBoolean) {

		Intent intent = new Intent("android.intent.action.VIEW");

		intent.addCategory("android.intent.category.DEFAULT");

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		if (paramBoolean) {

			Uri uri1 = Uri.parse(param);

			intent.setDataAndType(uri1, "text/plain");

		} else {

			Uri uri2 = Uri.fromFile(new File(param));

			intent.setDataAndType(uri2, "text/plain");

		}

		return intent;

	}

	// android获取一个用于打开音频文件的intent
	public static Intent getAudioFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");

		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		intent.putExtra("oneshot", 0);

		intent.putExtra("configchange", 0);

		Uri uri = Uri.fromFile(new File(param));

		intent.setDataAndType(uri, "audio/*");

		return intent;

	}

	// android获取一个用于打开视频文件的intent
	public static Intent getVideoFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");

		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		intent.putExtra("oneshot", 0);

		intent.putExtra("configchange", 0);

		Uri uri = Uri.fromFile(new File(param));

		intent.setDataAndType(uri, "video/*");

		return intent;

	}

	// android获取一个用于打开CHM文件的intent
	public static Intent getChmFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");

		intent.addCategory("android.intent.category.DEFAULT");

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		Uri uri = Uri.fromFile(new File(param));

		intent.setDataAndType(uri, "application/x-chm");

		return intent;

	}

	// android获取一个用于打开Word文件的intent
	public static Intent getWordFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");

		intent.addCategory("android.intent.category.DEFAULT");

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		Uri uri = Uri.fromFile(new File(param));

		intent.setDataAndType(uri, "application/msword");

		return intent;

	}

	// android获取一个用于打开PPT文件的intent
	public static Intent getPptFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");

		intent.addCategory("android.intent.category.DEFAULT");

		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		Uri uri = Uri.fromFile(new File(param));

		intent.setDataAndType(uri, "application/vnd.ms-powerpoint");

		return intent;

	}
	
	// android获取一个用于打开PPT文件的intent
	public static Intent getApkFileIntent(String param) {

			Intent intent = new Intent("android.intent.action.VIEW");

			intent.addCategory("android.intent.category.DEFAULT");

			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			Uri uri = Uri.fromFile(new File(param));

			intent.setDataAndType(uri, "application/vnd.android.package-archive");

			return intent;

		}

}
