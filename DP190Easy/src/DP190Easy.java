import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String url = "https://plus.googleapis.com/u/0/_/widget/render/comments?first_party_property=YOUTUBE&href=https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        ArrayList<String> happyWords = new ArrayList<>(3000);
        ArrayList<String> sadWords = new ArrayList<>(3000);
        File happy = new File("positive-words.txt");
        File sad = new File("negative-words.txt");
        Scanner fileScanner = new Scanner(happy);
        while (fileScanner.hasNextLine()) {
            happyWords.add(fileScanner.nextLine());
        }
        fileScanner.close();
        fileScanner = new Scanner(sad);
        while (fileScanner.hasNextLine()) {
            sadWords.add(fileScanner.nextLine());
        }
        fileScanner.close();
        int happyNum = 0;
        int sadNum = 0;
        int commentCount = 0;
        Document document = connect(url).get();
        Elements comments = document.getElementsByClass("Ct");
        for (Element c : comments) {
            commentCount++;
            for (String s : happyWords) {
                happyNum += count(c.text(), s);
            }
            for (String s : sadWords) {
                sadNum += count(c.text(), s);
            }
        }
        System.out.printf("From a sample size of %d comments, here were %d happy words and %d sad words%n", commentCount, happyNum, sadNum);
        if(happyNum>sadNum)
            System.out.println("The general tone of the comments was happy.");
        else if(happyNum<sadNum)
            System.out.println("The general tone of the comments was happy.");
        else
            System.out.println("The general tone was even.");
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
