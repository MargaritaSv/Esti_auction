package org.com.esti.models.view;

import java.time.LocalDate;
import java.time.Year;

public class ArtViewModel extends AuctionObjectViewModel {

    private String author;
    private Year painted;
    private Integer width;
    private Integer height;
    private String description;

    public ArtViewModel() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getPainted() {
        return painted;
    }

    public void setPainted(Year painted) {
        this.painted = painted;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
