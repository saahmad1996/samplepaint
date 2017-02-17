package hw5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField password;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login();
					login.setVisible(true);
					login.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		initUI();

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				if (InputManager.isFill(userName, password)) {
					if (InputManager.isValidable(userName)) {
						if (InputManager.isExist(userName.getText())) {
							if (InputManager.isValid(userName, password)) {

								String pass = "";

								for (char c : password.getPassword()) {
									pass += c;
								}

								user.setUserName(userName.getText());
								user.setPass(pass);
								user.setId(UserManager.getUserId(userName
										.getText()));

								SamplePaint samplePaint = new SamplePaint(user);
								samplePaint.setVisible(true);
								samplePaint.setResizable(false);
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null,
										"پسوورد اشتباه است");

							}
						} else {
							JOptionPane.showMessageDialog(null,
									"کاربر وجود ندارد");

						}
					} else {
						JOptionPane.showMessageDialog(null,
								"لطفا از اعدداد و حزوف و _ استفاده کنید");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"لطفا تمام فیلد ها را پر نمایید");
				}
			}
		});

		contentPane.add(btnLogin);
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 220, 150);
		contentPane = new JPanel();
		setTitle("Login");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 10, 70, 20);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 40, 70, 20);
		contentPane.add(lblPassword);

		userName = new JTextField();
		userName.setBounds(100, 10, 90, 20);
		contentPane.add(userName);
		userName.setColumns(10);

		password = new JPasswordField();
		password.setBounds(100, 40, 90, 20);
		contentPane.add(password);
		password.setColumns(10);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(0, 0, 90, 0);
		btnLogin.setBounds((getWidth() - btnLogin.getWidth()) / 2, 70, 90, 20);
	}
}