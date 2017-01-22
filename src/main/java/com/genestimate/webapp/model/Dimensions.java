package com.genestimate.webapp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "TYPE")
public abstract class Dimensions {
    private int id;
    private int width;
    private int length;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "WIDTH")
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Basic
    @Column(name = "LENGTH")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dimensions)) return false;

        Dimensions that = (Dimensions) o;

        if (id != that.id) return false;
        if (width != that.width) return false;
        return length == that.length;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + width;
        result = 31 * result + length;
        return result;
    }
}
