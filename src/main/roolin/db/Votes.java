package db;

import com.opencsv.bean.CsvBindByName;

public class Votes {
    private int id;
    private Integer idMovie;

    @CsvBindByName
    private int rate;
    @CsvBindByName
    private int votes;

    @Override
    public String toString() {
        return "Votes{" +
                "id=" + id +
                ", idMovie=" + idMovie +
                ", rate=" + rate +
                ", votes=" + votes +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
