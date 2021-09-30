package com.ESSBG.app.Render.SettingsScreen.Labels;

import com.badlogic.gdx.scenes.scene2d.ui.*;

public class LabelCheckBox extends Table {

    private final Label label;
    private final CheckBox checkBox;

    public LabelCheckBox(String labelText, Skin skin) {
        this(labelText, 1, skin);
    }
    public LabelCheckBox(String labelText, float boxScale, Skin skin) {
        label = new Label(labelText, skin);
        checkBox = new CheckBox("", skin);
        checkBox.getImage().setScale(boxScale);
        checkBox.getImageCell().padRight(checkBox.getImage().getWidth()*boxScale - checkBox.getImage().getWidth());
        checkBox.getImageCell().padTop(checkBox.getImage().getHeight()*boxScale - checkBox.getImage().getHeight());
        this.add(label).padRight(10);
        this.add(checkBox);
    }

    public void setLabelText(String text) {
        label.setText(text);
    }

    public void toggle() {
        checkBox.toggle();
    }

    public boolean isChecked() {
        return checkBox.isChecked();
    }

    public void setChecked(boolean value) {
        checkBox.setChecked(value);
    }

}
