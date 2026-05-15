package models;


public abstract class Person {

    private String personId;
    private String name;
    private String password;

    public Person(String personId, String name, String password) {
        this.personId = personId;
        this.name     = name;
        this.password = password;}

    public String getPersonId()  { return personId; }
    public String getName()      { return name; }
    public String getPassword()  { return password; }


    public void setName(String name) { this.name = name; }


    public boolean checkPassword(String inputPass) {
        return this.password.equals(inputPass);}

    public abstract String getRole();


    public void displayInfo() {
        System.out.println("Name: " + name + " | ID: " + personId + " | Role: " + getRole());}}