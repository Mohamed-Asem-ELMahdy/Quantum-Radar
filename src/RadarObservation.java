import java.util.Date;

public class RadarObservation {
    private Vehicle vehicle;

    public RadarObservation(Vehicle vehicle, Date observationTime, float speed, boolean seatbeltStatus) {
        this.vehicle = vehicle;
        this.observationTime = observationTime;
        this.speed = speed;
        this.seatbeltStatus = seatbeltStatus;
    }

    public Date observationTime;

    public float speed;

    public boolean seatbeltStatus;

    public Vehicle getVehicle() {
        return vehicle;
    }
    public RadarObservation getRadarObservation() {
        return this;
    }

}
