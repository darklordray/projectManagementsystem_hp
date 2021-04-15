public class Person {

    // Attributes
    String title;
    String name;
    int telephone_number;
    String email_address;
    String address;

    // Methods constructor
    public Person(String title, String name, int telephone_number, String email_address, String address) {
        this.title = title;
        this.name = name;
        this.telephone_number = telephone_number;
        this.email_address = email_address;
        this.address = address;
    }
    
    public String getTitle() {
        return title;
    }
    public String getName() {
        return name;
    }

    public Integer getTelephone_number() {
        return telephone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public String getAddress() {
        return address;
    }

    //toString printing the supplied object
    public String toString() {
        String output = "Title: " + title;
        output += "\n:Name" + name;
        output += "\n:Telephone Number" + telephone_number;
        output += "\n:email_address" + email_address;
        output += "\n:address" + address;
    
        return output;
    }

}
