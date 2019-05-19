package org.com.esti.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.com.esti.web.controllers.BaseController;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "auction")
@JsonIgnoreProperties(
        value = {"created_at", "updated_at"},
        allowGetters = true
)
public class Auction extends BaseController {

    private Integer id;
    private String name;
    private String place;
    private LocalDate date;
    private UserPersonal estimatedBy;
    private Date createdAt;
    private Date updatedAt;
    private Set<AuctionObject> auctionObjects;

    public Auction() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Column(name = "date", columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estimated_by", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    public UserPersonal getEstimatedBy() {
        return estimatedBy;
    }

    public void setEstimatedBy(UserPersonal estimatedBy) {
        this.estimatedBy = estimatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    @CreatedDate
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upated_at", nullable = false)
    @LastModifiedDate
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "auction_abjects",
//            joinColumns = @JoinColumn(name = "auction_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "auction_object_id", referencedColumnName = "id"))
//    public Set<AuctionObject> getAuctionObjects() {
//        return auctionObjects;
//    }
//
//    public void setAuctionObjects(Set<AuctionObject> auctionObjects) {
//        this.auctionObjects = auctionObjects;
//    }
}
