package example.com.inclass6;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String ARTICLE_KEY ="ARTICLE_KEY";
    ArrayList<Article> articleList;
    LinearLayout container;
    View itemView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollView sv_main = (ScrollView)findViewById(R.id.svmain);
        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        articleList = new ArrayList<Article>();

        new GetNewsAsyncTask(new GetNewsAsyncTask.IAsyncPassArticle() {
            @Override
            public void getArrayList(ArrayList<Article> articleArrayList) {
                articleList = articleArrayList;

                for (Article a: articleList){
                    final listItemUI listItem = new listItemUI(MainActivity.this);
                    itemView = (View)listItem;
                    listItem.tilte.setText(a.getTitle());


                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this,NewsDetailsActivity.class);
                            intent.putExtra(ARTICLE_KEY,articleList.get(((ViewGroup)container).indexOfChild(v)));
                            startActivity(intent);
                        }
                    });

                    new DownloadImage(new DownloadImage.AsyncImage() {
                        @Override
                        public void getImage(Bitmap image) {
                            listItem.storyImage.setImageBitmap(image);
                        }
                    }).execute(a.getImageUrl());

                    container.addView(itemView);

                }
            }

            @Override
            public Context getContext() {
                return MainActivity.this;
            }
        }).execute("http://rss.nytimes.com/services/xml/rss/nyt/World.xml");

        sv_main.addView(container);



    }
}
