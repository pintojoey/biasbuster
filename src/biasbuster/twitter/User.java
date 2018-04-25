package biasbuster.twitter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
 private long id;
 private String handle;
 private String gender;
public User(ResultSet rs) throws SQLException {
	if(rs.findColumn("id")!=0)this.id=rs.getLong("id");
	this.handle=rs.getString("handle");
	this.gender=rs.getString("gender");
}
public User() {
	// TODO Auto-generated constructor stub
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getHandle() {
	return handle;
}
public void setHandle(String handle) {
	this.handle = handle;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
 
}
