package db;

import com.opencsv.bean.CsvBindByName;

import java.util.Arrays;

public class Title {
    private int id;
    @CsvBindByName
    private String titleId;
    @CsvBindByName
    private int ordering;
    @CsvBindByName
    private String title;
    @CsvBindByName
    private String region;
    @CsvBindByName
    private String language;
    @CsvBindByName
    private String[] types;
    @CsvBindByName
    private String[] attributes;
    @CsvBindByName
    private Boolean isOriginalTitle;

    @Override
    public String toString() {
        return "Title{" +
                "titleId='" + titleId + '\'' +
                ", ordering=" + ordering +
                ", title='" + title + '\'' +
                ", region='" + region + '\'' +
                ", language='" + language + '\'' +
                ", types=" + Arrays.toString(types) +
                ", attributes=" + Arrays.toString(attributes) +
                ", isOriginalTitle=" + isOriginalTitle +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }

    public Boolean getOriginalTitle() {
        return isOriginalTitle;
    }

    public void setOriginalTitle(Boolean originalTitle) {
        isOriginalTitle = originalTitle;
    }
}
