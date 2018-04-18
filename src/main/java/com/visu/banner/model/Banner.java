package com.visu.banner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "banners")
public class Banner {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="weight")
    private int weight;

    public Banner() {}

    public Banner(int weight) {
        this.weight = weight;
    }

    public Banner(long id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Banner banner = (Banner) o;

        if (id != banner.id) return false;
        return weight == banner.weight;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", weight=" + weight +
                '}';
    }
}
