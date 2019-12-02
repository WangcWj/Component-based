package com.example.education.tree;

import java.util.Stack;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/11/6
 */
public class RBTree {



    void rotateLtft(RBEntry N){
        //先拿节点N的右节点。
        RBEntry right = N.right;
        //节点N的右子树的左子树给节点N的左子树。
        N.right = right.left;
        if(right.left != null){
             //指定要被替换掉的左子树重新找到节点N为其父结点
             right.left.parent = N;
        }
        //将N的父结点移交给右子树。
        right.parent = N.parent;

        Stack stack = new Stack();

    }
}
