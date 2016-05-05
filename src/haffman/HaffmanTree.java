package haffman;

import com.sun.istack.internal.Nullable;

/**
 * Created by Administrator on 2016/5/2.
 *
 * 数据结构：霍夫曼树
 */

public class HaffmanTree {

    //文本
    private String text;

    //频率
    private int f = -1;

    //左子树
    private HaffmanTree leftChild;

    //右子树
    private HaffmanTree rightChild;

    public HaffmanTree(){}


    /**
     * &nbsp;&nbsp;&nbsp;&nbsp;
     * 带参数的构造方法。因为霍夫曼是一种递归结构，所以我们传入一个节点的文本，频度，左子树，右子树。
     * <br></br>
     * &nbsp;&nbsp;&nbsp;&nbsp;
     * 若该节点为叶子节点,左子树和右子树为null。
     * @param text 该节点的文本
     * @param f 该节点文本出现的频度
     * @param leftChild 该节点的左子树
     * @param rightChild 该节点的右子树
     */
    public HaffmanTree(String text,
                       int f,
                       @Nullable HaffmanTree leftChild,
                       @Nullable HaffmanTree rightChild) {

        this.text = text;
        this.f = f;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }




    /**
     * @return text 该节点的文本
     */
    public String getText(){
        return this.text;
    }

    /**
     * @return f 该节点文本出现的频度
     */
    public int getF(){
        return this.f;
    }

    /**
     * @return HaffmanTree 该节点的左子树
     */
    public HaffmanTree getRightChild() {
        return rightChild;
    }

    /**
     * @return HaffmanTree 该节点的右子树
     */
    public HaffmanTree getLeftChild() {
        return leftChild;
    }
}
