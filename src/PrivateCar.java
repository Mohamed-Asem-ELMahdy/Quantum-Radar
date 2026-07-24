public class PrivateCar extends Vehicle{
    public PrivateCar(String plateNumber){
        carType = "Private Car";
        this.plateNumber = plateNumber;
    }
    public float getMaxSpeed(){
        return TrafficRules.privateCarMaxSpeed;
    }
}
