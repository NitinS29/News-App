package example.com.inclass6;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nitin on 6/8/2017.
 */

public class Article implements Serializable{
    String title,pubDate,imageUrl,description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;

        try {

            final String outputFormat = "MM/dd/yyyy HH:mm a";
            final String inputFormat = "EEE, dd MMM yyyy HH:mm:ss zzz";

             this.pubDate = new SimpleDateFormat(outputFormat).format(new SimpleDateFormat(inputFormat).parse(this.pubDate));//TimeStampConverter(inputFormat, this.pubDate, outputFormat) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*private static String TimeStampConverter(final String inputFormat,
                                             String inputTimeStamp, final String outputFormat)
            throws ParseException {
        return new SimpleDateFormat(outputFormat).format(new SimpleDateFormat(inputFormat).parse(inputTimeStamp));
    }*/

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
