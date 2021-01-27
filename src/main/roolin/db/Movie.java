package db;

import com.opencsv.bean.CsvBindByName;

import java.util.Arrays;

public class Movie {
    int id;
    @CsvBindByName
    String tconst;
    @CsvBindByName
    String titleType;
    @CsvBindByName
    String primaryTitle;
    @CsvBindByName
    String originalTitle;
    @CsvBindByName
    boolean isAdult;
    @CsvBindByName
    String startYear;
    @CsvBindByName
    String endYear;
    @CsvBindByName
    String runtimeMinutes;
    @CsvBindByName
    String[] genres;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", tconst='" + tconst + '\'' +
                ", titleType='" + titleType + '\'' +
                ", primaryTitle='" + primaryTitle + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", isAdult=" + isAdult +
                ", startYear='" + startYear + '\'' +
                ", endYear='" + endYear + '\'' +
                ", runtimeMinutes=" + runtimeMinutes +
                ", genres=" + Arrays.toString(genres) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(String runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }
}
