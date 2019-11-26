package net.usermd.grabiecm.jee.databases.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends CrudRepository<Car,Long> {

    List<Car> findAllByColor(Color color);
}
