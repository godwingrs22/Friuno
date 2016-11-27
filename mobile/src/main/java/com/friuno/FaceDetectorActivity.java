package com.friuno;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.friuno.image.ImageController;
import com.friuno.image.ImageSearch;
import com.friuno.util.InternetResolver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by GodwinRoseSamuel on 16-10-2016.
 */
public class FaceDetectorActivity extends AppCompatActivity {

    private static final String TAG = "FaceDetectorActivity";
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final String PROJECT_DIRECTORY_NAME = "FRIUNO";
    public static final String IMAGE_DIRECTORY_NAME = "images";
    public static final String CACHE_DIRECTORY_NAME = "cache";
    private static final int CHOOSE_IMAGE_REQUEST_CODE = 101;
    private Uri fileUri;
    private ImageView userImage;
    private Button browseImage, submit_image;
    String imageJSON = null;
    ImageController imageController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detector);

        browseImage = (Button) findViewById(R.id.browseImage);
        submit_image = (Button) findViewById(R.id.submit_image);
        userImage = (ImageView) findViewById(R.id.userImage);

        imageController = new ImageController();

        browseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        submit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchImage();
            }
        });
    }

    public void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CHOOSE_IMAGE_REQUEST_CODE);
    }

    public void searchImage() {

        if (InternetResolver.isInternetAvailable(getApplicationContext())) {
            if (getOutputMediaFile(MEDIA_TYPE_IMAGE).exists()) {
                new FaceDetectorThread().execute();
            } else {
                new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Oops!!")
                        .setMessage("No Image File Found....")
                        .show();
            }
        } else {
            new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Oops!!")
                    .setMessage("Internet Connection is not available..")
                    .show();
        }
    }

    private class FaceDetectorThread extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FaceDetectorActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
            progressDialog.setTitle("Please Wait..");
            progressDialog.setMessage("Uploading your image....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
//            imageJSON = ImageSearch.getImage(getOutputMediaFileUri(MEDIA_TYPE_IMAGE));
//            runOnUiThread(changeMessage);
//            Log.d(TAG, "<--json response--->" + imageJSON);
//            if (!imageJSON.contains("error")) {
//                imageController.verifyImage(imageJSON);
//            } else {
//                new AlertDialog.Builder(getApplicationContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK)
//                        .setIcon(android.R.drawable.ic_dialog_info)
//                        .setTitle("Oops!!")
//                        .setMessage("Unable to recognise Image..")
//                        .show();
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            finish();
        }

        private Runnable changeMessage = new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage("Analysing your Face...");
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == CHOOSE_IMAGE_REQUEST_CODE) {
                Uri selectedImage = data.getData();
                previewChoosenImage(selectedImage);
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "User cancelled image capture", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
        }
    }

    private void previewChoosenImage(Uri fileUri) {
        try {
            userImage.setVisibility(View.VISIBLE);
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(fileUri, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap bitmap = (BitmapFactory.decodeFile(picturePath));
            userImage.setVisibility(View.VISIBLE);
            userImage.setImageBitmap(bitmap);
            OutputStream out;
            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), PROJECT_DIRECTORY_NAME + File.separator + IMAGE_DIRECTORY_NAME);
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                }
            }
            File file = new File(Environment.getExternalStorageDirectory(), PROJECT_DIRECTORY_NAME + File.separator + IMAGE_DIRECTORY_NAME + File.separator + "image.jpg");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out = new FileOutputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            out.write(baos.toByteArray());
            out.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), PROJECT_DIRECTORY_NAME + File.separator + IMAGE_DIRECTORY_NAME);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "image.jpg");
        } else {
            return null;
        }
        return mediaFile;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fileUri = savedInstanceState.getParcelable("file_uri");
    }
}
