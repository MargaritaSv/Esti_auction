package org.com.esti.models.view;

import org.com.esti.domain.entities.enums.WatchCollection;

public class WatchViewModel extends AuctionObjectViewModel {

    private WatchCollection collection;

    public WatchViewModel() {
    }

    public WatchCollection getCollection() {
        return collection;
    }

    public void setCollection(WatchCollection collection) {
        this.collection = collection;
    }
}
