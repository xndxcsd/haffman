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

    private LinkedHashMap<String,Integer> map;

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

    private HaffmanTree constructHaffmanTree(HashMap map){
        while(map.size()>0){
            HaffmanTree left = getMin(map);
            HaffmanTree right = getMin(map);

            map.put(null,left.getF()+right.getF());
            return new HaffmanTree(null,left.getF()+right.getF(),left,right);
        }
        return null;
    }

    private HaffmanTree getMin(HashMap map) {
        Iterator<HashMap.Entry<String,Integer>> iter = map.entrySet().iterator();
        HashMap.Entry<String,Integer> entry;
        String minkey = null;
        int minValue=-1;
        if(iter.hasNext()){
            entry = iter.next();
            minkey = entry.getKey();
            minValue = entry.getValue();
        }

        while(iter.hasNext()){
            entry = iter.next();
            int value = entry.getValue();
            if(minValue>value) {
                minkey = entry.getKey();
                minValue = value;
            }
        }
        map.remove(minkey);
        return new HaffmanTree(minkey,minValue,null,null);
    }


    private LinkedHashMap<String,Integer> constructMap(String text) {
        map = new LinkedHashMap<String,Integer>();
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
