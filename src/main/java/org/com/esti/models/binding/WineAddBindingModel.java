package org.com.esti.models.binding;

import org.com.esti.domain.entities.enums.WineCollections;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class WineAddBindingModel extends AuctionObjectAddBindingModel {

    private WineCollections collection;
    private Integer numberOfBottles;

    public WineAddBindingModel() {
    }

    public WineCollections getCollection() {
        return collection;
    }

    public void setCollection(WineCollections collection) {
        this.collection = collection;
    }

    @Min(value = 1, message = "Bottle must be at least one.")
    @Max(value = 600, message = "")
    public Integer getNumberOfBottles() {
        return numberOfBottles;
    }

    public void setNumberOfBottles(Integer numberOfBottles) {
        this.numberOfBottles = numberOfBottles;
    }
}
