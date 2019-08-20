package cn.hsf.hsf.pojo.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kaituozhe
 */
public class Button {

    private List<AbstractButton> button = new ArrayList<>();


    public Button(List<AbstractButton> button) {
        this.button = button;
    }

    public Button() {
    }

    public List<AbstractButton> getButton() {
        return button;
    }

    public void setButton(List<AbstractButton> button) {
        this.button = button;
    }
}
