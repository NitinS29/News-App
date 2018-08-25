package example.com.inclass6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nitin on 6/8/2017.
 */

public class listItemUI extends LinearLayout {

    public TextView tilte;
    public ImageView storyImage;

    public listItemUI(Context context) {
        super(context);
        inflateXML(context);
    }

    private void inflateXML(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.listitem,this);
        this.tilte = (TextView)findViewById(R.id.textViewId);
        this.storyImage = (ImageView)findViewById(R.id.imageViewThumbnail);
    }
}
