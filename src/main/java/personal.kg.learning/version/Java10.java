package personal.kg.learning.version;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by gyanc on 7/29/2018.
 */
public class Java10 {
    public static void main(String... args) throws IOException {
        releaseNotes("https://www.oracle.com/technetwork/java/javase/10u-relnotes-4108739.html");

    }

    public static void releaseNotes(String urlString) throws IOException {

        URL url = new URL(urlString);
        ArrayList data = new ArrayList();
        Element headerTag = new Element(Tag.valueOf("a"), url.getHost());
        headerTag.text(url.toString());
        headerTag.attr("href", url.toString());
        Elements header = new Elements();
        header.add(headerTag);
        header.add(new Element(Tag.valueOf("br"), ""));
        data.add(header.outerHtml());

        Document doc = Jsoup.connect(url.toString())
                .get();



        doc.getElementsByClass("innerPgSignpost")
                .forEach(action ->
                        action.getElementsByTag("li")
                                .forEach(inner -> {
                                            Element anchorTag = inner.getElementsByTag("a").get(0);
                                            String text = url.getProtocol() + "://" + url.getHost() + anchorTag.attr("href");
                                            anchorTag.attr("href", text);
                                            inner.appendChild(new Element(Tag.valueOf("br"), url.getHost()));
                                            data.add(inner.html());
                                        }
                                )
                );
        File temp = new File(".\\src\\main\\resources\\Java10.html");
        temp.createNewFile();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(temp));
        bos.write(data.stream().collect(Collectors.joining()).toString().getBytes());
        bos.close();
        Runtime.getRuntime().exec("explorer " + temp.getAbsolutePath());
    }

}
