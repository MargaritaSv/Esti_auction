package org.com.esti.domain.entities;

import org.com.esti.resources.YearAttributeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.Year;

@Entity
@Table(name = "art")
public class Art extends AuctionObject {

    private String author;
    private Year painted;
    private Integer width;
    private Integer height;
    private String description;

    public Art() {
    }

    @Size(min = 2, message = "The name must be at least to 2 characters.")
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "painted")
    @Convert(converter = YearAttributeConverter.class)
    public Year getPainted() {
        return painted;
    }

    public void setPainted(Year painted) {
        this.painted = painted;
    }

    @Column(name = "width", nullable = false)
    @Min(1)
    @Max(600)
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Column(name = "height", nullable = false)
    @Min(1)
    @Max(600)
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    @Size(min = 10, message = "Description must be at least to 10 characters.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
