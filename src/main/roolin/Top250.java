import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;

public class Top250 {
    String url = "https://www.imdb.com/chart/top/?sort=rk,asc&mode=simple&page=1";
    String links[] = new String[250];

    void parse() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element body = doc.body();
        Element table = body.getElementsByClass("lister-list").get(0);
        Elements rows = body.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        int i = 0;
        for(Element row : rows) {
            String link = row.getElementsByTag("td").get(0).getElementsByTag("a").get(0).attr("href");
            String[] split = link.split("/");
            String movieId = split[2];
            links[i++] = movieId;
        }
    }

    @Override
    public String toString() {
        return "Top250{" +
                "links=" + Arrays.toString(links) +
                '}';
    }
}
