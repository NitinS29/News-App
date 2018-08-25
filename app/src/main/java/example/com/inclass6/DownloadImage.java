package example.com.inclass6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nitin on 6/6/2017.
 */

public class DownloadImage extends AsyncTask<String,Void,Bitmap> {

    //ProgressDialog progressDialog;
    public AsyncImage delegate = null;

    public DownloadImage(AsyncImage delegate) {
        this.delegate = delegate;
    }

    public interface AsyncImage{
        void getImage(Bitmap image);
        //Context getContext();
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            final Bitmap bitmap = BitmapFactory.decodeStream(stream);
            return bitmap;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        //progressDialog.dismiss();
        delegate.getImage(bitmap);
    }

}
