import java.io.IOException;
import org.jsoup.nodes.Document;
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
        Document document = Jsoup.connect(url).get();
        System.out.println(document.title());
        Elements comments = 
    }
    
}
