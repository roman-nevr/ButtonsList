package org.berendeev.buttonslist;

import org.berendeev.buttonslist.data.datasource.DatabaseDataSource;
import org.berendeev.buttonslist.data.datasource.DatabaseOpenHelper;
import org.berendeev.buttonslist.domain.model.Item;
import org.bouncycastle.util.test.TestFailedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import static org.berendeev.buttonslist.domain.Repository.ROWS_NUMBER;

@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class Database {

    private DatabaseDataSource dataSource;

    @Before
    public void before(){
        DatabaseOpenHelper openHelper = DatabaseOpenHelper.getInstance(RuntimeEnvironment.application.getApplicationContext());
        dataSource = new DatabaseDataSource(openHelper);
        fillDatabase(dataSource);
    }

    @Test
    public void getItemTest() throws Exception {
        Item item = getFilteredItems().get(1);
        if(!item.equals(dataSource.getItem(1))){
            throw new Exception();
        }
    }

    @Test
    public void getHistoryTest() throws Exception {
        List<Item> items = dataSource.getHistory();
        if(!items.equals(createItems())){
            throw new Exception();
        }
    }

    @Test
    public void getFilteredItemsTest() throws Exception{
        List<Item> items = null;
        items = dataSource.getItems();
        if(!getFilteredItems().equals(items)){
            throw new Exception();
        }
    }

    private void fillDatabase(DatabaseDataSource dataSource){
        List<Item> items = createItems();
        for (Item item : items) {
            dataSource.saveItem(item);
        }
    }

    private List<Item> createItems(){
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 0.5f));
        items.add(new Item(2, 0.5f));
        items.add(new Item(1, 0.7f));
        items.add(new Item(2, 0.1f));
        items.add(new Item(3, 0.5f));
        return items;
    }

    private List<Item> getFilteredItems(){
        List<Item> items = new ArrayList<>();
        items.add(new Item(0, 0.0f));
        items.add(new Item(1, 0.7f));
        items.add(new Item(2, 0.1f));
        items.add(new Item(3, 0.5f));
        items.add(new Item(4, 0.0f));
        for (int i = 0; i < ROWS_NUMBER - 5; i++) {
            items.add(new Item(i + 5, 0f));
        }
        return items;
    }
}
