/*
 * Copyright (c) 2019.  Nahum Martinez
 */

package com.api.pgc.core.APIRestPGC.service.TreeNodes;

import java.util.ArrayList;
import java.util.List;

public class MyTreeNode<T> {
    private T data = null;

    private List<MyTreeNode<T>> children = new ArrayList<>();

    private MyTreeNode<T> parent = null;

    public MyTreeNode(T data) {
        this.data = data;
    }

    public MyTreeNode<T> addChild(MyTreeNode<T> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<MyTreeNode<T>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public List<MyTreeNode<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(MyTreeNode<T> parent) {
        this.parent = parent;
    }

    public MyTreeNode<T> getParent() {
        return parent;
    }
}
