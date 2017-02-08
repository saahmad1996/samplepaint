package hw5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField password;
	private JPasswordField rePassword;
	private JButton btnSubmit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	public SignUp() {

		initUI();

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (InputManager.isFill(userName, password, rePassword)) {
					if (InputManager.isValidable(userName)) {
						if ((!InputManager.isExist(userName.getText()))
								&& (InputManager.checkPassword(password,
										rePassword))) {

							User user = new User();
							user.setUserName(userName.getText());

							String pass = "";
							for (char c : password.getPassword()) {
								pass += c;
							}

							user.setPass(pass);
							UserManager.addUser(user);

							new Login().setVisible(true);
							;

							setVisible(false);

						} else if (InputManager.isExist(userName.getText())) {
							JOptionPane.showMessageDialog(null,
									"کاربر وجو دارد");

						} else if (!InputManager.checkPassword(password,
								rePassword)) {
							JOptionPane.showMessageDialog(null,
									"پسورد همخاونی ندارد");

						}

					} else {
						JOptionPane.showMessageDialog(null,
								"لطفا فقط از اعداد , حروف و _ استفاده کنید");

					}

				} else {
					JOptionPane.showMessageDialog(null,
							"لطفا تماتی فیلد ها را پر کنید");
				}

			}
		});

	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 219, 169);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 10, 70, 20);

		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 40, 70, 20);
		contentPane.add(lblPassword);

		JLabel lblRePassword = new JLabel("re_password");
		lblRePassword.setBounds(10, 70, 70, 20);
		contentPane.add(lblRePassword);

		userName = new JTextField();
		userName.setBounds(100, 10, 90, 20);
		contentPane.add(userName);
		userName.setColumns(10);

		password = new JPasswordField();
		password.setBounds(100, 40, 90, 20);
		contentPane.add(password);
		password.setColumns(10);

		rePassword = new JPasswordField();
		rePassword.setBounds(100, 70, 90, 20);
		contentPane.add(rePassword);

		rePassword.setColumns(10);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(60, 100, 90, 25);
		contentPane.add(btnSubmit);

	}

}
