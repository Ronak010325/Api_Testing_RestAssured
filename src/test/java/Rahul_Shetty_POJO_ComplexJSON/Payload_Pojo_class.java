package Rahul_Shetty_POJO_ComplexJSON;

public class Payload_Pojo_class {
    private String url;
    private String instructor;
    private String services;
    private String expertise;
    private Courses courses;
    private String linkedIn;

    public String getLinkedIn() {
        return linkedIn;
    }

    public String getUrl() {
        return url;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getServices() {
        return services;
    }

    public String getExpertise() {
        return expertise;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }
}