import java.util.ArrayList;

public class Fine {

    private RadarObservation radarObservation;
    private Vehicle vehicle;

    public float amount;

    public ArrayList<String> violations = new ArrayList<>();

    public Fine calculateFine(RadarObservation radarObservation){

        this.radarObservation = radarObservation;
        this.vehicle = radarObservation.getVehicle();

        this.amount = 0;
        this.violations.clear();

        if( vehicle.getMaxSpeed() < radarObservation.speed ){
            amount += TrafficRules.speedTax;
            violations.add("- speed of " + radarObservation.speed + " exceeded max allowed " + TrafficRules.truckMaxSpeed);
        }

        if(!radarObservation.seatbeltStatus){
            amount += TrafficRules.seatBeltTax;
            violations.add("- Seatbelt not fastened : " + TrafficRules.seatBeltTax);
        }

        return this;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public RadarObservation getRadarObservation() {
        return radarObservation;
    }
}