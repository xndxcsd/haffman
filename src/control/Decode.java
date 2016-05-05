package control;

import haffman.Rule;

import java.util.HashMap;
/**
 * Created by Administrator on 2016/5/2.
 *
 * 该类根据密码本和编码进行译码操作。
 */
public class Decode {


    private StringBuilder srctext;

    public Decode(String code,Rule rule){
        setSrctext(decode(code,rule.getCode_text()));
    }

    private void setSrctext(StringBuilder srctext){
        this.srctext = srctext;
    }
    public StringBuilder decode(String code,HashMap<String,String> code_text){
        StringBuilder srctext = new StringBuilder();
        String word;
        int startIndex=0;
        for(int i=0;i<code.length();i++){
            word = code.substring(startIndex,i+1);
            if(code_text.containsKey(word)){
                srctext.append(code_text.get(word));
                startIndex=i+1;
            }
        }

        return srctext;
    }

    public StringBuilder getSrctext(){

        return srctext;
    }


}
