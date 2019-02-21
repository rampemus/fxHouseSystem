package houseSystem;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * Has filter selection insertion methods for searchOfferView and addOfferView controllers
 */
public class TextFieldFilter {
    /**
     * Adds integer filter for textField.
     * Source: https://stackoverflow.com/questions/40472668/numeric-textfield-for-integers-in-javafx-8-with-textformatter-and-or-unaryoperat#40472822
     * @param field
     */
    public static void setIntegerFilter(TextField field) {
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
    public static void setEmailFilter(TextField field) {
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
     * TextFieldFilter address as "##### # # #, #### ##" where # is number or letter
     * @param field
     */
    public static void setAddressFilter(TextField field) {
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
     * TextFieldFilter positive float number with two decimals and
     * accepts , and . as a decimal separator
     * @param field
     */
    public static void setFloatNumberFilter(TextField field) {
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
