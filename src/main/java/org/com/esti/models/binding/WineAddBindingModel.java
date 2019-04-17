package org.com.esti.models.binding;

import org.com.esti.domain.entities.enums.WineCollections;

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

    public Integer getNumberOfBottles() {
        return numberOfBottles;
    }

    public void setNumberOfBottles(Integer numberOfBottles) {
        this.numberOfBottles = numberOfBottles;
    }
}
