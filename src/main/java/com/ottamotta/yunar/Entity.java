package com.ottamotta.yunar;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "data", indexes = {@Index(name = "ts_index", columnList = "timestamp", unique = false)})
public class Entity {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "value")
    private String value;

    public Entity() {
    }

    public Entity(long timestamp, String value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
