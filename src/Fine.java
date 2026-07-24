import java.util.ArrayList;

public class Fine {
    private static int violationNumber = 1;

    private RadarObservation radarObservation;
    private Vehicle vehicle;

    public float amount;

    public ArrayList<String> violations = new ArrayList<>();

    public Fine calculateFine(RadarObservation radarObservation){

        this.radarObservation = radarObservation;
        this.vehicle = radarObservation.getVehicle();

        this.amount = 0;
        this.violations.clear();
        this.violationNumber = 1;

        if( vehicle.getMaxSpeed() < radarObservation.speed ){
            amount += TrafficRules.speedTax;
            violations.add(violationNumber++ +" - speed of " + radarObservation.speed + " exceeded max allowed " + vehicle.getMaxSpeed() + " : " + TrafficRules.speedTax);
        }

        if(!radarObservation.seatbeltStatus){
            amount += TrafficRules.seatBeltTax;
            violations.add(violationNumber++ +"- Seatbelt not fastened : " + TrafficRules.seatBeltTax +" EGP");
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