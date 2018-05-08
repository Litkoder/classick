package bishe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class ED extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea input;
	private JTextArea output;
	private JTextField key1;
	private JTextField key2;
	private JButton jiami;
	private JButton jiemi;
	private JButton clear;
	
	public ED() {
		init();
		southPanel();
		centerPanel();
		northPanel();
		event();
	}

	public void event() {
		jiami.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
					jiami();
				} catch (IOException e1) {
					e1.printStackTrace();
				}			
			}
		});
		jiemi.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
					jiemi();
				} catch (IOException e1) {
					e1.printStackTrace();
				}				
			}
		});		
	}

	private void jiami() throws IOException {
		String message = input.getText();					//获取输入区的内容
		String mykey1=key1.getText();						//获取key1的内容
		String mykey2=key2.getText();						//获取key2的内容

		if(message == null || message.equals("")) {
			JOptionPane.showMessageDialog(null, "输入的明文/密文不能为空!");    
			input.requestFocus();
		} else {
			if((mykey1 == null || mykey1.equals("")) && (mykey2 == null || mykey2.equals(""))) {
				JOptionPane.showMessageDialog(null, "密钥不能为空!");    
				key1.requestFocus();
			} else if (mykey1 != null && (mykey2 == null || mykey2.equals(""))) {
				JOptionPane.showMessageDialog(null, "测试1!"); 
				caesarEncrypt(message,mykey1);							
			} else if((mykey1 == null || mykey1.equals("")) && mykey2 != null) {
				JOptionPane.showMessageDialog(null, "测试2!");
				fenceEncryption(message,mykey2);					
			} else {
				JOptionPane.showMessageDialog(null, "密钥只能同时输入一个!");
				key1.requestFocus();
			}			
		}
	}

	private void jiemi() throws IOException {
		String message = input.getText();					//获取输入区的内容
		String mykey1=key1.getText();						//获取key1的内容
		String mykey2=key2.getText();						//获取key2的内容

		if(message == null || message.equals("")) {
			JOptionPane.showMessageDialog(null, "输入的明文/密文不能为空!");    
			input.requestFocus();
		} else {

			if((mykey1 == null || mykey1.equals("")) && (mykey2 == null || mykey2.equals(""))) {
				JOptionPane.showMessageDialog(null, "密钥不能为空!");    
				key1.requestFocus();
			} else if (mykey1 != null && (mykey2 == null || mykey2.equals(""))) {
				JOptionPane.showMessageDialog(null, "测试1!");    
				caesarDecrypt(message,mykey1);					
			} else if((mykey1 == null || mykey1.equals("")) && mykey2 != null) {
				JOptionPane.showMessageDialog(null, "测试2!");
				fenceDecrypt(message,mykey2);	
			} else {
				JOptionPane.showMessageDialog(null, "密钥只能同时输入一个!");
				key1.requestFocus();
			}			
		}
	}
	/*
	 * 凯撒密码加密部分
	 */
	public String caesarEncrypt(String str, String key) {
		StringBuilder result = new StringBuilder();
		int k = Integer.parseInt(key);
		for (char c : str.toCharArray()) {					//将字符串对象中的字符转换为一个字符数组
			if (c >= 'a' && c <= 'z') {						// 如果字符串中的某个字符是小写字母
				c += k % 26;								// 移动key%26位
					if (c < 'a')
						c += 26;
					if (c > 'z')
						c -= 26;
			}																				
			else if (c >= 'A' && c <= 'Z') {				// 如果字符串中的某个字符是大写字母
				c += k % 26;								// 移动key%26位
				if (c < 'A')
					c += 26;
				if (c > 'Z')
					c -= 26;
			}
			result.append(c);
		}
		output.setText(result.toString());
		return result.toString();	
	}
	/*
	 * 凯撒密码解密部分
	 */
	public String caesarDecrypt(String str, String key) {
		StringBuilder result = new StringBuilder();
		int k = Integer.parseInt(key);
		k = 0 - k;
		for (char c : str.toCharArray()) {					//将字符串对象中的字符转换为一个字符数组
			if (c >= 'a' && c <= 'z') {						// 如果字符串中的某个字符是小写字母
				c += k % 26;								// 移动key%26位
					if (c < 'a') {
						c += 26;
					}
					if (c > 'z') {
						c -= 26;
					}
			}																		
			else if (c >= 'A' && c <= 'Z') {				// 如果字符串中的某个字符是大写字母
				c += k % 26;								// 移动key%26位
				if (c < 'A') {
					c += 26;
				}
				if (c > 'Z') {
					c -= 26;
				}
			}
			result.append(c);
		}
		output.setText(result.toString());
		return result.toString();
	}
	/*
	 * 栅栏密码加密部分
	 */
	public String fenceEncryption(String str, String key){
        String p = new String();
        String p1 = new String();
        String p2 = new String();
        int k = Integer.parseInt(key);
        for    (int i = 0; i < str.length(); i++){
            if(i%k == 0){
                p1 += p.valueOf(str.charAt(i));					//charAt(i)就是字符串从左向右数的第i个字符
            }
            else{
                p2 += p.valueOf(str.charAt(i)); 
            }
        }
        output.setText((p1+p2).toString());
        return p1+p2;
    }
	/*
	 * 栅栏密码解密部分
	 */
	public String fenceDecrypt(String str, String key){
        String result = new String();
        String p = new String();
        int k = Integer.parseInt(key);
        String p1 = str.substring(0,str.length()/k);
        String p2 = str.substring(str.length()/k);       
        int i;
        for(i = 0; i < p1.length(); i++){
            result += p.valueOf(p1.charAt(i)) + p.valueOf(p2.charAt(i));
        }
        if(str.length()%k != 0){
        	result += p.valueOf(p2.charAt(i));
        }
        output.setText(result.toString());
        return result;
    }
	/*
	 * 创建南边的Panel
	 * 用来显示"加密"、"解密"、"清屏"的按钮
	 */
	public void southPanel() {
		JPanel south = new JPanel();							
		jiami = new JButton("加 密");
		jiemi = new JButton("解 密");
		clear = new JButton("清 屏");
		south.add(jiami);
		south.add(jiemi);
		south.add(clear);
		this.add(south,BorderLayout.SOUTH);						//将Panel放在Frame的南边
	}
	/*
	 * 创建北边的Panel
	 * 用来显示输入输出明文/密文的文本区域
	 */
	public void northPanel() {
		JPanel north1 = new JPanel();
		JLabel source = new JLabel ("请输入明文/密文:");
		input = new JTextArea(7,40);
		north1.add(source);
		north1.add(input);
		source.setFont(new Font("1",Font.PLAIN,13));
		input.setFont(new Font("1",Font.PLAIN,13));

		JPanel north2 = new JPanel();
		JLabel result = new JLabel ("输出的明文/密文:");
		output = new JTextArea(7,40);
		north2.add(result);
		north2.add(output);
		result.setFont(new Font("1",Font.PLAIN,13));
		output.setFont(new Font("1",Font.PLAIN,13));
		output.setEditable(false);

		JPanel panelnorth = new JPanel();						// 画板采用边界布局管理器
        panelnorth.setLayout(new BorderLayout());
        panelnorth.add("North", north1);
        panelnorth.add("Center", north2);
		this.add(panelnorth,BorderLayout.NORTH);	
	}
	/*
	 * 中间的panel
	 * 用来显示"凯撒密码密钥"和"栅栏密码密钥"的输入框
	 */
	public void centerPanel() {
		JPanel center = new JPanel();
		JLabel caesar = new JLabel ("凯撒密码密钥:");
		key1 = new JTextField(3);
		JLabel fence = new JLabel ("栅栏密码密钥:");
		key2 = new JTextField(3);
		center.add(caesar);
		center.add(key1);
		center.add(fence);
		center.add(key2);
		caesar.setFont(new Font("1",Font.PLAIN,13));
		key1.setFont(new Font("1",Font.PLAIN,13));
		fence.setFont(new Font("1",Font.PLAIN,13));
		key2.setFont(new Font("1",Font.PLAIN,13));
		this.add(center,BorderLayout.CENTER);
	}
	/*
	 * 初始化窗体
	 */
	public void init() {										
		this.setLocation(350,150);
		this.setSize(600,400);
		this.setVisible(true);
	}


	public static void main(String[] args) {
		new ED();
	}
}

