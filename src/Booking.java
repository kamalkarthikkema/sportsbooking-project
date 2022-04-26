public class Booking extends Exercise {
    String studentName = "";
    String bookedSlotId = "";
    int rating = 0;

    Booking(int exerciseId, String name, double price, String bookedSlotId, String studentName) {
        super(exerciseId, name, price);
        this.studentName = studentName;
        this.bookedSlotId = bookedSlotId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public int getRating() {
        return this.rating;
    }

    public String getBookedSlotId() {
        return this.bookedSlotId;
    }

}