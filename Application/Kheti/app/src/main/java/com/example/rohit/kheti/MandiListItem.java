package com.example.rohit.kheti;

/**
 * Created by Rohit on 3/18/2018.
 */

public class MandiListItem {
    int id,dist,pricelow,pricemiddle,pricehigh;
    String name;

    public MandiListItem(int id,int dist, int pricelow, int pricemiddle, int pricehigh, String name) {
        this.id = id;
        this.dist = dist;
        this.pricelow = pricelow;
        this.pricemiddle = pricemiddle;
        this.pricehigh = pricehigh;
        this.name = name;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPricelow() {
        return pricelow;
    }

    public void setPricelow(int pricelow) {
        this.pricelow = pricelow;
    }

    public int getPricemiddle() {
        return pricemiddle;
    }

    public void setPricemiddle(int pricemiddle) {
        this.pricemiddle = pricemiddle;
    }

    public int getPricehigh() {
        return pricehigh;
    }

    public void setPricehigh(int pricehigh) {
        this.pricehigh = pricehigh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
