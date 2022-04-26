import java.util.*;

public class USC {
    static ArrayList<TimeTable> timeTable = new ArrayList<>();
    static Exercise yoga = new Exercise(1, "Yoga", 1.50);
    static Exercise zumba = new Exercise(2, "Zumba", 2);
    static Exercise aquacise = new Exercise(3, "Aquacise", 5);
    static Exercise boxFit = new Exercise(4, "Box Fit", 3.5);
    static Exercise bodyBlitz = new Exercise(5, "Body Blitz", 3.75);
    static Exercise sports = new Exercise(6, "Sports", 1);
    static Exercise[] exerciseClass = {yoga, zumba, aquacise, boxFit, bodyBlitz, sports};

    static ArrayList<Booking> bookings = new ArrayList<>();

    static private void setUpTimeTable() {

        int year = 2020;
        int month = Calendar.MARCH;
        Calendar calendar = new GregorianCalendar(year, month, 1);
        int i = 0;
        while (i < 16) {
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
                String date = calendar.get(Calendar.DAY_OF_MONTH) + "-" + (calendar.get(Calendar.MONTH) + 1);
                for (int j = 0; j < 3; j++) {
                    int exerciseClassIndex = day == Calendar.SATURDAY ? j : j + 3;
                    TimeTable timetable = new TimeTable(exerciseClass[exerciseClassIndex].getId(),
                            exerciseClass[exerciseClassIndex].getName(), exerciseClass[exerciseClassIndex].getPrice(),
                            i + "" + j, date, j == 0 ? "9:00 to 11:00" : j == 1 ? "14:00 to 16:00" : "18:00 to 20:00");
                    timeTable.add(timetable);
                }
                i++;
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

    static private void timeTableByDate(String date) {

        Iterator<TimeTable> iterator = timeTable.iterator();
        boolean found = false;
        System.out.println("ID\t\t\t" + "Date" + "\t\t\t" + "Classes" + "\t\t\t" + "Slot" + "\t\t\t" + "Vacancy");
        while (iterator.hasNext()) {
            TimeTable st = (TimeTable) iterator.next();
            if (st.getDate().equals(date)) {
                found = true;
                System.out.println(st.getSlotId() + "\t\t\t" + st.getDate() + "\t\t\t" + st.getName()
                        + "\t\t\t" + st.getSlot() +
                        "\t\t\t" + st.getVacancy());
            }

        }
        if (!found) {
            System.out.println("No slots available!");
        }

    }

    static private void timeTableByName(String name) {

        Iterator<TimeTable> iterator = timeTable.iterator();
        boolean found = false;
        System.out.println("ID\t\t\t" + "Date" + "\t\t\t" + "Classes" + "\t\t\t" + "Slot" + "\t\t\t" + "Vacancy");
        while (iterator.hasNext()) {
            TimeTable st = (TimeTable) iterator.next();
            if (st.getName().equals(name)) {
                found = true;
                System.out.println(st.getSlotId() + "\t\t\t" + st.getDate() + "\t\t\t" + st.getName()
                        + "\t\t\t" + st.getSlot() +
                        "\t\t\t" + st.getVacancy());
            }

        }
        if (!found) {
            System.out.println("No slots available!");
        }

    }

    static void getTimeTable() {
        Iterator<TimeTable> iterator = timeTable.iterator();
        System.out.println("ID\t\t\t" + "Date" + "\t\t\t" + "Classes" + "\t\t\t" + "Slot" + "\t\t\t" + "Vacancy");
        while (iterator.hasNext()) {
            TimeTable st = (TimeTable) iterator.next();

            System.out.println(st.getSlotId() + "\t\t\t" + st.getDate() + "\t\t\t" + st.getName()
                    + "\t\t\t" + st.getSlot() +
                    "\t\t\t" + st.getVacancy());

        }
    }

    static private void bookingSlot(String name, String slotId) {

        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getBookedSlotId().equals(slotId)
                    && bookings.get(i).getStudentName().equals(name)) {
                System.out.println("You have already booked it!");
                return;
            }

        }

        Iterator<TimeTable> iterator = timeTable.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            TimeTable st = (TimeTable) iterator.next();
            if (st.getSlotId().equals(slotId)) {
                if (st.getVacancy() > 0) {
                    found = true;
                    Booking booking = new Booking(st.getId(), st.getName(), st.getPrice(), st.getSlotId(), name);
                    bookings.add(booking);
                    st.decreaseVacancy();
                    System.out.println("Your booking has been done.");

                } else {
                    System.out.println("This class is full please try different class.");
                    return;
                }
            }

        }
        if (!found) {
            System.out.println("Slot ID is wrong.");
        }

    }

    static private void changeSlot(String name) {
        if (bookings.size() == 0) {
            System.out.println("Name not found");
            return;
        }
        System.out.println("Student Name\tSlot Id\tDate");
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getStudentName().equals(name)) {
                for (int j = 0; j < timeTable.size(); j++) {
                    if (timeTable.get(j).getSlotId().equals(bookings.get(i).getBookedSlotId())) {
                        System.out.println(bookings.get(i).getStudentName() + "\t\t\t"
                                + timeTable.get(j).getSlotId() + "\t\t\t" + timeTable.get(j).getDate());
                    }

                }
            } else {
                System.out.println("Name not found!");
                return;
            }

        }
        System.out.println();
        System.out.println("Here is your all allocated slot, please enter slot ID which you have to change: ");
        String old_slot_id = scan.next();
        System.out.println("Please enter a new slot ID");
        String newSlotId = scan.next();

        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getStudentName().equals(name)
                    && bookings.get(i).getBookedSlotId().equals(old_slot_id)) {
                for (int j = 0; j < timeTable.size(); j++) {
                    if (timeTable.get(j).getSlotId().equals(old_slot_id)) {
                        timeTable.get(j).increaseVacancy();
                        bookingSlot(name, newSlotId);
                        bookings.remove(i);
                    }

                }
            } else {
                System.out.println("Student name and slot ID doesn't match, please try again!");
            }

        }

    }

    static void rating(String name) {
        if (bookings.size() == 0) {
            System.out.println("Name not found.");
            return;
        }
        System.out.println("Student Name\tSlot ID\tDate");
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getStudentName().equals(name)) {
                for (int j = 0; j < timeTable.size(); j++) {
                    if (timeTable.get(j).getSlotId().equals(bookings.get(i).getBookedSlotId())) {
                        System.out.println(bookings.get(i).getStudentName() + "\t\t\t"
                                + timeTable.get(j).getSlotId() + "\t\t\t" + timeTable.get(j).getDate());
                    }

                }
            } else {
                System.out.println("Name not found");
                return;
            }

        }
        System.out.println("Which class would you like to rate, enter slot ID?");
        String slot_id = scan.next();
        System.out.println("Give rating from 1 to 5 (1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied");

        try {
            int rating = scan.nextInt();
            if (rating > 0 && rating < 6) {
                for (int i = 0; i < bookings.size(); i++) {
                    if (bookings.get(i).getBookedSlotId().equals(slot_id)) {
                        bookings.get(i).setRating(rating);
                    }
                }
            } else {
                System.out.println("Make sure that you have provided rating from 1 to 5, and try again.");
                rating(name);
            }
        } catch (InputMismatchException e) {
            System.out.println("Make sure that you have provided rating from 1 to 5, and try again.");
            rating(name);
        }

    }

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        setUpTimeTable();
        boolean exit = false;
        String select = "";
        String studentNameLookup;

        while (!exit) {
            System.out.println("Please Select Options");
            System.out.println("1. Get full timetable");
            System.out.println("2. Find timetable by date ");
            System.out.println("3. Find timetable by exercise name");
            System.out.println("4. Book your class by id");
            System.out.println("5. Change your class ");
            System.out.println("6. Give rating ");
            System.out.println("7. Exit ");
            select = scan.next();
            switch (select) {
                case "1":
                    getTimeTable();
                    break;
                case "2":
                    System.out.println("Enter date for searching timetable as (d-m) format your classes start from March 2020");
                    String date = scan.next();
                    timeTableByDate(date);
                    break;
                case "3":
                    System.out.println("Enter name for searching classes. We are providing following classes ");
                    for (int i = 0; i < 6; i++) {
                        System.out.println(i + "." + exerciseClass[i].getName());
                    }
                    String name = scan.next();
                    timeTableByName(name);
                    break;
                case "4":
                    System.out.println("Enter your name: ");
                    String studentName = scan.next();
                    System.out.println("Enter slot ID: ");
                    String slot_id = scan.next();
                    bookingSlot(studentName, slot_id);
                    break;
                case "5":
                    System.out.println("Enter your name for finding your allocated slot: ");
                    studentNameLookup = scan.next();
                    changeSlot(studentNameLookup);
                    break;
                case "6":
                    System.out.println("Give rating of the class: ");
                    System.out.println("Enter your name so we can find your attended class: ");
                    studentNameLookup = scan.next();
                    rating(studentNameLookup);
                    break;
                default:
                    break;

            }
        }

    }
}
