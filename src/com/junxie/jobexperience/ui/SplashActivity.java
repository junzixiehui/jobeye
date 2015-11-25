package com.junxie.jobexperience.ui;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;



import com.junxie.jobexperience.application.MyApplication;
import com.junxie.jobexperience.utils.FileUtils;
import com.junxie.jobexperience.utils.StringUtils;
import com.junxie.jobeye.R;

public class SplashActivity extends Activity {

	private static final String TAG  = "AppStart";
	  MyApplication ac = (MyApplication)  getApplication();
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
      
       final View view = View.inflate(this, R.layout.activity_splash, null);
		LinearLayout wellcome = (LinearLayout) view.findViewById(R.id.splash_ll_drawable);
		check(wellcome);
		setContentView(view);
       
		//����չʾ������
		AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener()
		{
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}
			public void onAnimationRepeat(Animation animation) {}
			public void onAnimationStart(Animation animation) {}
			
		});
		
		//���ݵͰ汾cookie��1.5�汾���£�����1.5.0,1.5.1��
		MyApplication appContext = (MyApplication)getApplication();
		String cookie = appContext.getProperty("cookie");
		if(StringUtils.isEmpty(cookie)) {
			String cookie_name = appContext.getProperty("cookie_name");
			String cookie_value = appContext.getProperty("cookie_value");
			if(!StringUtils.isEmpty(cookie_name) && !StringUtils.isEmpty(cookie_value)) {
				cookie = cookie_name + "=" + cookie_value;
				appContext.setProperty("cookie", cookie);
				appContext.removeProperty("cookie_domain","cookie_name","cookie_value","cookie_version","cookie_path");
			}
		}
   }
   
   /**
    * ����Ƿ���Ҫ��ͼƬ
    * @param view
    */
   private void check(LinearLayout view) {
   	String path = FileUtils.getAppCache(this, "welcomeback");
   	List<File> files = FileUtils.listPathFiles(path);
   	if (!files.isEmpty()) {
   		File f = files.get(0);
   		long time[] = getTime(f.getName());
   		long today = StringUtils.getToday();
   		if (today >= time[0] && today <= time[1]) {
   			view.setBackgroundDrawable(Drawable.createFromPath(f.getAbsolutePath()));
   		}
   	}
   }
   
   /**
    * ������ʾ��ʱ��
    * @param time
    * @return
    */
   private long[] getTime(String time) {
   	long res[] = new long[2];
   	try {
   		time = time.substring(0, time.indexOf("."));
       	String t[] = time.split("-");
       	res[0] = Long.parseLong(t[0]);
       	if (t.length >= 2) {
       		res[1] = Long.parseLong(t[1]);
       	} else {
       		res[1] = Long.parseLong(t[0]);
       	}
		} catch (Exception e) {
		}
   	return res;
   }
   
   /**
    * ��ת��...
    */
   private void redirectTo(){        
       Intent intent = new Intent(this, MainActivity.class);
       startActivity(intent);
       finish();
   }
}
