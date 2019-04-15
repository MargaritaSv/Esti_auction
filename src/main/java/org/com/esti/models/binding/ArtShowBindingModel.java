package org.com.esti.models.binding;

import java.time.LocalDate;

public class ArtShowBindingModel extends AuctionObjectAddBindingModel {
    private String author;
    private LocalDate painted;
    private Integer width;
    private Integer height;
    private String description;


    public ArtShowBindingModel() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPainted() {
        return painted;
    }

    public void setPainted(LocalDate painted) {
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
