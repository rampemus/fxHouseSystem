package houseSystem.addOfferView;

import houseSystem.Kuntoluokka;
import houseSystem.MainController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.*;
import java.util.function.UnaryOperator;

public class AddOfferController extends MainController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField rentField;

    @FXML
    private TextField areaField;

    @FXML
    private TextField builtYearField;

    @FXML
    private ChoiceBox<Kuntoluokka> classField;

    @FXML
    private TextField emailField;

    @FXML
    private TextArea addInfoField;

    @FXML
    private TextField conditionField;


    @FXML
    private Button submitButton;

    @FXML
    public void initialize() {
        classField.getItems().setAll(Kuntoluokka.values());

        setIntegerFilter(rentField);
        setIntegerFilter(builtYearField);
        setEmailFilter(emailField);
        setAddressFilter(addressField);
        setFloatNumberFilter(areaField);

    }

    /**
     * Adds integer filter for textField.
     * Source: https://stackoverflow.com/questions/40472668/numeric-textfield-for-integers-in-javafx-8-with-textformatter-and-or-unaryoperat#40472822
     * @param field
     */
    private void setIntegerFilter(TextField field) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            String minusAndNumbers = "-?([1-9][0-9]*)?";
            if (newText.matches(minusAndNumbers)) {
                return change;
            }
            return null;
        };

        field.setTextFormatter( new TextFormatter<Integer>(integerFilter) );
    }

    /**
     * Adds email regex filter to text field.
     * @param field
     */
    private void setEmailFilter(TextField field) {
        UnaryOperator<TextFormatter.Change> emailFilter = change -> {
            String newText = change.getControlNewText();
            String emailRegex = "[A-Za-z0-9_%-]*@?[A-Za-z]*?.?[A-Za-z]?[A-Za-z]?[A-Za-z]?[A-Za-z]?";
            //I'm bad at regex todo:fix this emailregexfilter
            if ( newText.matches(emailRegex) ) {
                return change;
            }
            return null;
        };

        field.setTextFormatter( new TextFormatter<Integer>(emailFilter) );
    }

    /**
     * Filters address as "##### # # #, #### ##" where # is number or letter
     * @param field
     */
    private void setAddressFilter(TextField field) {
        UnaryOperator<TextFormatter.Change> addressFilter = change -> {
            String newText = change.getControlNewText();
            String finnishAddressRegex = "[A-Za-z0-9 ]*,?[A-Za-z0-9 ]*?";
            if ( newText.matches(finnishAddressRegex) ) {
                return change;
            }
            return null;
        };

        field.setTextFormatter( new TextFormatter<Integer>(addressFilter) );
    }


    /**
     * Filters positive float number with two decimals and
     * accepts , and . as a decimal separator
     * @param field
     */
    private void setFloatNumberFilter(TextField field) {
        UnaryOperator<TextFormatter.Change> floatNumberFilter = change -> {
            String newText = change.getControlNewText();
            String positiveFloatNumberRegex = "[0-9]*[.,]?[0-9]?[0-9]?";
            if ( newText.matches(positiveFloatNumberRegex) ) {
                return change;
            }
            return null;
        };

        field.setTextFormatter( new TextFormatter<Integer>(floatNumberFilter) );
    }
}