package net.usermd.grabiecm.jee.databases.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import net.usermd.grabiecm.jee.databases.model.*;
import net.usermd.grabiecm.jee.databases.utilities.CheckUtilities;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Year;
import java.util.Collection;


//@Controller
@Route("show-car")
public class CarShowGui extends VerticalLayout {
    private CarDaoImpl carRepo;
    private CheckUtilities checkUtilities;
    private Grid<Car> grid;

    @Autowired
    public CarShowGui(CarDaoImpl carRepo, CheckUtilities checkUtilities) {

        this.carRepo = carRepo;
        this.checkUtilities = checkUtilities;

        NumberField numberFieldId = new NumberField("ID");
        numberFieldId.setValue(1d);
        Button buttonFindCar = new Button("Find Car");
        TextField textFieldMark = new TextField("Mark");
        TextField textFieldModel = new TextField("Model");
        Button buttonAdd = new Button("Modify or add Car");
        TextField textFieldColor = new TextField("Color");
        NumberField numberFieldYear = new NumberField("Year");
        Dialog dialog = new Dialog(new Label("Invalid data"));
        NativeButton confirmButton = new NativeButton("OK", event -> {
            dialog.close();
        });
        dialog.add(confirmButton);

        buttonFindCar.addClickListener(buttonClickEvent -> {
            Car car =null;
                    if(carRepo.findById(numberFieldId.getValue().longValue())!=null)car=carRepo.findById(numberFieldId.getValue().longValue());
            if (car != null) {
                System.out.println(car);
                //fields field
                numberFieldId.setValue((double) car.getId());
                textFieldMark.setValue(car.getMark());
                textFieldModel.setValue(car.getModel());
                textFieldColor.setValue(car.getColor().toString());
                numberFieldYear.setValue((double) car.getYear().getValue());
            } else {
                dialog.open();
            }
        });

        this.carRepo = carRepo;
        this.grid = new Grid<>(Car.class);
        grid.setItems((Collection<Car>) carRepo.findAll());
        grid.addComponentColumn(this::buildDeleteButton);

        buttonAdd.addClickListener(clickEvent -> {
            String mark = textFieldMark.getValue();
            String model = textFieldModel.getValue();
            String color = textFieldColor.getValue();
            Double year = numberFieldYear.getValue();

            if (checkUtilities.checkData(mark, model, color)) {
                long id = numberFieldId.getValue().longValue();
                Car car = new Car(id,mark, model, color, Year.of(year.intValue()));
                if (carRepo.findById(id)!=null) {
                    editCar(car,id);
                } else {
                    carRepo.saveCar(car);
                }
            } else {
                dialog.setCloseOnEsc(false);
                dialog.setCloseOnOutsideClick(false);
                dialog.open();
            }
        });

        add(numberFieldId, buttonFindCar, textFieldMark, textFieldModel, textFieldColor,numberFieldYear, buttonAdd, dialog);
        add(grid);
    }


    private Button buildDeleteButton(Car car) {
        Button button = new Button("Delete");
        button.addClickListener(e -> deleteCar(car));
        return button;
    }

    private void deleteCar(Car car) {
        carRepo.deleteCar(car.getId());
        grid.setItems((Collection<Car>) carRepo.findAll());
    }

    private void editCar(Car car,long id) {
        carRepo.deleteCar(id);
        carRepo.saveCar(car);
        grid.setItems((Collection<Car>) carRepo.findAll());
    }

}
