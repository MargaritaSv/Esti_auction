package org.com.esti.models.binding;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ArtAddBindingModel extends AuctionObjectAddBindingModel {
    private String author;
    private LocalDate paintedTo;
    private LocalDate paintedFrom;
    private Integer width;
    private Integer height;
    private String description;

    public ArtAddBindingModel() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getPaintedTo() {
        return paintedTo;
    }

    public void setPaintedTo(LocalDate paintedTo) {
        this.paintedTo = paintedTo;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getPaintedFrom() {
        return paintedFrom;
    }

    public void setPaintedFrom(LocalDate paintedFrom) {
        this.paintedFrom = paintedFrom;
    }

    @NotNull
    @Min(value = 5, message = "Width must be at least 5sm.")
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @NotNull
    @Min(value = 5, message = "Height must be at least 5sm.")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @NotNull
    @NotEmpty(message = "Description is required.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
