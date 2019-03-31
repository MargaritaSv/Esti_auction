package org.com.esti.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.com.esti.domain.entities.enums.Closure;
import org.com.esti.domain.entities.enums.Dial;
import org.com.esti.domain.entities.enums.WatchCollection;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "watch")
public class Watch extends AuctionObject {

    private Dial dial;
    private String caliber;
    private String watchCase;
    private Closure closure;
    private Integer dimensions;
    private WatchCollection collection;
  //  private UserPersonal user;

    public Watch() {
    }

    @Column(name = "dial", nullable = false)
    @Enumerated(EnumType.STRING)
    public Dial getDial() {
        return dial;
    }

    public void setDial(Dial dial) {
        this.dial = dial;
    }

    @Column(name = "caliber")
    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    @Column(name = "watch_case")
    public String getWatchCase() {
        return watchCase;
    }

    public void setWatchCase(String watchCase) {
        this.watchCase = watchCase;
    }

    @Column(name = "closure", nullable = false)
    @Enumerated(EnumType.STRING)
    public Closure getClosure() {
        return closure;
    }

    public void setClosure(Closure closure) {
        this.closure = closure;
    }

    @Column(name = "dimentions", nullable = false)
    @Min(10)
    public Integer getDimensions() {
        return dimensions;
    }

    public void setDimensions(Integer dimensions) {
        this.dimensions = dimensions;
    }

    @Column(name = "collection")
    @Enumerated(EnumType.STRING)
    public WatchCollection getCollection() {
        return collection;
    }

    public void setCollection(WatchCollection collection) {
        this.collection = collection;
    }

//    public UserPersonal getUser() {
//        return user;
//    }
//
//    public void setUser(UserPersonal user) {
//        this.user = user;
  //  }
}
