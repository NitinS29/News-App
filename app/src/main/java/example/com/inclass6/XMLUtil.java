package example.com.inclass6;

import android.text.Html;
import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Nitin on 6/8/2017.
 */

public class XMLUtil {

    static class ArticleSAXParser extends DefaultHandler {
        ArrayList<Article> arrayList;
        Article article;
        StringBuilder sb;

        public ArrayList<Article> getArrayList() {
            return arrayList;
        }

        static  ArrayList<Article> articleParser(InputStream in) throws IOException, SAXException {
            ArticleSAXParser parser = new ArticleSAXParser();
            Xml.parse(in, Xml.Encoding.UTF_8,parser);
            return parser.getArrayList();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //super.startElement(uri, localName, qName, attributes);
            if(localName.equalsIgnoreCase("item")){
                article = new Article();
                //Log.d("demo","article create");
            }else if(qName.equalsIgnoreCase("media:content") && article!= null){
                //Log.d("demo",attributes.getValue("media:content"));
                article.setImageUrl(attributes.getValue("url"));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            //super.endElement(uri, localName, qName);
            if(localName.equalsIgnoreCase("item")){
                arrayList.add(article);
                article = null;
            }else if(localName.equalsIgnoreCase("title") && article!= null){
                article.setTitle(sb.toString().trim());
                //Log.d("demo",sb.toString().trim());
            }else if(localName.equalsIgnoreCase("description") && article!= null){
                article.setDescription(sb.toString().trim());
            }else if(localName.equalsIgnoreCase("pubDate") && article!= null){
                article.setPubDate(sb.toString().trim());
            }

            sb.setLength(0);
        }
        /*public String stripHtml(String html) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
            } else {
                return Html.fromHtml(html).toString();
            }
        }*/
        @Override
        public void startDocument() throws SAXException {
            //super.startDocument();
            arrayList = new ArrayList<Article>();
            sb = new StringBuilder();
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            //super.characters(ch, start, length);
            sb.append(ch,start,length);
        }
    }
}
