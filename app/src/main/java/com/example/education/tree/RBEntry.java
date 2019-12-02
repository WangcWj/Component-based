package com.example.education.tree;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/11/6
 */
public class RBEntry {

    RBEntry left;
    RBEntry right;
    RBEntry parent;
    int value;

    public RBEntry getLeft() {
        return left;
    }

    public void setLeft(RBEntry left) {
        this.left = left;
    }

    public RBEntry getRight() {
        return right;
    }

    public void setRight(RBEntry right) {
        this.right = right;
    }

    public RBEntry getParent() {
        return parent;
    }

    public void setParent(RBEntry parent) {
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
