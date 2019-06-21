package com.example.myparkinglot;

public class Vehicle {
    public Integer id;
    public Integer lot;

    public Vehicle() {
    }

    public Vehicle(Integer id, Integer lot) {
        this.id = id;
        this.lot = lot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }
}