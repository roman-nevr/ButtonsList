package org.berendeev.buttonslist.data.datasource;

import org.berendeev.buttonslist.domain.model.Item;

import java.util.List;

public interface Datasource {
    void saveItem(Item item);
    List<Item> getHistory();
    List<Item> getItems();
}
