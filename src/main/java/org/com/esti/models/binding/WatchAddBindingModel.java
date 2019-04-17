package org.com.esti.models.binding;

import org.com.esti.domain.entities.enums.Closure;
import org.com.esti.domain.entities.enums.Dial;
import org.com.esti.domain.entities.enums.WatchCollection;

public class WatchAddBindingModel extends AuctionObjectAddBindingModel {

    private Dial dial;
    private String caliber;
    private String watchCase;
    private Closure closure;
    private Integer dimensions;
    private WatchCollection collection;

    public WatchAddBindingModel() {
    }

    public Dial getDial() {
        return dial;
    }

    public void setDial(Dial dial) {
        this.dial = dial;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public String getWatchCase() {
        return watchCase;
    }

    public void setWatchCase(String watchCase) {
        this.watchCase = watchCase;
    }

    public Closure getClosure() {
        return closure;
    }

    public void setClosure(Closure closure) {
        this.closure = closure;
    }

    public Integer getDimensions() {
        return dimensions;
    }

    public void setDimensions(Integer dimensions) {
        this.dimensions = dimensions;
    }

    public WatchCollection getCollection() {
        return collection;
    }

    public void setCollection(WatchCollection collection) {
        this.collection = collection;
    }
}
