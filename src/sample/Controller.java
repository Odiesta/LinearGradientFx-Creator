package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.CycleMethod.*;

public class Controller {

    @FXML
    private Slider stop;
    @FXML
    private ColorPicker color;
    @FXML
    private TextField startX;
    @FXML
    private TextField startY;
    @FXML
    private TextField endX;
    @FXML
    private TextField endY;
    @FXML
    private CheckBox proportional;
    @FXML
    private ComboBox<CycleMethod> cycleMethod;
    @FXML
    private Rectangle rectangle;
    @FXML
    private ListView<Stop> stopListView;

    private ObservableList<Stop> stops;

    public void initialize() {
        cycleMethod.setItems(FXCollections.observableArrayList(NO_CYCLE, REFLECT, REPEAT));
        cycleMethod.getSelectionModel().selectFirst();
        stops = FXCollections.observableArrayList();
        stopListView.setItems(stops);
    }

    @FXML
    public void createStop() {
//        Testing purpose
//        System.out.println("stop.getValue() = " + stop.getValue());

        stops.add(new Stop(stop.getValue(), color.getValue()));
    }

    @FXML
    public void createGradient() {
        if (!(isNumber(startX.getText()) && isNumber(startY.getText()) && isNumber(endX.getText()) && isNumber(endY.getText()))) {
            return;
        }

//        Testing purpose
//        System.out.println("startX.getText() = " + startX.getText());
//        System.out.println("startY.getText() = " + startY.getText());
//        System.out.println("endX.getText() = " + endX.getText());
//        System.out.println("endY.getText() = " + endY.getText());
//        System.out.println("proportional.isSelected() = " + proportional.isSelected());
//        System.out.println("cycleMethod.getSelectionModel().getSelectedItem() = " + cycleMethod.getSelectionModel().getSelectedItem());

        double gradientStartX = Double.parseDouble(startX.getText());
        double gradientStartY = Double.parseDouble(startY.getText());
        double gradientEndX = Double.parseDouble(endX.getText());
        double gradientEndY = Double.parseDouble(endY.getText());
        LinearGradient lg = new LinearGradient(gradientStartX, gradientStartY, gradientEndX, gradientEndY, proportional.isSelected(), cycleMethod.getSelectionModel().getSelectedItem(), stops);
        rectangle.setFill(lg);
    }

    @FXML
    public void clearStop() {
        stops.clear();
    }

    private boolean isNumber(String text) {
        return text.matches("\\d+") || text.matches("\\d+.\\d+");
    }

}
