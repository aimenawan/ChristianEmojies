package com.chirstianemojis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.Emojis.utils.MySharedPreferences;
import com.chirstianemojis.R;

public class TermsAndConditionsActivity extends Activity
	{
		CheckBox cbxAgree;
		Button btnContinue;
		TextView tvTerm;

		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_terms_and_conditions);
				new MySharedPreferences(this);
				if (MySharedPreferences.getIsAgree())
					navigateToLogin();

				cbxAgree = (CheckBox) findViewById(R.id.tcCbxAgree);
				btnContinue = (Button) findViewById(R.id.tcbtnContinue);
				tvTerm = (TextView) findViewById(R.id.tcaTvTerms);

				cbxAgree.setOnCheckedChangeListener(new OnCheckedChangeListener()
					{

						@Override
						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
							{
								btnContinue.setEnabled(isChecked);
							}
					});

				btnContinue.setOnClickListener(new OnClickListener()
					{

						@Override
						public void onClick(View v)
							{
								navigateToLogin();
							}
					});
			}

		void navigateToLogin()
			{
				MySharedPreferences.setIsAgree(true);
				startActivity(new Intent(this, LoginActivity.class));
				finish();
			}
	}
