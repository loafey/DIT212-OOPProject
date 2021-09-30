package com.ESSBG.app.Render.SettingsScreen.Labels;

import com.badlogic.gdx.scenes.scene2d.ui.*;


/**
 * The LabelTextField class is a representation of a Label and a TextField grouped as one.
 */
public class LabelTextField extends Table {

    private final Label label;
    private final TextField textField;

    /**
     * Initializes a {@link Label} and a {@link TextField} and puts them beside each other.
     *
     * @param labelText is the initial value of the label text
     * @param textFieldText is the initial value that is put inside the text field
     * @param skin is the skin that is used for the label and field
     */
    public LabelTextField(String labelText, String textFieldText, Skin skin) {
        label = new Label(labelText, skin);
        textField = new TextField(textFieldText, skin);
        this.add(label).padRight(10);
        this.add(textField);

    }

    /**
     * Sets the text field to only accept digits.
     */
    public void setInputToDigitsOnly() {
        textField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
    }

    /**
     * Sets the text field to only accept letters.
     */
    public void setInputToLettersOnly() {
        TextField.TextFieldFilter textFilter = (textField, c) ->
                Character.isAlphabetic(c);
        textField.setTextFieldFilter(textFilter);
    }

    /**
     * Returns the text inside the text field.
     *
     * @return the text inside the text field
     */
    public String getTextFieldText() {
        return textField.getText();
    }

    /**
     * Sets the label text.
     *
     * @param text the new label text
     */
    public void setLabelText(String text) {
        label.setText(text);
    }

    /**
     * Sets the text inside the text field.
     *
     * @param text the new text that will be put in the text field
     */
    public void setTextFieldText(String text) {
        textField.setText(text);
    }

    /**
     * Sets the width of the text field.
     *
     * @param width the new width of text field
     */
    public void setTextFieldWidth(float width) {
        this.getCell(textField).width(width);
    }
}
