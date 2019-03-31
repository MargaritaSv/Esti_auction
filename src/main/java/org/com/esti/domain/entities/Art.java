package org.com.esti.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "art")
public class Art extends AuctionObject {

    private String author;
    private LocalDate paintedTo;
    private LocalDate paintedFrom;
    private Integer width;
    private Integer height;
    private String description;

    public Art() {
    }

    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "painted_to")
    public LocalDate getPaintedTo() {
        return paintedTo;
    }

    public void setPaintedTo(LocalDate paintedTo) {
        this.paintedTo = paintedTo;
    }

    @Column(name = "painted_from")
    public LocalDate getPaintedFrom() {
        return paintedFrom;
    }

    public void setPaintedFrom(LocalDate paintedFrom) {
        this.paintedFrom = paintedFrom;
    }

    @Column(name = "width", nullable = false)
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Column(name = "height", nullable = false)
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
