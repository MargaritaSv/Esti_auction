package org.com.esti.domain.entities;

import org.com.esti.domain.entities.enums.WineCollections;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "wines")
public class Wine extends AuctionObject {

    private WineCollections collection;
    private Integer numberOfBottles;

    public Wine() {
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "collection", nullable = false)
    public WineCollections getCollection() {
        return collection;
    }

    public void setCollection(WineCollections collection) {
        this.collection = collection;
    }

    @Column(name = "number_of_bottles")
    @Min(1)
    @Max(500)
    public Integer getNumberOfBottles() {
        return numberOfBottles;
    }

    public void setNumberOfBottles(Integer numberOfBottles) {
        this.numberOfBottles = numberOfBottles;
    }
}
