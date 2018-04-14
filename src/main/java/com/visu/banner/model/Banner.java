package com.visu.banner.model;

public class Banner {
    private long id;
    private int weight;

    public Banner(long id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
