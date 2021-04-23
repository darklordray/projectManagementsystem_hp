public class Person {

    // Attributes
    String name;
    String telephone_number;
    String email_address;
    String address;

    // Methods constructor
    public Person( String name, String telephone_number, String email_address, String address) {
        
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

    public String getTelephone_number() {
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
        output += "\n:Name" + name;
        output += "\n:Telephone Number" + telephone_number;
        output += "\n:email_address" + email_address;
        output += "\n:address" + address;
    
        return output;
    }

    //Method to Update details
    public void updateTitle(String updateTitle) {
        this.title = updateTitle;
    
}
