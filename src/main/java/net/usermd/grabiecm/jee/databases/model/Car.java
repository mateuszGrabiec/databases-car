package net.usermd.grabiecm.jee.databases.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;

@Entity
//@Table(name = "m1023_m1000_base.CAR") //i can change name of entity
public class Car {//implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mark;
    private String model;

    //@Transient colmun wasn't save
    //@Enumerated(EnumType.STRING) saving column as string
    private String color;
    private Year year;

    public Car() {
    }

    public Car(String mark, String model, String color, Year year) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.year=year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
