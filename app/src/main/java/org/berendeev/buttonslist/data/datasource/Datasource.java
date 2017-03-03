package org.berendeev.buttonslist.data.datasource;

import org.berendeev.buttonslist.domain.Item;

import java.util.List;

public interface Datasource {
    void setItem(Item item);
    List<Item> getHistory();
    List<Item> getItems();
}
