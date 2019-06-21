package com.ottamotta.yunar;

import java.util.Objects;

public class Dto {

    private long timestamp;
    private String value;

    public Dto() {
    }

    public Dto(Entity entity) {
        timestamp = entity.getTimestamp();
        value = entity.getValue();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dto that = (Dto) o;
        return timestamp == that.timestamp &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, value);
    }
}
