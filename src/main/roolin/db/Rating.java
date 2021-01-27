package db;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;

public class Rating {
    private int id;
    private Integer idMovie;

    @CsvBindByName
    private String tconst;
    @CsvBindByName
    private BigDecimal averageRating;
    @CsvBindByName
    private int numVotes;

    @Override
    public String toString() {
        return "Rating{" +
                "tconst='" + tconst + '\'' +
                ", averageRating=" + averageRating +
                ", numVotes=" + numVotes +
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

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }
}
