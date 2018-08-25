package example.com.inclass6;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NewsDetailsActivity extends AppCompatActivity {

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        TextView title = (TextView)findViewById(R.id.textViewNewsTitle);
        TextView pubdate = (TextView)findViewById(R.id.textViewPubDate);
        TextView desc_value = (TextView)findViewById(R.id.textViewDescValue);
        img = (ImageView)findViewById(R.id.imageViewNewsImage);
        img.setImageResource(0);

        if(getIntent().getExtras() != null){

                Article a = (Article) getIntent().getExtras().getSerializable(MainActivity.ARTICLE_KEY);
                title.setText(a.getTitle());
                pubdate.setText(a.getPubDate());
                desc_value.setText(a.getDescription());


            new DownloadImage(new DownloadImage.AsyncImage() {
                @Override
                public void getImage(Bitmap image) {
                    img.setImageBitmap(image);
                }
            }).execute(a.getImageUrl());
        }


    }
}
