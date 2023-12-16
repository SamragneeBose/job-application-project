package jdbc;
import java.sql.*;

public class MyFrameConnect{
	private String name, gender, address, qualification, profile;
	Connection conn;
//	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MyFrameConnect(String name, String gender, String address, String qualification, String profile) {
		this.name=name;
		this.gender=gender;
		this.address=address;
		this.qualification=qualification;
		this.profile=profile;
		
		conn=null;
		pstmt=null;
		rs=null;
	}
	
	public void insert() throws SQLException, ClassNotFoundException
	{
		try
		{
			// Loading driver(?)
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Establishing connection
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "1234");
			
//			stmt=conn.createStatement();
//			String s="\'"+name+"\', "+"\'"+gender+"\', "+"\'"+address+"\', "+"\'"+qualification+"\', "+"\'"+profile;
//			String s=name+", "+gender+", "+address+", "+qualification+", "+profile;
//			stmt.executeUpdate("INSERT INTO candidate(name, gender, address, qualification, profile) VALUE("+s+")");
			
			pstmt=conn.prepareStatement("INSERT INTO candidate(name, gender, address, qualification, profile) VALUE(?, ?, ?, ?, ?)");
			pstmt.setString(1, name);
			pstmt.setString(2, gender);
			pstmt.setString(3, address);
			pstmt.setString(4, qualification);
			pstmt.setString(5, profile);
			pstmt.executeUpdate();
			
			System.out.println("Successfully Inserted.");
			
		}
		catch(SQLException e)
		{
			System.out.println("SQLException: "+e.getMessage());
			System.out.println("SQLState: "+e.getSQLState());
			System.out.println("VendorError: "+e.getErrorCode());
		}
		finally
		{
			if(rs!=null && pstmt!=null)
			{
				rs.close();
				pstmt.close();
				rs=null;
				pstmt=null;
			}
			conn.close();
		}

	}

}
