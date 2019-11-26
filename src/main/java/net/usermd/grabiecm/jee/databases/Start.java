package net.usermd.grabiecm.jee.databases;

import net.usermd.grabiecm.jee.databases.model.Car;
import net.usermd.grabiecm.jee.databases.model.CarRepo;
import net.usermd.grabiecm.jee.databases.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class Start {
    private CarRepo carRepo;

    @Autowired
    public Start(CarRepo carRepo) {
        this.carRepo=carRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample(){
        Car car=new Car("Honda","Civic", Color.BLACK.toString(), Year.of(2010));
        carRepo.save(car);
        car=new Car("Audi","A1", Color.RED.toString(),Year.of(2011));
        carRepo.save(car);
        car=new Car("Audi","A3", Color.SILVER.toString(),Year.of(2012));
        carRepo.save(car);
        car=new Car("Audi","A4", Color.YELLOW.toString(),Year.of(2015));
        carRepo.save(car);
    }

}
