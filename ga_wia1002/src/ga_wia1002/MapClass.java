package ga_wia1002;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapClass implements Serializable {
    private List<Object> contentList;

    public MapClass() {
        contentList = new ArrayList<>();
    }

    public void add(Object obj) {
        contentList.add(obj);
    }

    public List<Object> getContentList() {
        return contentList;
    }
}

