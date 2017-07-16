package com.example.android.booklistingappudacity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

import static com.example.android.booklistingappudacity.BookActivity.LOG_TAG;


public  class imageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    ImageView mImage;

    //THIS IS A BACKGROUND METHOD
    public imageAsyncTask(ImageView bmImage) {
        this.mImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap image = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            image = BitmapFactory.decodeStream(in); //DECODING THE INPUTSTREAM TO A BITMAP
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return image;
    }

    //THIS HAPPENS AFTER FINISHED
    protected void onPostExecute(Bitmap result) {
        //SETTING THE OBTAINED IMAGE
        mImage.setImageBitmap(result);
    }
}
