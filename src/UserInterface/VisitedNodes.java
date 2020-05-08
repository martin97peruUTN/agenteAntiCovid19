package UserInterface;

public class VisitedNodes {
    private String id;
    private String lat;
    private String lng;
    private String north;
    private String south;
    private String east;
    private String west;

    public VisitedNodes(String id, String lat, String lng, String north, String south, String east, String west) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }
}
