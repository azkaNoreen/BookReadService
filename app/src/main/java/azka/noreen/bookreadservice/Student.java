package azka.noreen.bookreadservice;

public class Student {

    private String name;
    private String email;
    private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emial) {
        this.email = emial;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student() {
    }

    public Student(String name, String emial, String phone) {
        this.name = name;
        this.email = emial;
        this.phone = phone;
    }

    public Student(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
