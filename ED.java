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
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.UnknownHostException;


public class ED extends JFrame {
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
		String message = input.getText();			//获取输入区的内容
		String mykey1=key1.getText();				//获取key1的内容
		String mykey2=key2.getText();				//获取key2的内容

		if(message == null || message.equals("")) {
			JOptionPane.showMessageDialog(null, "输入的明文/密文不能为空!");    
			input.requestFocus();
		} else {

			if((mykey1 == null || mykey1.equals("")) && (mykey2 == null || mykey2.equals(""))) {
				JOptionPane.showMessageDialog(null, "密钥不能为空!");    
				key1.requestFocus();
			} else if (mykey1 != null && (mykey2 == null || mykey2.equals(""))) {
				JOptionPane.showMessageDialog(null, "测试1!"); 
				encrypt(message,mykey1);		
					
			} else if((mykey1 == null || mykey1.equals("")) && mykey2 != null) {
				JOptionPane.showMessageDialog(null, "测试2!");
				
					
			} else {
				JOptionPane.showMessageDialog(null, "密钥只能同时输入一个!");
				key1.requestFocus();
			}			
		}
	}

	private void jiemi() throws IOException {
		String message = input.getText();			//获取输入区的内容
		String mykey1=key1.getText();				//获取key1的内容
		String mykey2=key2.getText();				//获取key2的内容

		if(message == null || message.equals("")) {
			JOptionPane.showMessageDialog(null, "输入的明文/密文不能为空!");    
			input.requestFocus();
		} else {

			if((mykey1 == null || mykey1.equals("")) && (mykey2 == null || mykey2.equals(""))) {
				JOptionPane.showMessageDialog(null, "密钥不能为空!");    
				key1.requestFocus();
			} else if (mykey1 != null && (mykey2 == null || mykey2.equals(""))) {
				JOptionPane.showMessageDialog(null, "测试1!");    
				decrypt(message,mykey1);

					
			} else if((mykey1 == null || mykey1.equals("")) && mykey2 != null) {
				JOptionPane.showMessageDialog(null, "测试2!");
					
			} else {
				JOptionPane.showMessageDialog(null, "密钥只能同时输入一个!");
				key1.requestFocus();
			}			
		}
	}

	public String encrypt(String str, String key) {
		StringBuilder result = new StringBuilder();
		int k = Integer.parseInt(key);
		for (char c : str.toCharArray()) {					
			if (c >= 'a' && c <= 'z') {		// 如果字符串中的某个字符是小写字母
				c += k % 26;			// 移动key%26位
					if (c < 'a')
						c += 26;											// 向左超界
					if (c > 'z')
						c -= 26;											// 向右超界
			}																				
			else if (c >= 'A' && c <= 'Z') {	// 如果字符串中的某个字符是大写字母
				c += k % 26;			// 移动key%26位
				if (c < 'A')
					c += 26;												// 向左超界
				if (c > 'Z')
					c -= 26;												// 向右超界
			}
			result.append(c);
		}
		output.setText(result.toString());
		return result.toString();	
	}

	public String decrypt(String str, String key) {
		StringBuilder result = new StringBuilder();
		int k = Integer.parseInt(key);
		k = 0 - k;
		for (char c : str.toCharArray()) {					
			if (c >= 'a' && c <= 'z') {		// 如果字符串中的某个字符是小写字母
				c += k % 26;			// 移动key%26位
					if (c < 'a') {		// 向左超界
						c += 26;
					}
					if (c > 'z') {		// 向右超界
						c -= 26;
					}
			}																		
			else if (c >= 'A' && c <= 'Z') {	// 如果字符串中的某个字符是大写字母
				c += k % 26;			// 移动key%26位
				if (c < 'A') {			// 向左超界
					c += 26;
				}
				if (c > 'Z') {			// 向右超界
					c -= 26;
				}
			}
			result.append(c);
		}
		output.setText(result.toString());
		return result.toString();
	}
	
	public void southPanel() {
		JPanel south = new JPanel();			//创建南边的Panel
		jiami = new JButton("加 密");
		jiemi = new JButton("解 密");
		clear = new JButton("清 屏");
		south.add(jiami);
		south.add(jiemi);
		south.add(clear);
		this.add(south,BorderLayout.SOUTH);		//将Panel放在Frame的南边
	}
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

		JPanel panelnorth = new JPanel();		// 画板采用边界布局管理器
        panelnorth.setLayout(new BorderLayout());
        panelnorth.add("North", north1);
        panelnorth.add("Center", north2);
		this.add(panelnorth,BorderLayout.NORTH);
		
	}
	public void centerPanel() {
		JPanel center = new JPanel();
		JLabel kaisa = new JLabel ("凯撒密码密钥:");
		key1 = new JTextField(3);
		JLabel zhalan = new JLabel ("栅栏密码密钥:");
		key2 = new JTextField(3);
		center.add(kaisa);
		center.add(key1);
		center.add(zhalan);
		center.add(key2);
		kaisa.setFont(new Font("1",Font.PLAIN,13));
		key1.setFont(new Font("1",Font.PLAIN,13));
		zhalan.setFont(new Font("1",Font.PLAIN,13));
		key2.setFont(new Font("1",Font.PLAIN,13));
		this.add(center,BorderLayout.CENTER);
	}
	
	public void init() {
		this.setLocation(350,150);
		this.setSize(600,400);
		this.setVisible(true);
	}


	public static void main(String[] args) {
		new ED();
	}
}
