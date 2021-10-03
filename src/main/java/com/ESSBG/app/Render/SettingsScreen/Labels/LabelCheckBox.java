package com.ESSBG.app.Render.SettingsScreen.Labels;

import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * The LabelCheckBox class is a representation of a Label and a Checkbox grouped as one.
 */
public class LabelCheckBox extends Table {

    private final Label label;
    private final CheckBox checkBox;

    /**
     * Initializes a {@link Label} and a {@link CheckBox} and puts them beside each other.
     *
     * @param labelText is the initial value of the label text
     * @param skin is the skin that is used for the label and checkbox
     */
    public LabelCheckBox(String labelText, Skin skin) {
        this(labelText, 1, skin);
    }

    /**
     * Initializes a {@link Label} and a {@link CheckBox} and puts them beside each other.
     *
     * @param labelText is the initial value of the label text
     * @param boxScale is the initial scale of the checkbox
     * @param skin is the skin that is used for the label and checkbox
     */
    public LabelCheckBox(String labelText, float boxScale, Skin skin) {
        label = new Label(labelText, skin);
        checkBox = new CheckBox("", skin);
        setScaleOnCheckBox(boxScale);
        this.add(label).padRight(10);
        this.add(checkBox);
    }

    /**
     * Sets the scale of the checkbox.
     *
     * @param scale is the new scale
     */
    public void setScaleOnCheckBox(float scale) {
        // set the scale to 1, so we don't scale something that's already scaled
        if (scale != 1) {
            setScaleOnCheckBox(1);
        }
        checkBox.getImage().setScale(scale);
        checkBox.getImageCell().padRight(checkBox.getImage().getWidth()*scale - checkBox.getImage().getWidth());
        checkBox.getImageCell().padTop(checkBox.getImage().getHeight()*scale - checkBox.getImage().getHeight());
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
     * Changes the value of the checkbox to the opposite.
     */
    public void toggle() {
        checkBox.toggle();
    }

    /**
     * Returns the value of the checkbox.
     *
     * @return the current value of the checkbox
     */
    public boolean isChecked() {
        return checkBox.isChecked();
    }

    /**
     * Sets the value of the checkbox to the given argument.
     *
     * @param value the new value of the checkbox
     */
    public void setChecked(boolean value) {
        checkBox.setChecked(value);
    }

}
