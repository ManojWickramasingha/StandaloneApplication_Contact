
public class Contact {
    private String contactID;
    private String name;
    private String number;
    private String company;
    private double salary;
    private String birthday;

    public Contact(String contactID, String name, String number, String company, double salary, String birthday) {
        this.contactID = contactID;
        this.name = name;
        this.number = number;
        this.company = company;
        this.salary = salary;
        this.birthday = birthday;
    }

    // create getters-------------------------------
    public String getContactID() {
        return contactID;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getCompany() {
        return company;
    }

    public double getSalary() {
        return salary;
    }

    public String getBirthday() {
        return birthday;
    }

    // create setters-----------------------------
    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
