package org.com.esti.models.service;

import org.com.esti.domain.entities.UserPersonal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ArtServiceModel extends AuctionObjectServiceModel {
    private String author;
    private LocalDate paintedTo;
    private LocalDate paintedFrom;
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

    public LocalDate getPaintedTo() {
        return paintedTo;
    }

    public void setPaintedTo(LocalDate paintedTo) {
        this.paintedTo = paintedTo;
    }

    public LocalDate getPaintedFrom() {
        return paintedFrom;
    }

    public void setPaintedFrom(LocalDate paintedFrom) {
        this.paintedFrom = paintedFrom;
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
