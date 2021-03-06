package org.berendeev.buttonslist.presentation;

import org.berendeev.buttonslist.domain.model.Item;

import java.util.List;

public interface SettingsView {
    void showHistory(List<Item> items);
    void addItem(Item item);

    void showError();
    void showInputError();

    void clearInputFields();

    public interface Router{
        void moveToButtonsList();
    }
}
