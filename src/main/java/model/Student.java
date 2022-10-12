package model;

public class Student {
    protected int id, id_classes;
    protected String name;
    protected String dateofBirth;
    protected String address,phoneNumber,email;

    public Student(String name, String dateofBirth, String address, String phoneNumber, String email) {
    }

    public Student(int id, String name, String dateofBirth, String address, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.dateofBirth = dateofBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Student(int id, int id_classes, String name, String dateofBirth, String address, String phoneNumber, String email) {
        this.id = id;
        this.id_classes = id_classes;
        this.name = name;
        this.dateofBirth = dateofBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_classes() {
        return id_classes;
    }

    public void setId_classes(int id_classes) {
        this.id_classes = id_classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
