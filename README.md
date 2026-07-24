# Quantum Radar — Traffic Fine System

##  Design Overview
The system is built around these core classes:
### `Vehicle` *(Abstract)*
The base class for all vehicle types. Holds `plateNumber` and `carType`, and defines the abstract method `getMaxSpeed()`.
### `PrivateCar` & `Truck` *(extends Vehicle)*
Concrete vehicle types. Each sets its own `carType` and returns its allowed max speed from `TrafficRules`.
### `TrafficRules`
A constants class holding the speed limits and tax amounts:
- `privateCarMaxSpeed = 80.0`
- `truckMaxSpeed = 60.0`
- `speedTax = 300.0`
- `seatBeltTax = 100.0`
### `RadarObservation`
Records a single radar snapshot: the vehicle observed, time, speed, and seatbelt status.
### `Fine`
Takes a `RadarObservation` and calculates violations:
- Speeding → +300 EGP
- No seatbelt → +100 EGP
### `GenerateFineReport`
Prints the fine report for a given observation.
---
## Class Diagram (Simplified)
```
Vehicle (abstract)
├── PrivateCar
└── Truck
RadarObservation ──uses──> Vehicle
Fine ──uses──> RadarObservation, TrafficRules
GenerateFineReport ──uses──> Fine
```
---
## Code Snippets
### `Vehicle` — Abstract Base Class
```java
public abstract class Vehicle {
    public String plateNumber;
    public String carType;
    public abstract float getMaxSpeed();
}
```
### `Truck` — extends Vehicle
```java
public class Truck extends Vehicle {
    public Truck(String plateNumber) {
        carType = "Truck";
        this.plateNumber = plateNumber;
    }
    public float getMaxSpeed() {
        return TrafficRules.truckMaxSpeed;
    }
}
```
### `PrivateCar` — extends Vehicle
```java
public class PrivateCar extends Vehicle {
    public PrivateCar(String plateNumber) {
        carType = "Private Car";
        this.plateNumber = plateNumber;
    }
    public float getMaxSpeed() {
        return TrafficRules.privateCarMaxSpeed;
    }
}
```
### `TrafficRules` — Constants
```java
public class TrafficRules {
    public static float truckMaxSpeed = 60.0F;
    public static float privateCarMaxSpeed = 80.0F;
    public static float speedTax = 300.0F;
    public static float seatBeltTax = 100.0F;
}
```
### `RadarObservation` — Snapshot
```java
public class RadarObservation {
    private Vehicle vehicle;
    public Date observationTime;
    public float speed;
    public boolean seatbeltStatus;
    public RadarObservation(Vehicle vehicle, Date observationTime, float speed, boolean seatbeltStatus) {
        this.vehicle = vehicle;
        this.observationTime = observationTime;
        this.speed = speed;
        this.seatbeltStatus = seatbeltStatus;
    }
    public Vehicle getVehicle() { return vehicle; }
}
```
### `Fine` — Violation Calculator
```java
public class Fine {
    private RadarObservation radarObservation;
    private Vehicle vehicle;
    public float amount;
    public ArrayList<String> violations = new ArrayList<>();
    public Fine calculateFine(RadarObservation radarObservation) {
        this.radarObservation = radarObservation;
        this.vehicle = radarObservation.getVehicle();
        this.amount = 0;
        this.violations.clear();
        if (vehicle.getMaxSpeed() < radarObservation.speed) {
            amount += TrafficRules.speedTax;
            violations.add("Speed exceeded max allowed: " + TrafficRules.speedTax + " EGP");
        }
        if (!radarObservation.seatbeltStatus) {
            amount += TrafficRules.seatBeltTax;
            violations.add("Seatbelt not fastened: " + TrafficRules.seatBeltTax + " EGP");
        }
        return this;
    }
}
```
### `GenerateFineReport` — Report Printer
```java
public class GenerateFineReport {
    private static int fineNumber = 1;
    public void generateFineReport(Fine fine) {
        System.out.println("======================");
        System.out.println("Fine Number : " + fineNumber++);
        System.out.println("======================");
        System.out.println("Traffic fine for " + fine.getVehicle().carType + " : " + fine.getVehicle().plateNumber);
        System.out.println("Total amount : " + fine.amount + " EGP");
        System.out.println("Violations : ");
        for (String violation : fine.violations) {
            System.out.println(violation);
        }
        System.out.println("-----------------------------------------");
    }
}
```
---
## How It Works
1. Create a `Vehicle` (`PrivateCar` or `Truck`) with a plate number.
2. Create a `RadarObservation` with the vehicle, speed, and seatbelt status.
3. Pass the observation to `Fine.calculateFine()`.
4. Print the report using `GenerateFineReport`.
