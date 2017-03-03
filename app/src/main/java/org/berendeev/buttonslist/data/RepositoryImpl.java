package org.berendeev.buttonslist.data;

import android.database.sqlite.SQLiteDatabase;

import org.berendeev.buttonslist.data.datasource.Datasource;
import org.berendeev.buttonslist.domain.Item;
import org.berendeev.buttonslist.domain.Repository;

import java.util.List;


public class RepositoryImpl implements Repository {

    Datasource datasource;

    public RepositoryImpl(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override public void setItem(Item item) {

    }

    @Override public List<Item> getHistory() {
        return null;
    }

    @Override public List<Item> getItems() {
        return null;
    }
}
