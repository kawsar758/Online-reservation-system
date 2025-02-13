public class Reservation {

    private String name;
    private String age;
    private String trainName;
    private String trainNumber;
    private String classType;
    private String from;
    private String to;
    private String date;
    private String pnrNumber;

    public Reservation(String name, String age, String trainName, String trainNumber, String classType, String from, String to, String date) {
        this.name = name;
        this.age = age;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.from = from;
        this.to = to;
        this.date = date;

        // Generate a simple PNR number
        this.pnrNumber = trainNumber + "-" + (int)(Math.random() * 10000); // Simpler random PNR generation
    }

    // Getters for each field
    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getClassType() {
        return classType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getPnrNumber() {
        return pnrNumber;
    }
}
