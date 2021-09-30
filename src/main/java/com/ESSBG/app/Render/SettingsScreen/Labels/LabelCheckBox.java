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
        setScaleOnCheckBox(boxScale);
        this.add(label).padRight(10);
        this.add(checkBox);
    }

    public void setScaleOnCheckBox(float scale) {
        // set the scale to 1, so we don't scale something that's already scaled
        if (scale != 1) {
            setScaleOnCheckBox(1);
        }
        checkBox.getImage().setScale(scale);
        checkBox.getImageCell().padRight(checkBox.getImage().getWidth()*scale - checkBox.getImage().getWidth());
        checkBox.getImageCell().padTop(checkBox.getImage().getHeight()*scale - checkBox.getImage().getHeight());
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
