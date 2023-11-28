package L_management_prototype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Rent extends Databaseconn
{
    static Date d =new Date();
    static SimpleDateFormat sd =new SimpleDateFormat("dd/MM/yyyy");
    static String date = sd.format(d);
    static SimpleDateFormat DD =new SimpleDateFormat("DD");
    static int presentday =Integer.parseInt(DD.format(d));
    PreparedStatement ps;

    public void rent(String rollno)
    {
        try {

            Scanner s = new Scanner(System.in);
            Query = "Select * from students where Reg_no= ?";
            ps = connect.prepareStatement(Query);
            ps.setString(1, rollno);
            ResultSet rs= ps.executeQuery();
            if(rs.next())
            {
                System.out.println("Student found");
            }
            else {
                System.out.println("Student not Found");
                System.out.println("Create Student !!!");
                return;

            }
            String sname = rs.getString("Name");

                System.out.print("Enter the Bookno ");
                int bookno = s.nextInt();
                Query = "Select * from books where Book_no = ?";
                ps= connect.prepareStatement(Query);
                ps.setInt(1,bookno);
                rs=ps.executeQuery();
                if(rs.next())
                {
                    System.out.println("Book Found !!!");
                }
                else {
                    System.out.println("Book Not Found ");
                    for(int i=0;i<80;i++)
                        System.out.print("-");
                    System.out.println();
                    return;
                }
            String bname=rs.getString("Book_Name");

            System.out.println("Are you sure you want to continue ? (yes/No)");
            if(s.next().equalsIgnoreCase("YES"))
            {
                Query="Insert into rent values (?,?,?,?,?,?,?)";
                ps= connect.prepareStatement(Query);
                ps.setString(1,rollno);
                ps.setString(2,sname);
                ps.setInt(3,bookno);
                ps.setString(4,bname);
                ps.setString(5,date);
                ps.setString(6,"Not Returned");
                ps.setInt(7,presentday);
                ps.executeUpdate();
                ps.close();
            }

            Query="Update students set book_no = ? where Reg_no= ?";
            ps = connect.prepareStatement(Query);
            ps.setInt(1,bookno);
            ps.setString(2,rollno);
            ps.executeUpdate();
            rs.close();
            ps.close();
            System.out.println("\nRent Successfully updated");
        }
        catch (Exception e)
        {
            System.out.println("Renting Error "+e);
        }
        for(int i=0;i<80;i++)
            System.out.print("-");
        System.out.println();
    }
    public void update(String sno)
    {
        try {
            Query= "Select * from rent where Student_Reg_no = ?";
            ps= connect.prepareStatement(Query);
            ps.setString(1,sno);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                System.out.println("Student Rent Found ");
            }
            else
            {
                System.out.println("Rent record not Found !!!");
                for(int i=0;i<80;i++)
                    System.out.print("-");
                System.out.println();
                return;
            }
            System.out.print(rs.getString("Student_Name"));
            System.out.print(" ");
            System.out.print(rs.getInt("Book_no")+" ");
            System.out.print(rs.getString("Book_Name"));
            System.out.print(" ");
            System.out.print(rs.getString("Date"));
            System.out.print(" ");
            System.out.println(rs.getString("Status")+" ");

            //calculate rent
            int rentday=rs.getInt("yeardays");
            int nod = presentday-rentday;
            if(nod>15)
            {
                int temp =(nod-15)*15;
                System.out.println("Rent Amount is "+ temp);
                System.out.println("Book Returned and Rent Paid ??(yes/no) ");

            }
            else
            {
                System.out.println("No rent amount");
                System.out.println("Book Returned ??(yes/no) ");
            }




            if(s.next().equalsIgnoreCase("yes"))
            {
                Query="Update Rent Set Status = ? where Student_Reg_no = ? ";
                ps= connect.prepareStatement(Query);
                ps.setString(1,"Returned");
                ps.setString(2,sno);
                ps.executeUpdate();
                Query = "Update Students Set Book_no = ? where Reg_No = ?";
                ps= connect.prepareStatement(Query);
                ps.setInt(1,0);
                ps.setString(2,sno);
                ps.executeUpdate();
                System.out.println("Update Successful!!!");
                delete(sno);
            }
            else {
                System.out.println( "Update Failed!!!");
            }
        }
        catch(Exception e)
        {
            System.out.println("Rent update Error \n"+e);
        }
        for(int i=0;i<80;i++)
            System.out.print("-");
        System.out.println();
    }

    public void delete(String rollno)
    {

        try {
            Query="Select Status from rent where Student_Reg_no = ?";
            ps= connect.prepareStatement(Query);
            ps.setString(1,rollno);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String status = rs.getString("Status");
            if(status.equals("Returned"))
            {
                System.out.print("Record Can be Deleted !!!");
            }
            else
            {
                System.out.println("Book Not Returned Record cannot be deleted");
                for(int i=0;i<80;i++)
                    System.out.print("-");
                System.out.println();
                return;
            }
            Query = "Delete from Rent where Student_Reg_no = ?";
            ps= connect.prepareStatement(Query);
            ps.setString(1,rollno);
            ps.executeUpdate();
            ps.close();
            rs.close();
            System.out.println("Record Delete Successful");
        }
        catch(Exception e)
        {
            System.out.println("Error in Delete Rent Record\n"+e);
        }
        for(int i=0;i<80;i++)
            System.out.print("-");
        System.out.println();

    }

    public void showdatabase()
    {
        try {
            st= connect.createStatement();
            Query = """
                    Select Student_reg_no,
                           Student_name,
                           Book_No,
                           Book_Name,
                           Date,
                           Status
                    from Rent""";
            ResultSet rs = st.executeQuery(Query);

            while(rs.next())
            {
                System.out.print(rs.getString("Student_Reg_no")+"  ");
                System.out.print(rs.getString("Student_Name"));
                for(int i=0;i<10-(rs.getString("Student_Name").length());i++)
                    System.out.print(" ");
                System.out.print(rs.getInt("Book_no")+"  ");
                System.out.print(rs.getString("Book_Name"));
                for(int i=0;i<13-(rs.getString("Book_Name").length());i++)
                    System.out.print(" ");
                System.out.print(rs.getString("Date")+"  ");
                System.out.println(rs.getString("Status")+"  ");
            }
        }
        catch (Exception e)
        {
            System.out.println("Rent Database Error "+e);
        }
        for(int i=0;i<80;i++)
            System.out.print("-");
        System.out.println();
    }
}
