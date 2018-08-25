package example.com.inclass6;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Nitin on 6/8/2017.
 */

public class GetNewsAsyncTask extends AsyncTask<String,Void,ArrayList<Article>>{

    IAsyncPassArticle iAsyncPassArticle;
    ProgressDialog progressDialog;
    public GetNewsAsyncTask(IAsyncPassArticle iArticle){
        this.iAsyncPassArticle = iArticle;
    }

    public interface IAsyncPassArticle{
        void getArrayList(ArrayList<Article> articleArrayList);
        Context getContext();
    }
    @Override
    protected ArrayList<Article> doInBackground(String... params) {
        StringBuilder sb = new StringBuilder();
        InputStream in = null;
        ArrayList<Article> myList = new ArrayList<Article>();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //con.connect();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            in = con.getInputStream();
           /* String line = "";

            while ((line = reader.readLine()) != null){
                sb.append(line);
            }*/
            myList =  XMLUtil.ArticleSAXParser.articleParser(in);
            // myList = ArticleXMLUtil.ArticlePullParse.parseXML(in);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        //return JSONUtil.parseArticleJSON(sb.toString());

        return myList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(iAsyncPassArticle.getContext());
        progressDialog.setMessage("Loading News ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(ArrayList<Article> articleArrayList) {
        super.onPostExecute(articleArrayList);

        iAsyncPassArticle.getArrayList(articleArrayList);
        progressDialog.dismiss();
    }
}
