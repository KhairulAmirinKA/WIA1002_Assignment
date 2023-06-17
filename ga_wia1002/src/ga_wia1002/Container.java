/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ga_wia1002;

/**
 *
 * @author nordi
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Container implements Serializable {
    private List<Object> contentList;

    public Container() {
        contentList = new ArrayList<>();
    }

    public void add(Object obj) {
        contentList.add(obj);
    }

    public List<Object> getContentList() {
        return contentList;
    }
}

