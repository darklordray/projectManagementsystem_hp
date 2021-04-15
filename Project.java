public class Project {

    // Attributes
    int project_number;
    String project_name;
    String project_type;
    String address;
    String erf;
    int total_cost;
    int cost_owed;
    String deadline;

    // Methods constructor
    public Project(int project_number, String project_name, String project_type, String address, String erf, int total_cost, int cost_owed, String deadline) {
        this.project_number = project_number;
        this.project_name = project_name;
        this.project_type = project_type;
        this.address = address;
        this.erf = erf;
        this.total_cost = total_cost;
        this.cost_owed = cost_owed;
        this.deadline = deadline;
        
    }
    
    public Integer project_number() {
        return project_number;
    }
    public String project_name() {
        return project_name;
    }
    public String project_type() {
        return project_type;
    }
    public String address() {
        return address;
    }
    public String erf() {
        return erf;
    }
    public Integer total_cost() {
        return total_cost;
    }
    public Integer cost_owed() {
        return cost_owed;
    }
    public String deadline() {
        return deadline;
    }
    //toString printing the supplied object
    public String toString() {
        String output = "Project Number: " + project_number;
        output += "\n:Project Name" + project_name;
        output += "\n:Project Type" + project_type;
        output += "\n:Address" + address;
        output += "\n:ERF" + erf;
        output += "\n:Total Cost" + total_cost;
        output += "\n:Cost Owed" + cost_owed;
        output += "\n:Deadline" + deadline;
        
        return output;
    }

}