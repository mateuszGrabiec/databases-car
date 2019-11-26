package net.usermd.grabiecm.jee.databases.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(Car car) {
        String sql = "INSERT INTO cars VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, car.getId(), car.getMark(), car.getModel(), car.getColor(), car.getYear().toString());
    }

    @Override
    public List<Car> findAll() {

        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                String.valueOf(element.get("year"))
        )));
        return  carList;
    }


    @Override
    public Car findById(long id) {
        String sql = "SELECT * FROM cars WHERE cars.id = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql,id);
        List<Car> carList= new ArrayList<>();
        maps.forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                String.valueOf(element.get("year"))
        )));
        if (carList.size()>0) return carList.get(0);
        else return null;
    }

    @Override
    public void updateCar(Car newcar) {
        String sql = "UPDATE cars SET cars.mark = ?, cars.model = ?, cars.color = ?, cars.year = ? WHERE cars.id=?";
        jdbcTemplate.update(sql, newcar.getMark(),newcar.getModel(), newcar.getColor(), newcar.getYear(), newcar.getId());

    }

    @Override
    public void deleteCar(long id) {

        String sql = "DELETE FROM  cars WHERE  cars.id=?";
        jdbcTemplate.update(sql, id);
    }

}
