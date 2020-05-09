package UserInterface;

public class VisitedNode {
    private String id; //0
    private String streets; //1
    private String lat; //2
    private String lng; //3

    public VisitedNode(String id, String streets, String lat, String lng) {
        this.id = id;
        this.streets = streets;
        this.lat = lat;
        this.lng = lng;
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

}
