package org.com.esti.models.binding;

import org.com.esti.domain.entities.enums.WineCollections;

public class WineShowBindingModel extends AuctionObjectAddBindingModel{

    private WineCollections collection;

    public WineShowBindingModel() {
    }

    public WineCollections getCollection() {
        return collection;
    }

    public void setCollection(WineCollections collection) {
        this.collection = collection;
    }
}
