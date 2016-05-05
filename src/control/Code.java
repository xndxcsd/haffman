package control;

import haffman.Rule;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/5/2.
 *
 * 该类根据密码本进行编码操作。
 */
public class Code {
    //文本
    private String text;
    //该文本的编码
    private Rule rule;

    //该文本的编码情况
    private String ruleText;

    private String codeText;

    public Code(String text){
        this.text = text;
        rule = new Rule(text);
        setRuleText(rule);
        setcodeText(rule);
    }

    public Rule getRule(){
        return this.rule;
    }

    private void setRuleText(Rule rule){
        HashMap text_code = rule.getText_code();
        StringBuilder sb = new StringBuilder("\t密码本：\n");

        Iterator<HashMap.Entry<String,String>> iter = text_code.entrySet().iterator();

        HashMap.Entry<String,String> entry;

        while(iter.hasNext()){
            entry = iter.next();
            sb.append(entry.getKey()+" >>>>>> "+entry.getValue()+"\n");
        }

        ruleText = sb.toString();
    }

    private void setcodeText(Rule rule) {
        HashMap text_code = rule.getText_code();
        StringBuilder sb = new StringBuilder();

        for (int i=0;i<this.text.length();++i){
            sb.append(text_code.get(String.valueOf(this.text.charAt(i))));
        }
        codeText = sb.toString();
    }
    /**
     * 包装密码本字符串。
     * @return
     */
    public String getRuleText() {
        return ruleText;
    }

    public String getCodeText() {
        return codeText;
    }


}
