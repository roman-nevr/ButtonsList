package org.berendeev.buttonslist.domain;

import org.berendeev.buttonslist.domain.model.Item;

import java.util.List;

public interface Repository {

    public static final int ROWS_NUMBER = 100;

    void setItem(Item item);
    Item getItem(int number);
    List<Item> getHistory();
    List<Item> getItems();
}
