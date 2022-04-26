public class Exercise {
    private int exerciseId;
    private String exerciseName;
    private double price;

    Exercise(int exerciseId, String name, double price) {
        this.exerciseId = exerciseId;
        this.exerciseName = name;
        this.price = price;
    }

    public int getId() {
        return exerciseId;
    }

    public String getName() {
        return exerciseName;
    }

    public double getPrice() {
        return price;
    }
}
