package models;

public class Student extends Person {

    private String course;
    private int yearLevel;

    public Student(String personId, String name, String password, String course, int yearLevel) {
        super(personId, name, password);
        this.course    = course;
        this.yearLevel = yearLevel;}

    public String getCourse()   { return course; }
    public int getYearLevel()   { return yearLevel; }

    @Override
    public String getRole() {
        return "Student";}


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Course: " + course + " | Year Level: " + yearLevel);}
}