package net.usermd.grabiecm.jee.databases.model;

import java.time.Year;


public class Car {

    private long id;

    private String mark;
    private String model;

    private String color;
    private Year year;

    public Car() {
    }

    public Car(Long id, String mark, String model, String color, Year year) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.year = year;
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


    public Car(Long id, String mark, String model, String color, String year) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.year = Year.of(Integer.parseInt(year));
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                '}';
    }
}
