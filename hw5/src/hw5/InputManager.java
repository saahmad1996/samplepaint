package hw5;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InputManager {
	static boolean checkPassword(JPasswordField password,
			JPasswordField rePassword) {
		String pass = "";
		String rePass = "";

		for (char c : password.getPassword()) {
			pass += c;
		}
		for (char c : rePassword.getPassword()) {
			rePass += c;
		}

		return pass.equals(rePass);
	}

	public static boolean isExist(String userName) {
		return UserManager.isExist(userName);
	}

	public static boolean isValid(JTextField userName, JPasswordField password) {
		String pass = "";
		for (char c : password.getPassword()) {
			pass += c;
		}
		return UserManager.isValid(userName.getText(), pass);
	}

	public static boolean isFill(JTextField userName, JPasswordField password,
			JPasswordField rePassword) {
		return userName.getText().length() > 0
				&& password.getPassword().length > 0
				&& rePassword.getPassword().length > 0;
	}

	public static boolean isFill(JTextField userName, JPasswordField password) {
		return isFill(userName, password, password);
	}

	public static boolean isValidable(JTextField userName) {
		boolean result = true;

		for (char c : userName.getText().toCharArray()) {
			if (!(((c == '_') || c <= 'z' && c >= 'a')
					|| (c <= 'Z' && c >= 'A') || (c <= '9' && c >= '0'))) {
				result = false;
			}
		}
		return result;
	}
}
