import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;


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
				jiami();
				

			}

		});
		jiemi.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				jiemi();
				

			}

		});
		
	}
	private void jiami() {
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
					
				} else if((mykey1 == null || mykey1.equals("")) && mykey2 != null) {
					JOptionPane.showMessageDialog(null, "测试2!");
					
				} else {
					JOptionPane.showMessageDialog(null, "密钥只能同时输入一个!");
					key1.requestFocus();
				}
				
			}
			output.setText(message);				//放message测试

		}
		private void jiemi() {
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
					
				} else if((mykey1 == null || mykey1.equals("")) && mykey2 != null) {
					JOptionPane.showMessageDialog(null, "测试2!");
					
				} else {
					JOptionPane.showMessageDialog(null, "密钥只能同时输入一个!");
					key1.requestFocus();
				}
				
			}

			output.setText(message);				//放message测试

		}

	public void southPanel() {
		JPanel south = new JPanel();					//创建南边的Panel
		jiami = new JButton("加 密");
		jiemi = new JButton("解 密");
		clear = new JButton("清 屏");
		south.add(jiami);
		south.add(jiemi);
		south.add(clear);
		this.add(south,BorderLayout.SOUTH);				//将Panel放在Frame的南边
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

		JPanel panelnorth = new JPanel();				// 画板采用边界布局管理器
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
