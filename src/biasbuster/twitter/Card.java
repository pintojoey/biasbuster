package biasbuster.twitter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Card {
private long id;
private String preference,handle;
private String answer;
public Card(ResultSet rs) throws SQLException {
	if(rs.findColumn("id")>0)this.id=rs.getLong("id");
	this.preference=rs.getString("preference");
	this.handle=rs.getString("handle");
	this.answer=rs.getString("answer");
}
public Card() {
	// TODO Auto-generated constructor stub
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getPreference() {
	return preference;
}
public void setPreference(String preference) {
	this.preference = preference;
}
public String getHandle() {
	return handle;
}
public void setHandle(String handle) {
	this.handle = handle;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}


}
