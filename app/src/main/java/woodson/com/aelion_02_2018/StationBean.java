package woodson.com.aelion_02_2018;

/**
 * Created by AELION on 23/02/2018.
 */

public class StationBean {
    String name;
    String address;
    PositionBean position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PositionBean getPosition() {
        return position;
    }

    public void setPosition(PositionBean position) {
        this.position = position;
    }
}
