package org.com.esti.models.view;

import org.com.esti.domain.entities.enums.WineCollections;

public class WineViewModel extends AuctionObjectViewModel {

    private WineCollections collection;

    public WineViewModel() {
    }

    public WineCollections getCollection() {
        return collection;
    }

    public void setCollection(WineCollections collection) {
        this.collection = collection;
    }
}
