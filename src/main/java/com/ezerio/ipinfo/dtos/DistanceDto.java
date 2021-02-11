package com.ezerio.ipinfo.dtos;

public class DistanceDto {

    private String averageDistance;
    private String farthestDistance;
    private String closestDistance;

    public String getAverageDistance() {
        return averageDistance;
    }

    public void setAverageDistance(String averageDistance) {
        this.averageDistance = averageDistance;
    }

    public String getFarthestDistance() {
        return farthestDistance;
    }

    public void setFarthestDistance(String farthestDistance) {
        this.farthestDistance = farthestDistance;
    }

    public String getClosestDistance() {
        return closestDistance;
    }

    public void setClosestDistance(String closestDistance) {
        this.closestDistance = closestDistance;
    }
}
