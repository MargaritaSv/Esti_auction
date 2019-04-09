package org.com.esti.models.service;

import org.com.esti.domain.entities.UserPersonal;

import java.math.BigDecimal;
import java.util.Date;

public class AuctionObjectServiceModel {
    private Long id;
    private String name;
    private BigDecimal estimateTo;
    private BigDecimal estimateFrom;
    private UserPersonal estimatedBy;
    private Date createdAt;
    private Date updatedAt;

    public AuctionObjectServiceModel() {
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

    public UserPersonal getEstimatedBy() {
        return estimatedBy;
    }

    public void setEstimatedBy(UserPersonal estimatedBy) {
        this.estimatedBy = estimatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
