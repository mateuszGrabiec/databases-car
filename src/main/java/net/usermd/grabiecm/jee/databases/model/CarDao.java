package net.usermd.grabiecm.jee.databases.model;

import java.util.List;

public interface CarDao {

    void saveCar(Car car);
    List<Car> findAll();
    void updateCar(Car newCar);
    void deleteCar(long id);
    Car findById(long id);
}
