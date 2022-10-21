package com.Automation;

public class DataContext {

	 public static String getEmailAddress() {
		return emailAddress;
	}
	public static void setEmailAddress(String emailAddress) {
		DataContext.emailAddress = emailAddress;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		DataContext.password = password;
	}
	public static String getMessageToBeSaved() {
		return messageToBeSaved;
	}
	public static void setMessageToBeSaved(String messageToBeSaved) {
		DataContext.messageToBeSaved = messageToBeSaved;
	}
	private static String emailAddress = "monikaassudani91@gmail.com";
	  private static String password = "Lovemyself@148";
	  private static String messageToBeSaved = "Message to be Saved" + Math.random();
}
