package org.berendeev.buttonslist.data.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDiskIOException;

import org.berendeev.buttonslist.domain.model.Item;

import java.util.ArrayList;
import java.util.List;

import static org.berendeev.buttonslist.data.datasource.DatabaseOpenHelper.COEF;
import static org.berendeev.buttonslist.data.datasource.DatabaseOpenHelper.DATABASE_TABLE;
import static org.berendeev.buttonslist.data.datasource.DatabaseOpenHelper.NUMBER;

public class DatabaseDataSource implements Datasource {

    DatabaseOpenHelper openHelper;
    SQLiteDatabase database;
    private ContentValues contentValues;

    public DatabaseDataSource(DatabaseOpenHelper openHelper) {
        this.openHelper = openHelper;
        database = openHelper.getWritableDatabase();
        contentValues = new ContentValues();

    }

    @Override public void saveItem(Item item) {
        contentValues.clear();
        contentValues.put(NUMBER, item.getNumber());
        contentValues.put(COEF, item.getFill());
        long rowId = database.insert(DATABASE_TABLE, null, contentValues);
        if(rowId == -1){
            throw new SQLiteDiskIOException("can't write item");
        }
    }

    @Override public List<Item> getHistory() {
        List<Item> items = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            items.add(getItemFromCursor(cursor));
        }
        cursor.close();
        return items;
    }

    @Override public List<Item> getItems() {
        String[] columns = {NUMBER, COEF};
        Cursor cursor = database.query(true, DATABASE_TABLE, columns, null, null, NUMBER, null, NUMBER + " ASC", null);
        List<Item> items = getFilledListFromCursor(cursor);
        cursor.close();
        return items;
    }

    private Item getItemFromCursor(Cursor cursor) {
        int numberIndex = cursor.getColumnIndex(NUMBER);
        int coefIndex = cursor.getColumnIndex(COEF);
        return new Item(cursor.getInt(numberIndex), cursor.getFloat(coefIndex));
    }

    private List<Item> getItemsFromCursor(Cursor cursor){
        List<Item> items = new ArrayList<>();
        while (cursor.moveToNext()){
            items.add(getItemFromCursor(cursor));
        }
        return items;
    }

    private List<Item> getFilledListFromCursor(Cursor cursor){
        List<Item> items = new ArrayList<>();
        int i = 0;
        int max = 5;
        Item item = null;
        while (cursor.moveToNext()){
            item = getItemFromCursor(cursor);
            while (i < item.getNumber() && i < max){
                items.add(new Item(i, 0f));
                i++;
            }
            items.add(item);
            i++;
        }
        while (i < max){
            items.add(new Item(i, 0f));
            i++;
        }
        return items;
    }

}
