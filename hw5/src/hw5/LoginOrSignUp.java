package hw5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class LoginOrSignUp extends JFrame {
	private JButton btnLogin ,btnSignUp ;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginOrSignUp frame = new LoginOrSignUp();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public LoginOrSignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 113);
		contentPane = new JPanel();
		setTitle("Login or SignUP");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(58, 10, 89, 23);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				login.setResizable(false);
				setVisible(false);
			}
		});

		contentPane.add(btnLogin);

		btnSignUp = new JButton("SignUp");
		btnSignUp.setBounds(58, 40, 89, 23);
		contentPane.add(btnSignUp);

		btnSignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SignUp  signUp = new SignUp();
				signUp.setVisible(true);
				signUp.setResizable(false);
				setVisible(false);
			}
		});

		setVisible(true);

	}
}
