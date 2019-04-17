package org.com.esti.models.view;

import org.com.esti.domain.entities.UserPersonal;

import java.math.BigDecimal;
import java.util.Date;

public abstract class AuctionObjectViewModel {
    private Long id;
    private String name;
    private String imageUrl;
    private BigDecimal estimateTo;
    private BigDecimal estimateFrom;


    public AuctionObjectViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getEstimateTo() {
        return estimateTo;
    }

    public void setEstimateTo(BigDecimal estimateTo) {
        this.estimateTo = estimateTo;
    }

    public BigDecimal getEstimateFrom() {
        return estimateFrom;
    }

    public void setEstimateFrom(BigDecimal estimateFrom) {
        this.estimateFrom = estimateFrom;
    }
}
