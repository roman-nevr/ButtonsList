package org.berendeev.buttonslist.presentation;

import org.berendeev.buttonslist.domain.model.Item;

import java.util.List;

public interface ButtonsListView {
    void showList(List<Item> items);
    void showError();

    interface Router{
        void showSettings();
        void showItem(int number);
    }
}
