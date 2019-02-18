package com.softteco.roadqualitydetector.model;

public class Evaluation {
    String bumps,speed,distance, quality;
    String location;
    double ax,ay,az;
    String Uid;

    public String getBumps() {
        return bumps;
    }


    public void setBumps(String bumps) {
        this.bumps = bumps;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getAx() {
        return ax;
    }

    public void setAx(double ax) {
        this.ax = ax;
    }

    public double getAy() {
        return ay;
    }

    public void setAy(double ay) {
        this.ay = ay;
    }

    public double getAz() {
        return az;
    }

    public void setAz(double az) {
        this.az = az;
    }
}
