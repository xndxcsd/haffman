package GUI;

import com.sun.xml.internal.bind.marshaller.DataWriter;
import control.Code;
import control.Decode;
import haffman.Rule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Writer;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/4/24.
 */
public class MainFrame  extends JFrame{


    private JTextArea srcArea;
    private JTextArea ruleArea;
    private JTextArea destArea;

    private Rule rule;
    private Code code;
    private Decode decode;
    public MainFrame(String title){
        // 文本框面板
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1,3));

        srcArea = this.getTextArea(true);
        ruleArea = this.getTextArea(false);
        destArea = this.getTextArea(true);


        JScrollPane srcPane = new JScrollPane(srcArea);
        JScrollPane rulePane = new JScrollPane(ruleArea);
        JScrollPane destPane = new JScrollPane(destArea);


        textPanel.add(srcPane,BorderLayout.WEST);
        textPanel.add(rulePane,BorderLayout.CENTER);
        textPanel.add(destPane,BorderLayout.EAST);

        add(textPanel);
        // 按钮面板

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));

        JButton codeButton = new JButton("code");
        JButton clearButton = new JButton("clear");
        JButton decodeButton = new JButton("decode");

        buttonPanel.add(codeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(decodeButton);

        /*完成译码*/
        codeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                code = new Code(srcArea.getText());
                rule = code.getRule();
                ruleArea.setText(code.getRuleText());
                destArea.setText(code.getCodeText());
            }
        });

        /*清理所有文本框中的信息*/
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srcArea.setText("");
                ruleArea.setText("");
                destArea.setText("");
            }
        });

        /*提供密码本和编码后的文本进行译码*/
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rule!=null){
                    decode = new Decode(destArea.getText(),rule);
                    srcArea.setText(decode.getSrctext().toString());
                }else{
                    JOptionPane.showMessageDialog(null,"message");
                }
            }
        });

        this.add(buttonPanel,BorderLayout.SOUTH);

        // 设置属性

        this.setTitle(title);
        this.setSize(new Dimension(800,450));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JTextArea getTextArea(boolean editable){
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(editable);
        return textArea;
    }

}
