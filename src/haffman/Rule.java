package haffman;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.*;

/**
 * Created by Administrator on 2016/5/2.
 * <p>
 * 该类为编码译码的密码本。能够通过文本构建霍夫曼树，从而获得密码本。
 */

public class Rule {

    //需要编码的文本
    private String text;

//    private List rule;

    private HashMap<String,Integer> map;

    //the haffmantree mapped by the text;
    private HaffmanTree tree ;



    //the rules that code;
    private HashMap<String,String> text_code;

    //the rule that decode;
    private HashMap<String,String> code_text;
    /**
     * &nbsp;&nbsp;&nbsp;&nbsp;
     * 构造函数。传入需要编码的文本。
     *
     * @param text 需要编码的文本。不能为null。
     */
    public Rule(@NotNull String text) {
        this.text = text;
        this.map = constructMap(this.text);

        if(!map.isEmpty()){

            this.tree = constructHaffmanTree(map);
            //赋予key-value . value-key
            text_code = new HashMap<String,String>();
            code_text = new HashMap<String,String>();
            setRule(this.tree,"");
        }
    }

    public Rule() {
    }

//    private class Node{
//        String text;
//        int f;
//
//        public Node(String text, int f) {
//            this.text = text;
//            this.f = f;
//        }
//
//        public int getF() {
//            return f;
//        }
//
//        public String getText() {
//            return text;
//        }
//    }

    private HaffmanTree constructHaffmanTree(HashMap map){
        ArrayList<HaffmanTree> nodes = new ArrayList<>();

        Iterator<HashMap.Entry<String,Integer>> iter = map.entrySet().iterator();

        HashMap.Entry<String,Integer> entry;
        while(iter.hasNext()){
            entry = iter.next();
            nodes.add(new HaffmanTree(entry.getKey(),entry.getValue(),null,null));
        }


        while(nodes.size()>0){
            HaffmanTree left = getMin(nodes);
            HaffmanTree right = getMin(nodes);

            nodes.add(new HaffmanTree(null,left.getF()+right.getF(),left,right));

            if (nodes.size()==1)
                break;
        }
        return nodes.get(0);
    }

//    private HaffmanTree getMin(HashMap map) {
//        Iterator<HashMap.Entry<String,Integer>> iter = map.entrySet().iterator();
//        HashMap.Entry<String,Integer> entry;
//        String minkey = null;
//        int minValue=-1;
//        if(iter.hasNext()){
//            entry = iter.next();
//            minkey = entry.getKey();
//            minValue = entry.getValue();
//        }
//
//        while(iter.hasNext()){
//            entry = iter.next();
//            int value = entry.getValue();
//            if(minValue>value) {
//                minkey = entry.getKey();
//                minValue = value;
//            }
//        }
//        map.remove(minkey);
//        return new HaffmanTree(minkey,minValue,null,null);
//    }
    private HaffmanTree getMin(ArrayList<HaffmanTree> list) {

        Iterator<HaffmanTree> iter = list.iterator();
        HaffmanTree tempNode;
        HaffmanTree minNode = list.get(0);
        while(iter.hasNext()){
            tempNode = iter.next();
            if(tempNode.getF()<minNode.getF()) {
                minNode = tempNode;
            }
        }
        list.remove(minNode);
        return minNode;
    }


    private HashMap<String,Integer> constructMap(String text) {
        map = new HashMap<String,Integer>();
        for (int i = 0; i<text.length(); ++i) {
            String word = String.valueOf(text.charAt(i));
            if (map.containsKey(word)) {
                int n = (int) map.get(word);
                map.replace(String.valueOf(word), ++n);
            } else {
                map.put(String.valueOf(word), 1);
            }
        }
        return map;
    }
    private void setRule(@Nullable HaffmanTree tree, String code){
        if(tree.getText()==null) {
            setRule(tree.getLeftChild(),code+"0");
            setRule(tree.getRightChild(),code+"1");
        }else {
            text_code.put(tree.getText(),code);
            code_text.put(code,tree.getText());
        }
    }

    public HashMap<String, String> getText_code() {
        return text_code;
    }

    public HashMap<String, String> getCode_text() {
        return code_text;
    }

}
