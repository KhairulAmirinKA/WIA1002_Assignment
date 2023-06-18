package ga_wia1002;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveFileContainer implements Serializable {
    private List<Object> contentList;

    public SaveFileContainer() {
        contentList = new ArrayList<>();
    }

    public void add(Object obj) {
        contentList.add(obj);
    }

    public List<Object> getContentList() {
        return contentList;
    }
}

