package L_management_prototype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Books extends Databaseconn
{
    public void bookcreate()
    {
        int bookno;
        String bookname;
        String author;
        String publisher;

        try {
            System.out.print("Enter the Book Name ");
            bookname = s.next();
            System.out.print("Book no ");
            bookno = s.nextInt();
            System.out.print("Author ");
            author = s.next();
            System.out.print("Publisher ");
            publisher = s.next();

            Query = "Insert into books values (?,?,?,?)";
            PreparedStatement ps = connect.prepareStatement(Query);
            ps.setInt(1, bookno);
            ps.setString(2, bookname);
            ps.setString(3,author);
            ps.setString(4,publisher);
            ps.executeUpdate();
            ps.close();
            System.out.println("Insertion Complete" );
        }
        catch (Exception e) {
            System.out.println("Book Insertion Error");
        }

        for(int i=0;i<80;i++)
            System.out.print("-");
        System.out.println();
    }
    public void delete()
    {
        int delete;
        System.out.print("Enter the Bookno ");
        delete= s.nextInt();

        Query="Delete from books where Book_No =?";

        try {
            PreparedStatement ps = connect.prepareStatement(Query);
            ps.setInt(1,delete);
            ps.executeUpdate();
            ps.close();
            System.out.println("Delete Complete");
        }
        catch(Exception e)
        {
            System.out.println("Book Deletion Error");
        }

        for(int i=0;i<80;i++)
            System.out.print("-");
        System.out.println();
    }

    public void bookdatabase()
    {
        try {
            st= connect.createStatement();
            Query = "Select * from books";
            ResultSet rs = st.executeQuery(Query);

            while(rs.next())
            {
                System.out.print(rs.getInt("Book_No")+"\t");
                String book_name=rs.getString("Book_Name");
                System.out.print(book_name);
                for(int i=0;i<15-book_name.length();i++)
                    System.out.print(" ");
                String author= rs.getString("Author");
                System.out.print(author);
                for (int i=0;i<15-author.length();i++)
                    System.out.print(" ");
                String publisher= rs.getString("Publisher");
                System.out.print(publisher);
                System.out.println();
            }
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Book Database Connection Error !!!");
        }

        for(int i=0;i<80;i++)
            System.out.print("-");
        System.out.println();
    }
}