import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;



public class ED extends JFrame {

	private JTextArea ta;
	private JButton jiami;
	private JButton jiemi;

	public ED() {
		init();
		southPanel();
		centerPanel();
		northPanel();
		

	
	}
	public void southPanel() {
		JPanel south = new JPanel();			//创建南边的Panel
		jiami = new JButton("加 密");
		jiemi = new JButton("解 密");
		south.add(jiami);
		south.add(jiemi);
		this.add(south,BorderLayout.SOUTH);		//将Panel放在Frame的南边
	}
	public void northPanel() {
		JPanel north = new JPanel();
		JLabel ed = new JLabel ("请输入明文/密文:");
		ta = new JTextArea(11,40);
		north.add(ed);
		north.add(ta);
		ed.setFont(new Font("1",Font.PLAIN,13));
		ta.setFont(new Font("1",Font.PLAIN,13));
		this.add(north,BorderLayout.NORTH);

	}
	public void centerPanel() {
		JPanel center = new JPanel();
		JLabel kaisa = new JLabel ("凯撒密码密钥:");
		JTextField key1 = new JTextField(3);
		JLabel zhalan = new JLabel ("栅栏密码密钥:");
		JTextField key2 = new JTextField(3);
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
