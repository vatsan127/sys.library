package L_management_prototype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Student extends Databaseconn
{
	public void screate()
	{
		String name;
		String rollno;
		int batch;
		try {
			System.out.print("Enter the Name ");
			name = s.next();
			System.out.print("Enter the Rollno ");
			rollno = s.next();
			System.out.print("Enter the Batch ");
			batch = s.nextInt();

			//inserting into database
			Query = "INSERT into students values(?,?,?,?)";
			PreparedStatement ps = connect.prepareStatement(Query);
			ps.setString(1, rollno);
			ps.setString(2, name);
			ps.setInt(3, batch);
			ps.setInt(4, 0);
			ps.executeUpdate();
			ps.close();
			System.out.println("Insertion Complete");

		}
		catch (Exception e) {
			System.out.println("Error in  StudentDatabase  Insertion!!!");
		}
		for(int i=0;i<80;i++)
			System.out.print("-");
		System.out.println();
	}

	public void delete()
	{
		try {
			System.out.println("Enter the Reg no to be Deleted ");
			String delete = s.next();

			Query="Delete from students where reg_no = ?";
			PreparedStatement ps =connect.prepareStatement(Query);
			ps.setString(1,delete);
			ps.executeUpdate();
			System.out.println("Student Record Deleted");
			ps.close();
		}
		catch (Exception e)
		{
			System.out.println("Deletion Error !!!");
		}
		for(int i=0;i<80;i++)
			System.out.print("-");
		System.out.println();

	}
	public void Studentdatabase()												//student database
	{
		System.out.println("\t\t\t\t\tLibrary Database  ");
		System.out.println();

		try {
			st = connect.createStatement();
			ResultSet rs = st.executeQuery("select * from students");

			while (rs.next()) {
				String regno=rs.getString("Reg_No");
				String name = rs.getString("Name");
				int batch = rs.getInt("Batch");
				int bkn = rs.getInt("Book_no");
				System.out.print(regno+"   ");
				System.out.print(name);
				for(int i=0;i<20-name.length();i++)
					System.out.print(" ");
				System.out.print(batch+"   ");
				System.out.println(bkn);
			}
		} catch (Exception e) {
			System.out.println("Error in StudentDatabase Connection !!!");
		}
		for(int i=0;i<80;i++)
			System.out.print("-");
		System.out.println();
	}
}