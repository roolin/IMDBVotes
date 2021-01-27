import db.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

class IMDBVotes {


    public static void main(String[] args) {
        VotesDownloader votesDownloader = new VotesDownloader();
        try {
            votesDownloader.downloadVotesConcurrent(1);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void filesIntoDB() {
        DBConn conn = new DBConn();

        List<Thread> threads = new ArrayList<>();
        var movieToDB = new IMDBToDB<Movie>(Movie.class);
        threads.add(new Thread(() -> movieToDB.process(conn.getDataSource())));
        var ratingToDB = new IMDBToDB<Rating>(Rating.class);
        threads.add(new Thread(() -> ratingToDB.process(conn.getDataSource())));

        for(Thread t : threads) {
            t.start();
        }


        boolean going = true;

        while(going) {
            going = false;
            for (Thread t : threads) {
                if (t.isAlive()) {
                    going = true;
                    break;
                }
            }
        }
    }
}