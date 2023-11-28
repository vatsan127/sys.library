package L_management_prototype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Databaseconn
{
    static String Query="";
    static Scanner s = new Scanner(System.in);
    static Connection connect;
    static Statement st ;

    private  boolean checkcon()							//check connection
    {
        connect = null;
        Scanner s = new Scanner(System.in);
        System.out.println("\t\t\t\t\t\t Library Database  ");
        System.out.println("\t\t\t\t\t\t\tConnection  ");
        System.out.print("Username ");

        String username = s.nextLine();
        System.out.print("Password ");

        String password = s.nextLine();
        System.out.println();


        boolean connection;
        try
        {
            String url = "jdbc:mysql://localhost:3306/Library";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connection TRUE !!!");
            connection =true;

        }
        catch (Exception e)
        {
            System.out.println("Database Connnection False !!!");
            connection =false;
        }
        return connection;
    }
    public boolean getconn()
    {
        return checkcon();
    }
    public void closeconn()
    {
        try {
            connect.close();
            System.out.println("Database Connection False");
        }
        catch(Exception e)
        {
            System.out.println("Error Cannot Disconnect Database !!!");
        }
    }
}