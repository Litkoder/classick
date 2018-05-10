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
	private static final long serialVersionUID = 1L;
	private JTextArea input;
	private JTextArea output;
	private JTextField key1;
	private JTextField key2;
	private JButton encrypt;
	private JButton decrypt;
	private JButton clear;
	
	public ED() {
		init();
		southPanel();
		centerPanel();
		northPanel();
		event();
	}
	/*
	 * 设置监听器
	 * 监听"加密","解密","清屏"按钮
	 */
	public void event() {
		encrypt.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
					encrypt();
				} catch (IOException e1) {
					e1.printStackTrace();
				}			
			}
		});
		decrypt.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
					decrypt();
				} catch (IOException e1) {
					e1.printStackTrace();
				}				
			}
		});	
		/*
		 * 设置清屏功能
		 */
		clear.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				input.setText("");	
				output.setText("");
				key1.setText("");
				key2.setText("");
			}
		});
	}
	
	/*
	 * 设置加密按钮
	 */
	private void encrypt() throws IOException {
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
				fenceEncrypt(message,mykey2);					
			} else {
				JOptionPane.showMessageDialog(null, "密钥只能同时输入一个!");
				key1.requestFocus();
			}			
		}
	}
	/*
	 * 设置解密按钮
	 */
	private void decrypt() throws IOException {
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
	public String caesarEncrypt(String plainText, String key) {
		StringBuilder cipherText = new StringBuilder();
		int k = Integer.parseInt(key);
		for (char c : plainText.toCharArray()) {			//将字符串对象中的字符转换为一个字符数组
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
			cipherText.append(c);
		}
		output.setText(cipherText.toString());
		return cipherText.toString();	
	}
	/*
	 * 凯撒密码解密部分
	 */
	public String caesarDecrypt(String cipherText, String key) {
		StringBuilder plainText = new StringBuilder();
		int k = Integer.parseInt(key);
		k = 0 - k;
		for (char c : cipherText.toCharArray()) {					//将字符串对象中的字符转换为一个字符数组
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
			plainText.append(c);
		}
		output.setText(plainText.toString());
		return plainText.toString();
	}
	
	/*
	 * 栅栏密码加密部分
	 */
	public String fenceEncrypt(String plainText, String key) {
		int k = Integer.parseInt(key);
		int r = k;
		int len = plainText.length();
		int c = len / k;
		char mat[][] = new char[r][c];
		int p = 0;  
		String cipherText = "";
		for(int i = 0; i < c; i++) {
			for(int j = 0; j < r; j++) {
				if(p!=len) {
					mat[j][i] = plainText.charAt(p++);
				} else {
					mat[j][i] = 'X';
				}
			}
		}
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				cipherText += mat[i][j];
			}
		}
		output.setText(cipherText);
		return cipherText;
	}
	/*
	 * 栅栏密码解密部分
	 */
	String fenceDecrypt(String cipherText, String key) {
		int k = Integer.parseInt(key);
		int r = k;
		int len = cipherText.length();
		int c = len / k;
		char mat[][] = new char[r][c];
		int p = 0;
		String plainText = "";
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				mat[i][j] = cipherText.charAt(p++);
			}
		}
		for(int i = 0; i < c; i++) {
			for(int j = 0; j < r; j++) {
				plainText += mat[j][i];
			}
		}
		output.setText(plainText);
		return plainText;
	}

	/*
	 * 创建南边的Panel
	 * 用来显示"加密"、"解密"、"清屏"的按钮
	 */
	public void southPanel() {
		JPanel south = new JPanel();							
		encrypt = new JButton("加 密");
		decrypt = new JButton("解 密");
		clear = new JButton("清 屏");
		south.add(encrypt);
		south.add(decrypt);
		south.add(clear);
		this.add(south,BorderLayout.SOUTH);					//将Panel放在Frame的南边
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

		JPanel panelnorth = new JPanel();					
        panelnorth.setLayout(new BorderLayout());			//采用边界布局管理器
        panelnorth.add("North", north1);
        panelnorth.add("Center", north2);
		this.add(panelnorth,BorderLayout.NORTH);			//将Panel放在Frame的北边
	}
	/*
	 * 创建中间的panel
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
		this.add(center,BorderLayout.CENTER);				//将Panel放在Frame的中间
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
