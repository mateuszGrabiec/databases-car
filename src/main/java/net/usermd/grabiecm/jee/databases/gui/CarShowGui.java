package net.usermd.grabiecm.jee.databases.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import net.usermd.grabiecm.jee.databases.model.Car;
import net.usermd.grabiecm.jee.databases.model.CarRepo;
import net.usermd.grabiecm.jee.databases.model.Color;
import net.usermd.grabiecm.jee.databases.utilities.CheckUtilities;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Year;
import java.util.Collection;


//@Controller
@Route("show-car")
public class CarShowGui extends VerticalLayout {
    private CarRepo carRepo;
    private CheckUtilities checkUtilities;
    private Grid<Car> grid;

    @Autowired
    public CarShowGui(CarRepo carRepo, CheckUtilities checkUtilities) {

        this.carRepo = carRepo;
        this.checkUtilities = checkUtilities;

        NumberField numberFieldId = new NumberField("ID");
        numberFieldId.setValue(1d);
        Button buttonFindCar = new Button("Find Car");
        TextField textFieldMark = new TextField("Mark");
        TextField textFieldModel = new TextField("Model");
        Button buttonAdd = new Button("Modify Car");
        TextField textFieldColor = new TextField("Color");
        NumberField numberFieldYear = new NumberField("Year");
        Dialog dialog = new Dialog(new Label("Invalid data"));
        NativeButton confirmButton = new NativeButton("OK", event -> {
            dialog.close();
        });
        dialog.add(confirmButton);

        buttonFindCar.addClickListener(buttonClickEvent -> {
            Car car =null;
                    if(carRepo.findById(numberFieldId.getValue().longValue()).isPresent())car=carRepo.findById(numberFieldId.getValue().longValue()).get();
            if (car != null) {
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
        //grid.addComponentColumn(this::buildEditButton);
        add(grid);

        buttonAdd.addClickListener(clickEvent -> {
            String mark = textFieldMark.getValue();
            String model = textFieldModel.getValue();
            String color = textFieldColor.getValue();
            Double year = numberFieldYear.getValue();

            if (checkUtilities.checkData(mark, model, color)) {
                long id = numberFieldId.getValue().longValue();
                Car car = new Car(mark, model, color, Year.of(year.intValue()));
                if (carRepo.findById(id).isPresent()) {
                    editCar(car,id);
                } else {
                    dialog.open();
                }
            } else {
                dialog.setCloseOnEsc(false);
                dialog.setCloseOnOutsideClick(false);
                dialog.open();
            }
        });

        add(numberFieldId, buttonFindCar, textFieldMark, textFieldModel, textFieldColor, buttonAdd, dialog);
    }


    private Button buildDeleteButton(Car car) {
        Button button = new Button("Delete");
        button.addClickListener(e -> deleteCar(car));
        return button;
    }

    private void deleteCar(Car car) {
        carRepo.delete(car);
        grid.setItems((Collection<Car>) carRepo.findAll());
    }

    private void editCar(Car car,long id) {
        carRepo.deleteById(id);
        carRepo.save(car);
        grid.setItems((Collection<Car>) carRepo.findAll());
    }

//    private Button buildEditButton(Car car) {
//        Button button = new Button("Edit");
//        button.addClickListener(e -> editCar(car));
//        return button;
//    }
}
