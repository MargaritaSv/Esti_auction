package org.com.esti.models.service;

import org.com.esti.domain.entities.enums.WineCollections;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class WineServiceModel extends AuctionObjectServiceModel {

    private WineCollections collection;
    private Integer numberOfBottles;

    public WineServiceModel() {
    }

    public WineCollections getCollection() {
        return collection;
    }

    public void setCollection(WineCollections collection) {
        this.collection = collection;
    }

    @Min(value = 1, message = "Number of bottles cannot be less than 1")
    @Max(value = 500, message = "Number of bottles cannot be greater than 500")
    public Integer getNumberOfBottles() {
        return numberOfBottles;
    }

    public void setNumberOfBottles(Integer numberOfBottles) {
        this.numberOfBottles = numberOfBottles;
    }
}
