public class GenerateFineReport {

    private static int fineNumber = 1;

    public void generateFineReport(Fine fine){

        System.out.println("======================");
        System.out.println("Fine Number : " + fineNumber++);
        System.out.println("======================");

        System.out.println("traffic fine for " + fine.getVehicle().carType + " : " + fine.getVehicle().plateNumber);
        System.out.println("Total amount : " + fine.amount + " EGP");
        System.out.println("Violations : ");
        for(String violation : fine.violations){
            System.out.println(violation);
        }
        System.out.println("-----------------------------------------");

    }
}


