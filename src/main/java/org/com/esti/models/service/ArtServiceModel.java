package org.com.esti.models.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Year;

public class ArtServiceModel extends AuctionObjectServiceModel {
    private String author;
    private Year painted;
    private Integer width;
    private Integer height;
    private String description;

    public ArtServiceModel() {
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

    @NotNull(message = EMPTY)
    @Size(min = 5, max = 100, message = "Cannot be empty, should be between 5 and 100 symbols.")
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @NotNull(message = EMPTY)
    @Size(min = 5, max = 100, message = "Cannot be empty, should be between 5 and 100 symbols.")
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
