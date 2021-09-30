package com.ESSBG.app.Render.SettingsScreen.Labels;

import com.badlogic.gdx.scenes.scene2d.ui.*;

public class LabelTextField extends Table {

    private final Label label;
    private final TextField textField;

    public LabelTextField(String labelText, String textFieldText, Skin skin) {
        label = new Label(labelText, skin);
        textField = new TextField(textFieldText, skin);
        this.add(label).padRight(10);
        this.add(textField);

    }

    public void setInputToDigitsOnly() {
        textField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
    }

    public void setInputToLettersOnly() {
        TextField.TextFieldFilter textFilter = (textField, c) ->
                Character.isAlphabetic(c);
        textField.setTextFieldFilter(textFilter);
    }

    public String getTextFieldText() {
        return textField.getText();
    }

    public void setLabelText(String text) {
        label.setText(text);
    }

    public void setTextFieldText(String text) {
        textField.setText(text);
    }

    public void setTextFieldWidth(float width) {
        this.getCell(textField).width(width);
    }
}
