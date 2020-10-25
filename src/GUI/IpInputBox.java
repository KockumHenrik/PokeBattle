package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IpInputBox {
	public String ip;
	
	public String getIp() {
		return ip;
	}
	
	public void popUpBox() {
		JFrame parent = new JFrame("Enter IP-adress");
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		parent.setPreferredSize(new Dimension(400,100));
		parent.setLayout(new BorderLayout());
		JButton button = new JButton();		
		JTextField textField = new JTextField();
		
		parent.setLocationRelativeTo(null);
		textField.setPreferredSize(new Dimension(300,20));
		panel.add(textField, BorderLayout.PAGE_START);
		panel.add(button, BorderLayout.PAGE_END);
		
		button.setText("ENTER IP");
        parent.add(panel);
        parent.pack();
        parent.setVisible(true);

        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ip = textField.getText();
                parent.dispose();
            }
        });
	}
}
