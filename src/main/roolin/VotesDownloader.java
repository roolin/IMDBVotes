import db.DBOperations;
import db.Movie;
import db.Votes;
import db.VotesDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class VotesDownloader {
    DBConn conn = new DBConn();
    DBOperations operations = new DBOperations();
    VotesDB votesDB = new VotesDB(conn.getDataSource());

    public void downloadVotesOneByOne() throws IOException {
        Movie[] movies = getMoviesForVotesDownload();
        for(Movie m : movies) {
            votes4Movie(m);
        }
    }

    public void downloadVotesConcurrent(int threadsNumber) throws InterruptedException, ExecutionException {
        Movie[] movies = getMoviesForVotesDownload();
        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);
        List<Callable<Long>> tasks = new ArrayList<>();
        AtomicInteger jobsDone = new AtomicInteger();

        long startAll = System.nanoTime();
        for(Movie m : movies) {
            tasks.add(() -> {
                long start = System.nanoTime();
                votes4Movie(m);
                long stop = System.nanoTime();
                double time = (stop - start)/1000000000.0;
                int count = jobsDone.incrementAndGet();
                String s = m.getTconst() + ":" + count + ":" + time;
                System.out.println(s);
                return 0l;
            });
        }
        List<Future<Long>> futures = executor.invokeAll(tasks);
        long stopAll = System.nanoTime();
        System.out.println("Time: " + (stopAll - startAll)/1000000000);
    }

    private Movie[] getMoviesForVotesDownload() {
        return operations.getMoviesForVotesDownload(conn.getDataSource(), 100000, 100);
    }

    private void votes4Movie(Movie m) throws IOException {
        VotesForMovieDownload downloader = new VotesForMovieDownload(m.getTconst());
        downloader.parse();
        int[] ratings = downloader.getRatings();

        Votes[] movieVotes = votesDB.getVotesForMovie(m);
        if(movieVotes.length == 0) {
            addVotes(m, ratings);
        } else {
            updateVotes(movieVotes, ratings);
        }
    }

    private void updateVotes(Votes[] movieVotes, int[] ratings) {
        for(Votes v : movieVotes) {
            v.setVotes(ratings[v.getRate()-1]);
            votesDB.updateVotes(v);
        }
    }

    private void addVotes(Movie m, int[] ratings) {
        int i = 1;
        for(int v : ratings) {
            Votes votes = new Votes();
            votes.setIdMovie(m.getId());
            votes.setRate(i++);
            votes.setVotes(v);
            votesDB.insert(votes);
        }
    }


}
