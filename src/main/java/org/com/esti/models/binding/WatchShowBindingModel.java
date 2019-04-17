package org.com.esti.models.binding;

import org.com.esti.domain.entities.enums.WatchCollection;

public class WatchShowBindingModel extends AuctionObjectAddBindingModel {

    private WatchCollection collection;

    public WatchShowBindingModel() {
    }

    public WatchCollection getCollection() {
        return collection;
    }

    public void setCollection(WatchCollection collection) {
        this.collection = collection;
    }
}
