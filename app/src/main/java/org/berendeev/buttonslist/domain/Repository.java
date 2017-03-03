package org.berendeev.buttonslist.domain;

import java.util.List;

public interface Repository {

    void setItem(Item item);
    List<Item> getHistory();
    List<Item> getItems();
}
