package org.berendeev.buttonslist.data.datasource;

import android.database.sqlite.SQLiteDatabase;

import org.berendeev.buttonslist.domain.Item;

import java.util.List;

public class DatabaseDataSource implements Datasource {

    SQLiteDatabase database;

    public DatabaseDataSource(SQLiteDatabase database) {
        this.database = database;
    }

    @Override public void setItem(Item item) {
        database.insert()
    }

    @Override public List<Item> getHistory() {
        return null;
    }

    @Override public List<Item> getItems() {
        return null;
    }
}
