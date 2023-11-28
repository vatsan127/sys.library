package L_management_prototype;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin
{
	static Scanner s=new Scanner(System.in);
	static Student student =new Student();

	private static int option;

	public static void main(String[] args) throws Exception {
		System.out.println("\t\t\t\t\tWelcome to Library Management");
		System.out.println("\t\t\t\t\t\t\t Made by");
		System.out.println("\t\t\t\t\t\t\tSrivatsan");
		if(student.getconn()) {
			Menu();
		}
	}

	static void Menu() throws Exception {
		boolean loop=true;
		while(loop)
		{
			System.out.println("\t\t\t\t\t\t\tMain Menu");
			System.out.println("1.Student Menu");
			System.out.println("2.Book MENU");
			System.out.println("3.Rent Books");
			System.out.println("4.Rent Instruction");
			System.out.println("5.Exit");

			option =s.nextInt();

			for(int i=0;i<80;i++)
				System.out.print("-");
			System.out.println();

			switch(option)
			{
				case 1:
					studentmenu();
					break;
				case 2:
					bookmenu();
					break;
				case 3:
					rentmenu();
					break;
				case 4:
					System.out.println("\t\t\t\t\t\tRent Instruction");
					System.out.println("""
							All  books in the Library are available for Rent
							The Books are free for the First 15 Days
							Each Person can take only one book Library
							After 15 Days for each day the Rent is Rs.15\s""");

					System.out.println("\nEnter a Key");
					s.next();
					System.out.println("Returning To Main Menu");
					break;

				case 5:
					loop=false;
					student.closeconn();
					System.out.println("Exiting !!!");
					break;
			}
			for(int i=0;i<80;i++)
				System.out.print("-");
			System.out.println();
		}
	}
	static  void rentmenu()  {
		boolean loop =true;
		while(loop) {
			Rent r = new Rent();
			System.out.println("1.Rent Books");
			System.out.println("2.Update Rent ");
			System.out.println("3.Delete Record ");
			System.out.println("4.Rent Database");
			System.out.println("5.Return");
			try
			{
			option = s.nextInt();
				for(int i=0;i<80;i++)
					System.out.print("-");
				System.out.println();

			String rollno;

				switch (option) {
					case 1:
						System.out.print("Enter Rollno ");
						rollno = s.next();
						r.rent(rollno);
						break;
					case 2:
						System.out.print("Enter Rollno ");
						rollno = s.next();
						r.update(rollno);
						break;
					case 3:
						System.out.print("Enter Rollno ");
						rollno = s.next();
						r.delete(rollno);
						break;
					case 4:
						r.showdatabase();
						break;
					case 5:
						loop = false;
						break;
				}
			}
			catch(InputMismatchException IME)
			{
				System.out.println("Enter correct INput");
			}
		}
	}
	static void studentmenu() throws Exception
	{
		boolean loop =true;
		while(loop)
		{
			System.out.println("\t\t\t\t\t\t\tStudent Menu ");
			System.out.println("1.Create Student ");
			System.out.println("2.Delete Record");
			System.out.println("3.Student Database ");
			System.out.println("4.Return to Main Menu");

			option =s.nextInt();
			for(int i=0;i<80;i++)
				System.out.print("-");
			System.out.println();

			switch(option)
			{
				case 1:
					student.screate();
					break;
				case 2:
					student.delete();
					break;
				case 3:
					student.Studentdatabase();
					break;
				case 4:
					loop=false;
					System.out.println("Returning to Main Menu");
					break;
			}
		}
	}
	static Books book=new Books();
	static void bookmenu()  {
		boolean loop=true;
		while(loop)
		{
			System.out.println("\t\t\t\t\t\t\t   Book Menu ");
			System.out.println("1.Create Book ");
			System.out.println("2.Delete Record");
			System.out.println("3.Book Database ");
			System.out.println("4.Return to Main Menu");

			option =s.nextInt();

			for(int i=0;i<80;i++)
				System.out.print("-");
			System.out.println();

			switch(option)
			{
				case 1:
					book.bookcreate();
					break;
				case 2:
					book.delete();
					break;
				case 3:
					book.bookdatabase();
					break;
				case 4:
					loop=false;
					System.out.println("Returning to Main Menu");
					break;
			}
		}
	}
}