public class Student {
    private int id;
    private String name, roll, grade, email;

    public Student(int id, String name, String roll, String grade, String email) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.grade = grade;
        this.email = email;
    }

    public int getId() { return id; }
}
