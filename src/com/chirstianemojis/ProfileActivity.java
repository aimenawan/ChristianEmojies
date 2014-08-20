package com.chirstianemojis;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.chirstianemojis.R;

public class ProfileActivity extends Activity implements android.view.View.OnClickListener
	{
		final int CAMERA = 99, GALLERY = 88;
		String imgUri = "";
		ImageView imgPhoto;

		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_profile);
				imgPhoto = (ImageView) findViewById(R.id.paIvDp);
				imgPhoto.setOnClickListener(this);
			}

		@Override
		public void onClick(View v)
			{
				switch (v.getId())
					{
					case R.id.paIvDp:
						OpenDialogForImages(this);
						break;

					default:
						break;
					}
			}

		void OpenDialogForImages(final Context con)
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(con);
				builder.setTitle("Select option");
				builder.setIcon(android.R.drawable.ic_menu_gallery);
				builder.setMessage("Select Image").setCancelable(true).setPositiveButton("Camera", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int id)
							{
								dialog.cancel();
								// Open Camera
								openCamera();
							}
					}).setNegativeButton("Gallery", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int id)
							{
								dialog.cancel();
								// Open Gallery
								openGallery();
							}
					});
				AlertDialog diag = builder.create();
				diag.show();
			}

		void openCamera()
			{
				String imagePath = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".jpeg";
				File file = new File(imagePath);
				imgUri = file.getAbsolutePath();
				Uri selectedImage = Uri.fromFile(file);

				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedImage);
				startActivityForResult(cameraIntent, CAMERA);
				// startActivityForResult(getCameraImage, CAMERA);
			}

		private void openGallery()
			{
				Intent takePicture = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(takePicture, GALLERY);
			}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data)
			{
				super.onActivityResult(requestCode, resultCode, data);

				switch (requestCode)
					{
					case CAMERA:
						if (resultCode == RESULT_OK)// && data != null)
							{
									// if (data.getData() != null)
									{
										// Uri selectedImage = data.getData();
										// imgUri = selectedImage.getPath();
										Log.e("imgUri", imgUri);
										try
											{
												imgPhoto.setImageURI(Uri.parse(imgUri));
											}
										catch (OutOfMemoryError e)
											{
												Toast.makeText(ProfileActivity.this, "Photo did not load, Memory is Low", Toast.LENGTH_LONG).show();
											}
									}
							}
						break;
					case GALLERY:
						if (resultCode == RESULT_OK && data != null)
							{
								if (data.getData() != null)
									{
										Uri selectedImage = data.getData();
										String[] filePathColumn = {MediaStore.Images.Media.DATA};

										Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
										cursor.moveToFirst();

										int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
										String picturePath = cursor.getString(columnIndex);
										cursor.close();
										try
											{
												imgPhoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
											}
										catch (OutOfMemoryError e)
											{
												Toast.makeText(ProfileActivity.this, "Photo did not load, Memory is Low", Toast.LENGTH_LONG).show();
											}
										imgUri = picturePath;
										Log.e("imgUri", imgUri);
									}
							}
						break;
					}
			}

	}
