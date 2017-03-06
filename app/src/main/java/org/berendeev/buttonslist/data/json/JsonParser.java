package org.berendeev.buttonslist.data.json;

import org.berendeev.buttonslist.domain.model.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonParser {
    public static final String JSON = "[{0, 0.1}, {2, 0.0}, {4, 1}]";
    public static final String SNIPPET = "{4, 1.0}";

    public static List<Item> parseList(String json){
        List<Item> items = new ArrayList<>();
        int startItemIndex = 0;
        int endItemIndex = 0;
        while (startItemIndex < json.length()){
            if (json.charAt(startItemIndex) != '{'){
                startItemIndex++;
            }else {
                endItemIndex = startItemIndex + 1;
                while (json.charAt(endItemIndex) != '}'){
                    endItemIndex++;
                    if (endItemIndex >= json.length()){
                        throw new IllegalArgumentException("wrong json");
                    }
                }
                endItemIndex++;
                items.add(parseItem(json.substring(startItemIndex, endItemIndex)));
                startItemIndex = endItemIndex;
            }
        }
        return items;
    }

    public static Item parseItem(String json){
        json = json.substring(1, json.length() - 1);
        String[] args = json.split(",");
        int number = Integer.parseInt(args[0]);
        float fill = Float.parseFloat(args[1]);
        return new Item(number, fill);
    }

    public static String toJson(List<Item> items){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Iterator<Item> iter = items.iterator();
        while (iter.hasNext()){
            Item item = iter.next();
            stringBuilder.append("{" + item.getNumber() + ", " + item.getFill() + "}");
            if (iter.hasNext()){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
