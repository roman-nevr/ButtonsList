package org.berendeev.buttonslist;

import org.berendeev.buttonslist.data.datasource.DatabaseDataSource;
import org.berendeev.buttonslist.data.datasource.DatabaseOpenHelper;
import org.berendeev.buttonslist.domain.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class Database {

    private DatabaseDataSource dataSource;

    @Before
    public void before(){
        DatabaseOpenHelper openHelper = DatabaseOpenHelper.getInstance(RuntimeEnvironment.application.getApplicationContext());
        dataSource = new DatabaseDataSource(openHelper);
    }

    @Test
    public void insertTest(){
        Item item = new Item(1, 0.5f);
        dataSource.saveItem(item);
    }

    @Test
    public void getHistoryTest(){
        Item item = new Item(1, 0.5f);
        dataSource.saveItem(item);
        List<Item> items = null;
        items = dataSource.getHistory();
        System.out.println(items);
    }

    @Test
    public void getFilteredItemsTest(){
        fillDatabase(dataSource);
        List<Item> items = null;
        items = dataSource.getItems();
        System.out.println(items);
    }

    private void fillDatabase(DatabaseDataSource dataSource){
        dataSource.saveItem(new Item(1, 0.5f));
        dataSource.saveItem(new Item(2, 0.5f));
        dataSource.saveItem(new Item(1, 0.7f));
        dataSource.saveItem(new Item(2, 0.1f));
        dataSource.saveItem(new Item(3, 0.5f));
    }
}
