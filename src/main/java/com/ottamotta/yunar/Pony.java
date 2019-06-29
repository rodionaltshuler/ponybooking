package com.ottamotta.yunar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "ponies")
public class Pony {

    @Id
    String name;

    @Column
    boolean available;

    @Column(name = "returned_timestamp")
    long returnedTimestamp;

    @Column(name = "recharge_time_mins")
    int rechargeTimeMins;


    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public long getReturnedTimestamp() {
        return returnedTimestamp;
    }

    public int getRechargeTimeMins() {
        return rechargeTimeMins;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setReturnedTimestamp(long returnedTimestamp) {
        this.returnedTimestamp = returnedTimestamp;
    }

    public boolean isRecharged() {
        return returnedTimestamp < 0 ||
                Instant.ofEpochMilli(returnedTimestamp)
                        .plus(rechargeTimeMins, ChronoUnit.MINUTES)
                        .isAfter(Instant.ofEpochMilli(System.currentTimeMillis()));
    }
}
