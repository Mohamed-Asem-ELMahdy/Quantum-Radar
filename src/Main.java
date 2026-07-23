
void main() {

    Vehicle vehicle1 = new PrivateCar("ABC123");
    RadarObservation radarObservation = new RadarObservation(vehicle1, new Date(), 100, true);

    Fine fineCalculator = new Fine();

    GenerateFineReport generateFineReport = new GenerateFineReport();

    Fine fine = fineCalculator.calculateFine(radarObservation);
    generateFineReport.generateFineReport(fine);

    Vehicle vehicle2 = new Truck("XYZ456");
    RadarObservation radarObservation2 = new RadarObservation(vehicle2, new Date(), 120, false);

    fine = fineCalculator.calculateFine(radarObservation2);
    generateFineReport.generateFineReport(fine);


}
