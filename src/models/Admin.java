package models;

public class Admin extends Person {

    private String department;

    public Admin(String personId, String name, String password, String department) {
        super(personId, name, password);
        this.department = department;
    }

    public String getDepartment() { return department; }

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Department: " + department);
    }
}