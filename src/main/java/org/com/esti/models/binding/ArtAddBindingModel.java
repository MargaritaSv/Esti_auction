package org.com.esti.models.binding;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Year;

public class ArtAddBindingModel extends AuctionObjectAddBindingModel {
    private String author;
    private Year painted;
    private Integer width;
    private Integer height;
    private String description;

    public ArtAddBindingModel() {
    }

    //  @Min(value = 2, message = "The name must be at least to 2 characters.")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE, pattern = "yyyy")
//    @JsonFormat(pattern = "yyyy")
    public Year getPainted() {
        return painted;
    }

    public void setPainted(Year painted) {
        this.painted = painted;
    }

    @NotNull
    @Min(value = 5, message = "Width must be at least 5cm.")
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @NotNull
    @Min(value = 5, message = "Height must be at least 5cm.")
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
