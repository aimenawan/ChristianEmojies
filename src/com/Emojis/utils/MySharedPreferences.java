package com.Emojis.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class MySharedPreferences
	{
		static SharedPreferences sp;
		static Editor editor;
		static final String KEY_AGREE = "isAgree";
		static final String KEY_LOGIN = "isLogin";

		public MySharedPreferences( Context context )
			{
				sp = PreferenceManager.getDefaultSharedPreferences(context);
				editor = sp.edit();
				editor.commit();
			}

		public static boolean getIsLogin()
			{
				return sp.getBoolean(KEY_LOGIN, false);
			}
		public static void setIsLogin(boolean isLogin)
			{
				editor.putBoolean(KEY_LOGIN, isLogin);
				commitChanges();
			}

		public static boolean getIsAgree()
			{
				return sp.getBoolean(KEY_AGREE, false);
			}
		public static void setIsAgree(boolean isAgree)
			{
				editor.putBoolean(KEY_AGREE, isAgree);
				commitChanges();
			}

		
		public int getHitCount()
			{
				return sp.getInt("hitCount", 0);
			}

		public void setHitCount()
			{
				editor.putInt("hitCount", getHitCount() + 1);
				commitChanges();
			}

		static void commitChanges()
			{
				editor.commit();
			}
	}