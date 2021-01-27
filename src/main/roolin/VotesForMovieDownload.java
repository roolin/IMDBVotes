import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;

public class VotesForMovieDownload {
    private String imdb_id = "";
    private String name = "";
    private int ratings[] = new int[10];
    private Element body;

    public VotesForMovieDownload(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public void parse() throws IOException {
        connect();
        table();
    }

    private void connect() throws IOException {
        Document doc = Jsoup.connect("https://www.imdb.com/title/" + imdb_id + "/ratings").get();
        body = doc.body();

        Element title = body.getElementsByAttributeValue("itemprop", "url").get(0);
        name = title.text();
    }

    private void table() {
        Element ratingsClass = body.getElementsByClass("title-ratings-sub-page").get(0);
        Element table = ratingsClass.getElementsByTag("table").get(0);
        Elements rows = table.getElementsByTag("tr");
        for(Element row : rows){
            Elements cells = row.getElementsByTag("td");
            if(!cells.isEmpty()) {
                Element index = cells.get(0).getElementsByClass("rightAligned").get(0);
                var lp = Integer.parseInt(index.text());
                var votes = Integer.parseInt(cells.get(2).getElementsByClass("leftAligned").get(0).text().replace(",", ""));
                ratings[lp-1] = votes;
            }
        }
    }

    @Override
    public String toString() {
        return "MoviePage{" +
                "name='" + name + '\'' +
                ", ratings=" + Arrays.toString(ratings) +
                '}';
    }

    public int[] getRatings() {
        return ratings;
    }
}
