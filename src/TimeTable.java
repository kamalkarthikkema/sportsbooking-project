public class TimeTable extends Exercise {

    private String id = "";
    private String slot = "";
    private String date = "";
    private int vacancy = 4;

    TimeTable(int exerciseId, String name, double price, String id, String date, String slot) {

        super(exerciseId, name, price);
        this.id = id;
        this.slot = slot;
        this.date = date;

    }

    public String getSlotId() {
        return id;
    }

    public String getSlot() {
        return slot;
    }

    public String getDate() {
        return date;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void decreaseVacancy() {
        this.vacancy--;
    }

    public void increaseVacancy() {
        this.vacancy++;
    }

}