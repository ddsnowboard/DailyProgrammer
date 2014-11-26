import java.io.IOException;
import java.util.ArrayList;
import static org.jsoup.Jsoup.connect;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ddsnowboard
 */
public class DP190Easy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String url = "https://plus.googleapis.com/u/0/_/widget/render/comments?first_party_property=YOUTUBE&href=https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        String[] happyWords = {"love", "loved", "like", "liked", "awesome", "amazing", "good", "great", "excellent"};
        String[] sadWords = {"hate", "hated", "dislike", "disliked", "awful", "terrible", "bad", "painful", "worst"};
        int happyNum = 0;
        int sadNum = 0;
        Document document = connect(url).get();
        Elements comments = document.getElementsByClass("Ct");
        for (Element c : comments) {
            for (String s : happyWords) {
                happyNum += count(c.text(), s);
            }
            for (String s : sadWords)
            {
                sadNum += count(c.text(), s);
            }
        }
        System.out.printf("There were %d happy words and %d sad words", happyNum, sadNum);
    }

    public static int count(String s, String search) {
        int count = 0;
        int len = search.length();
        for (int i = 0; i < s.length() - len; i++) {
            if (s.substring(i, i + len).equals(search)) {
                count++;
            }
        }
        return count;
    }

}
