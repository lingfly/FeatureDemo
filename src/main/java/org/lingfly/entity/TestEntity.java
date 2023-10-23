package org.lingfly.entity;


import lombok.Data;

import java.util.Objects;

@Data
public class TestEntity {
    private String string;
    private int a;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity entity = (TestEntity) o;
        return getA() == entity.getA() && Objects.equals(getString(), entity.getString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getString(), getA());
    }
}
