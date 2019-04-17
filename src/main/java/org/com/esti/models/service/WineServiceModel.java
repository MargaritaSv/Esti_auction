package org.com.esti.models.service;

import org.com.esti.domain.entities.enums.WineCollections;

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

    public Integer getNumberOfBottles() {
        return numberOfBottles;
    }

    public void setNumberOfBottles(Integer numberOfBottles) {
        this.numberOfBottles = numberOfBottles;
    }
}
