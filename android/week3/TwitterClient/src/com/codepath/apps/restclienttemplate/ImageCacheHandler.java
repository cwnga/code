package com.codepath.apps.restclienttemplate;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageCacheHandler {

	HashMap<String, Bitmap> imgUrlBitmap;
	
	public Bitmap getImage(String url)
	{
		Bitmap bitmap = null;
		if (this.imgUrlBitmap.get(url) != null) {
			bitmap = this.imgUrlBitmap.get(url);
		} else  {
			bitmap = getBitmapURLResources(url);
			this.imgUrlBitmap.put(url, bitmap);
			
		}
		return bitmap;
	}
	
	  public Bitmap getBitmapURLResources(String bitmapURL) {
	    	bitmapURL = bitmapURL.replace("m1","l");//large pic
	    	Bitmap bmp = null;
	    	try{
	    	  	URL myUrl = new URL(bitmapURL);	//
	    		URLConnection myConn = myUrl.openConnection();	
	    		InputStream in = myConn.getInputStream();	
	    		 bmp = BitmapFactory.decodeStream(in);
	    		
	    		//ImageView iv = (ImageView)findViewById(R.id.iv);
	    		//iv.setImageBitmap(bmp);			
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	return bmp;
	    }
	    
}
