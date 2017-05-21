package GladeProject;

public class DataBaseConnect {
	static String url = "jdbc:mysql://localhost/PhoneList?useSSL=false&characterEncoding=cp1251";
	static String user = "root";
	static String password = "8s5b19a";
	static String[] count = new String[6];
	static String selectTableSQL = "SELECT * from tablePhoneList";
}
