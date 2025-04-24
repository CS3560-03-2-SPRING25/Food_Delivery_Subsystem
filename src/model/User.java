package model;

public class User {
	 private int userId;
	 private String name;
	 private String phoneNumber;
	 private String email;
	 private String password;
	 protected String role;

	public User(int userId, String name, String phoneNumber, String email, String password, String role) {
	    this.userId = userId;
	    this.name = name;
	    this.phoneNumber = phoneNumber;
	    this.email = email;
	    this.password = password;
	    this.role = role;
	}
	
	// Static Factory Method
	private User(String name, String email, String phoneNumber, String password, String role) {
        this(0, name, email, phoneNumber, password, role); // 0 or a placeholder for new users
    }
	
	// Static Factory Method
    public static User newUserForSignup(String name, String email, String phoneNumber, String password, String role) {
        return new User(name, email, phoneNumber, password, role);
    }
    
	// Static Factory Method
    public static User existingUserFromDB(int userId, String name, String email, String phoneNumber, String password, String role) {
        return new User(userId, name, email, phoneNumber, password, role);
    }
	 
	 public int getUserId() {
		 return userId;
	 }
	 
	 public void setUserId(int userId) {
	     this.userId = userId;
	 }
	 
	 public String getName() {
	     return name; 
	 }
	 
	 public void setName(String name) {
	     this.name = name;
	 }
	
	 public String getPhoneNumber() {
	     return phoneNumber; 
	 }
	 
	 public void setPhoneNumber(String phoneNumber) {
	     this.phoneNumber = phoneNumber;
	 }
	
	 public String getEmail() {
	     return email; 
	 }
	 
	 public String getRole() {
	     return role; 
	 }
	 
	 public void setEmail(String email) {
	     this.email = email;
	 }
	 
	 public String getPassword() {
	     return password;
	 }
	
	 public void setPassword(String password) {
	     this.password = password;
	 }
	 
	 public void setRole(String role) {
	     this.role = role;
	 }
}