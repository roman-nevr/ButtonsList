package org.berendeev.buttonslist.data;

import org.berendeev.buttonslist.data.datasource.Datasource;
import org.berendeev.buttonslist.domain.model.Item;
import org.berendeev.buttonslist.domain.Repository;

import java.util.List;


public class RepositoryImpl implements Repository {

    private Datasource datasource;

    public RepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override public void setItem(Item item) {
        datasource.saveItem(item);
    }

    @Override public Item getItem(int number) {
        return datasource.getItem(number);
    }

    @Override public List<Item> getHistory() {
        return datasource.getHistory();
    }

    @Override public List<Item> getItems() {
        return datasource.getItems();
    }
}
