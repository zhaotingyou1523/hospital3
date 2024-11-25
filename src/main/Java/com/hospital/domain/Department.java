package main.Java.com.hospital.domain;

public class Department {
    //科室
    private long id; // 번호
    private String name; // 이름

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
