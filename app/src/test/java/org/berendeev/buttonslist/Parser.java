package org.berendeev.buttonslist;

import org.berendeev.buttonslist.data.json.JsonParser;
import org.berendeev.buttonslist.domain.model.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    @Test
    public void fromJson(){
        System.out.println(JsonParser.parseList(JsonParser.toJson(getList())));
        System.out.println(Float.parseFloat("0.5"));
        System.out.println(JsonParser.parseItem("{2, 0.7}"));
    }

    @Test
    public void toJson(){
        System.out.println(JsonParser.toJson(getList()));
    }

    private List<Item> getList(){
        List<Item> items = new ArrayList<>();
        items.add(new Item(0, 0.5f));
        items.add(new Item(1, 0.7f));
        return items;
    }
}
