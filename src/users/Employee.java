package users;

public class Employee extends User {
	private String specialization;
    private Double salary;
    private Integer experience;
    private String department;
    private String dateOfJoining;
    
    public Employee(String specialization, Double salary, Integer experience, String department, String dateOfJoining) {
        super();
        this.specialization = specialization;
        this.salary = salary;
        this.experience = experience;
        this.department = department;
        this.dateOfJoining = dateOfJoining;
    }
    
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }
    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
}
