package com.hysea.entity.run.list;

import com.hysea.entity.run.tree.MainBodyTreeNode;

import java.util.*;

public class MainBodyList extends ArrayList<MainBodyTreeNode> {

    public void add(MainBodyTreeNode element, MainBodyTreeNode parent) {
        super.add(element);
        element.setParent(parent);
    }
}
