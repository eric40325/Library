import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// �d�߮��y�Ƨ�
class StringComparator implements Comparator<String>
{
	@Override
	public int compare(String s1,String s2)
	{
		if(s1.compareTo(s2)==32||s1.compareTo(s2)==-32)
			return s1.compareTo(s2);
		else
			return s1.compareToIgnoreCase(s2);
	}
}

public class Library
{
	static Book[] Book = new Book[10];
	static int index = -1;// ���ޭ�
	static int num_of_book = 8;// �w�n�����y��-1(�Y���ޭȳ̤j��)
	static Scanner sc = new Scanner(System.in);
	public static class Book
	{
		int book_num;// �Ѹ�
		String book_name;// �ѦW
		String book_type;// ����
		String author;// �@��
		int page;// ����
		boolean book_borrow;// ���y�O�_�ɥX
		int reserve_people;// ���y�w���H��
		boolean student_borrow;// �ǥͬO�_�ɮ�
		boolean student_reserve;// �ǥͬO�_�w��

		Book(int a,String b,String c,String d,int e)
		{
			book_num = a;
			book_name = b;
			book_type = c;
			author = d;
			page = e;
			book_borrow = false;
			reserve_people = 0;
			student_borrow = false;
			student_reserve = false;
		}
	}

	// �n�J����
	public static void identity()
	{
		while(true)
		{
			System.out.println("=============================\n\nWho are you?\n* student\n* staff\n* exit\nAns: ");
			if(identity_choice(sc.next()))
			{
				System.out.println("=============================\n\nBye bye ~ ~ ~");
				return;
			}
		}
	}

	// �ާ@����
	public static boolean identity_choice(String choice)
	{
		switch(choice)
		{
			// �ǥ;ާ@����
			case "student":
				while(true)
				{
					boolean q = false,r = false;
					System.out.println("=============================\n\nStudent Number: 105502019\n\nBorrowed:\n");
					for(int i = 0;i<=num_of_book;i++)
					{
						if(Book[i].student_borrow)
						{
							if(!q)
							{
								System.out.printf("\t%-40s","Bookname");
								System.out.printf("%-20s","Booknumber");
							}
							System.out.printf("\n\n\t%-40s",Book[i].book_name);
							System.out.printf("%05d",Book[i].book_num);
							q = true;
						}
					}
					if(!q)
						System.out.print("\tNull!");
					System.out.println("\n\nReserve:\n");
					for(int i = 0;i<=num_of_book;i++)
					{
						if(Book[i].student_reserve)
						{
							if(!r)
							{
								System.out.printf("\t%-40s","Bookname");
								System.out.printf("%-20s","Booknumber");
							}
							System.out.printf("\n\n\t%-40s",Book[i].book_name);
							System.out.printf("%05d",Book[i].book_num);
							r = true;
						}
					}
					if(!r)
						System.out.println("\tNull!");
					System.out.println(
							"=============================\n\nYou can do:\n* borrow\n* return\n* reserve\n* reservecancel\n* search\n* exit\nAns: ");
					if(Student_choice(sc.next()))
						break;
				}
				break;
			// �Ϯ��]�H���ާ@����
			case "staff":
				while(true)
				{
					System.out.println(
							"=============================\n<<This is staff page>>\n\nYou can do:\n* bookregister\n* bookdelete\n* bookedit\n* search\n* viewstudent\n* exit\nAns: ");
					if(Staff_choice(sc.next()))
						break;
				}
				break;
			// Exit
			case "exit":
				return true;
			default:
				System.out.println("Input Invalid!");
				break;
		}
		return false;
	}

	// �ǥͥ\��
	public static boolean Student_choice(String choice)
	{
		switch(choice)
		{
			// �ɮ�
			case "borrow":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\nAns: ");
					if(Student_borrow(sc.next()))
						break;
				}
				break;
			// �ٮ�
			case "return":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\nAns: ");
					if(Student_return(sc.next()))
						break;
				}
				break;
			// �w�����y
			case "reserve":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\nAns: ");
					if(Student_reserve(sc.next()))
						break;
				}
				break;
			// �����w�����y
			case "reservecancel":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\nAns: ");
					if(Student_reserve_cancel(sc.next()))
						break;
				}
				break;
			// �d�߮ѳ�
			case "search":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to search:\n1. Bookname\n2. Booknumber\n3. Type\n4. List All Books\n* exit\nAns: ");
					if(Search(sc.next()))
						break;
				}
				break;
			// Exit
			case "exit":
				return true;
			default:
				System.out.println("Input Invalid!");
				break;
		}
		return false;
	}

	// �Ϯ��]�H���\��
	public static boolean Staff_choice(String choice)
	{
		switch(choice)
		{
			// �n���s���y�A�A �A�A
			case "bookregister":
				Staff_bookregister();
				break;
			// �R�����y
			case "bookdelete":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\n* exit\nAns: ");
					if(Staff_bookdelete(sc.next()))
						break;
				}
				break;
			// ��s���y���
			case "bookedit":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\n* exit\nAns: ");
					if(Staff_bookedit(sc.next()))
						break;
				}
				break;
			// �d�߮ѳ�
			case "search":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to search:\n1. Bookname\n2. Booknumber\n3. Type\n4. List All Books\n* exit\nAns: ");
					if(Search(sc.next()))
						break;
				}
				break;
			// �d�߾ǥ͸��
			case "viewstudent":
				Staff_viewstudent();
				break;
			// Exit
			case "exit":
				return true;
			default:
				System.out.println("Input Invalid!");
				break;
		}
		return false;
	}

	// �ɮ�
	public static boolean Student_borrow(String choice)
	{
		switch(choice)
		{
			// ��J�ѦW
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				// �Ѥ��s�b
				if(!find_bookname(sc.nextLine()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			// ��J�Ѹ�
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				// �Ѥ��s�b
				if(!find_booknum(sc.nextInt()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			default:
				System.out.println("Input Invalid!");
				return false;
		}
		// �ɮѦ��\
		if(!Book[index].book_borrow)
		{
			Book[index].book_borrow = Book[index].student_borrow = true;
			System.out.println("\nBorrow successfully!");
		}
		// �ɮѥ���
		else
		{
			System.out.println("\nThe book has been borrowed!");
			// �w������
			if(Book[index].reserve_people==10)
				System.out.println("\nThe number of reservation is full!");
			// �w�����\
			else
			{
				System.out.println("\nThe book can be reserved. Do you want?\n1. Yes\n2. No\nAns: ");
				if(sc.nextInt()==1)
				{
					Book[index].reserve_people += 1;
					Book[index].student_reserve = true;
					System.out.println("\nReserve successfully!");
				}
			}
		}
		return true;
	}

	// �ٮ�
	public static boolean Student_return(String choice)
	{
		String a;
		int b;
		switch(choice)
		{
			// ��J�ѦW
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				a = sc.nextLine();
				for(int i = 0;i<=num_of_book+1;i++)
				{
					// �S���ɮ�
					if(i==10)
					{
						System.out.println("\nYou didn't borrow the book!");
						return true;
					}
					else if(a.equals(Book[i].book_name)&&Book[i].student_borrow)
					{
						index = i;
						break;
					}
				}
				break;
			// ��J�Ѹ�
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				b = sc.nextInt();
				for(int i = 0;i<=num_of_book+1;i++)
				{
					// �S���ɮ�
					if(i==num_of_book+1)
					{
						System.out.println("\nYou didn't borrow the book!");
						return true;
					}
					else if(b==Book[i].book_num&&Book[i].student_borrow)
					{
						index = i;
						break;
					}
				}
				break;
			default:
				System.out.println("Input Invalid!");
				return false;
		}
		// �ٮѦ��\
		Book[index].student_borrow = Book[index].book_borrow = false;
		System.out.println("\nReturn successfully!");
		return true;
	}

	// �w�����y
	public static boolean Student_reserve(String choice)
	{
		switch(choice)
		{
			// ��J�ѦW
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				// �Ѥ��s�b
				if(!find_bookname(sc.nextLine()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			// ��J�Ѹ�
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				// �Ѥ��s�b
				if(!find_booknum(sc.nextInt()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			default:
				System.out.println("Input Invalid!");
				return false;
		}
		// ����w��
		if(!Book[index].book_borrow)
		{
			System.out.println(
					"\nYou can't reserve this book!\n\nThe book can be borrowed! Do you want?\n1. Yes\n2. No\nAns: ");
			// �ɮѦ��\
			if(sc.nextInt()==1)
			{
				Book[index].book_borrow = Book[index].student_borrow = true;
				System.out.println("\nBorrow successfully!");
			}
		}
		// �w������
		else if(Book[index].reserve_people==10)
			System.out.println("\nThe number of reservation is full!");
		// �w�����\
		else
		{
			Book[index].student_reserve = true;
			Book[index].reserve_people += 1;
			System.out.println("\nReserve successfully!");
		}
		return true;
	}

	// �����w�����y
	public static boolean Student_reserve_cancel(String choice)
	{
		String a;
		int b;
		switch(choice)
		{
			// ��J�ѦW
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				a = sc.nextLine();
				for(int i = 0;i<=num_of_book+1;i++)
				{
					// �S���w��
					if(i==num_of_book+1)
					{
						System.out.println("\nYou didn't reserve the book!");
						return true;
					}
					else if(a.equals(Book[i].book_name)&&Book[i].student_reserve)
					{
						index = i;
						break;
					}
				}
				break;
			// ��J�Ѹ�
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				b = sc.nextInt();
				for(int i = 0;i<=num_of_book+1;i++)
				{
					// �S���w��
					if(i==num_of_book+1)
					{
						System.out.println("\nYou didn't reserve the book!");
						return true;
					}
					else if(b==Book[i].book_num&&Book[i].student_reserve)
					{
						index = i;
						break;
					}
				}
				break;
			default:
				System.out.println("Input Invalid!");
				return false;
		}
		// �������\
		Book[index].student_reserve = false;
		Book[index].reserve_people -= 1;
		System.out.println("\nCancel successfully!");
		return true;
	}

	// �d�߮ѳ�
	public static boolean Search(String choice)
	{
		String a;
		int x = 0;
		ArrayList<String> result_a = new ArrayList<String>();
		switch(choice)
		{
			// �ήѦW�d��
			case "1":
				System.out.println("Search:");
				sc.nextLine();
				a = sc.nextLine();
				for(int i = 0;i<=num_of_book;i++)
				{
					if(Book[i].book_name.indexOf(a)!=-1)
					{
						result_a.add(x,Book[i].book_name);
						x++;
					}
				}
				System.out.println("\nResults:\n");
				// �Ѥ��s�b
				if(x==0)
				{
					System.out.println("\tThe book doesn't exist!");
					return false;
				}
				// ��X���G
				System.out.printf("%-20s","Booknumber");
				System.out.printf("%-40s","Bookname");
				System.out.printf("%-20s","Type");
				System.out.printf("%-30s","Author");
				System.out.printf("%-15s","Page");
				System.out.printf("%-15s","Borrowed");
				System.out.printf("%-20s","Reserve People");
				Collections.sort(result_a,new StringComparator());
				for(int i = 0;i<=result_a.size()-1;i++)
				{
					for(int j = 0;j<=num_of_book;j++)
					{
						if(result_a.get(i).equals(Book[j].book_name))
						{
							System.out.printf("\n\n%-5s"," ");
							System.out.printf("%05d",Book[j].book_num);
							System.out.printf("%-10s"," ");
							System.out.printf("%-40s",Book[j].book_name);
							System.out.printf("%-20s",Book[j].book_type);
							System.out.printf("%-30s",Book[j].author);
							System.out.printf("%-15d",Book[j].page);
							if(Book[j].book_borrow)
							{
								System.out.printf("%-15s","Yes");
								System.out.printf("%-20d",Book[j].reserve_people);
							}
							else
							{
								System.out.printf("%-15s","No");
								System.out.printf("%-20d",Book[j].reserve_people);
							}
						}
					}
				}
				System.out.print("\n");
				break;
			// �ήѸ��d��
			case "2":
				System.out.println("Search:");
				sc.nextLine();
				a = sc.nextLine();
				for(int i = 0;i<=num_of_book;i++)
				{
					if(String.valueOf(Book[i].book_num).indexOf(a)!=-1)
					{
						result_a.add(x,String.valueOf(Book[i].book_num));
						x++;
					}
				}
				System.out.println("\nResults:\n");
				// �Ѥ��s�b
				if(x==0)
				{
					System.out.println("\tThe book doesn't exist!");
					return false;
				}
				// ��X���G
				System.out.printf("%-20s","Booknumber");
				System.out.printf("%-40s","Bookname");
				System.out.printf("%-20s","Type");
				System.out.printf("%-30s","Author");
				System.out.printf("%-15s","Page");
				System.out.printf("%-15s","Borrowed");
				System.out.printf("%-20s","Reserve People");
				Collections.sort(result_a,new StringComparator());
				for(int i = 0;i<=result_a.size()-1;i++)
				{
					for(int j = 0;j<=num_of_book;j++)
					{
						if(Integer.valueOf(result_a.get(i))==Book[j].book_num)
						{
							System.out.printf("\n\n%-5s"," ");
							System.out.printf("%05d",Book[j].book_num);
							System.out.printf("%-10s"," ");
							System.out.printf("%-40s",Book[j].book_name);
							System.out.printf("%-20s",Book[j].book_type);
							System.out.printf("%-30s",Book[j].author);
							System.out.printf("%-15d",Book[j].page);
							if(Book[j].book_borrow)
							{
								System.out.printf("%-15s","Yes");
								System.out.printf("%-20d",Book[j].reserve_people);
							}
							else
							{
								System.out.printf("%-15s","No");
								System.out.printf("%-20d",Book[j].reserve_people);
							}
						}
					}
				}
				System.out.print("\n");
				break;
			// �κ����d��
			case "3":
				System.out.println("Search:");
				sc.nextLine();
				a = sc.nextLine();
				for(int i = 0;i<=num_of_book;i++)
				{
					if(Book[i].book_type.indexOf(a)!=-1)
					{
						result_a.add(x,Book[i].book_name);
						x++;
					}
				}
				System.out.println("\nResults:\n");
				// �Ѥ��s�b
				if(x==0)
				{
					System.out.println("\tThe book doesn't exist!");
					return false;
				}
				// ��X���G
				System.out.printf("%-20s","Booknumber");
				System.out.printf("%-40s","Bookname");
				System.out.printf("%-20s","Type");
				System.out.printf("%-30s","Author");
				System.out.printf("%-15s","Page");
				System.out.printf("%-15s","Borrowed");
				System.out.printf("%-20s","Reserve People");
				Collections.sort(result_a,new StringComparator());
				for(int i = 0;i<=result_a.size()-1;i++)
				{
					for(int j = 0;j<=num_of_book;j++)
					{
						if(result_a.get(i).equals(Book[j].book_name))
						{
							System.out.printf("\n\n%-5s"," ");
							System.out.printf("%05d",Book[j].book_num);
							System.out.printf("%-10s"," ");
							System.out.printf("%-40s",Book[j].book_name);
							System.out.printf("%-20s",Book[j].book_type);
							System.out.printf("%-30s",Book[j].author);
							System.out.printf("%-15d",Book[j].page);
							if(Book[j].book_borrow)
							{
								System.out.printf("%-15s","Yes");
								System.out.printf("%-20d",Book[j].reserve_people);
							}
							else
							{
								System.out.printf("%-15s","No");
								System.out.printf("%-20d",Book[j].reserve_people);
							}
						}
					}
				}
				System.out.print("\n");
				break;
			// ��X�������y
			case "4":
				System.out.println("\nResults:\n");
				if(num_of_book==-1)
				{
					System.out.println("\tNull!");
					return false;
				}
				for(int i = 0;i<=num_of_book;i++)
				{
					result_a.add(x,Book[i].book_name);
					x++;
				}
				System.out.printf("%-20s","Booknumber");
				System.out.printf("%-40s","Bookname");
				System.out.printf("%-20s","Type");
				System.out.printf("%-30s","Author");
				System.out.printf("%-15s","Page");
				System.out.printf("%-15s","Borrowed");
				System.out.printf("%-20s","Reserve People");
				Collections.sort(result_a,new StringComparator());
				for(int i = 0;i<=result_a.size()-1;i++)
				{
					for(int j = 0;j<=num_of_book;j++)
					{
						if(result_a.get(i).equals(Book[j].book_name))
						{
							System.out.printf("\n\n%-5s"," ");
							System.out.printf("%05d",Book[j].book_num);
							System.out.printf("%-10s"," ");
							System.out.printf("%-40s",Book[j].book_name);
							System.out.printf("%-20s",Book[j].book_type);
							System.out.printf("%-30s",Book[j].author);
							System.out.printf("%-15d",Book[j].page);
							if(Book[j].book_borrow)
							{
								System.out.printf("%-15s","Yes");
								System.out.printf("%-20d",Book[j].reserve_people);
							}
							else
							{
								System.out.printf("%-15s","No");
								System.out.printf("%-20d",Book[j].reserve_people);
							}
						}
					}
				}
				System.out.print("\n");
				break;
			case "exit":
				return true;
			default:
				break;
		}
		return false;
	}

	// �n���s���y
	public static void Staff_bookregister()
	{
		while(true)
		{
			String a,b,c,d;
			int e;
			System.out.println("\nEnter these informations in order (or enter \"exit\" to exit)--\nBooknumber:");
			a = sc.next();
			// Exit
			if(a.equals("exit"))
				break;
			// �Ѥw�s�b
			if(find_booknum(Integer.valueOf(a)))
				System.out.println("\nThe book has already existed!");
			else
			{
				System.out.println("Bookname:");
				sc.nextLine();
				b = sc.nextLine();
				// �Ѥw�s�b
				if(find_bookname(b))
					System.out.println("\nThe book has already existed!");
				// �n�����\
				else
				{
					System.out.println("Type:");
					c = sc.nextLine();
					System.out.println("Author:");
					d = sc.nextLine();
					System.out.println("Page:");
					e = sc.nextInt();
					Book[num_of_book+1] = new Book(Integer.valueOf(a),b,c,d,e);
					num_of_book += 1;
					System.out.println("\nRegister successfully!");
				}
			}
		}
		return;
	}

	// �R�����y
	public static boolean Staff_bookdelete(String choice)
	{
		switch(choice)
		{
			// ��J�ѦW
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				// �Ѥ��s�b
				if(!find_bookname(sc.nextLine()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			// ��J�Ѹ�
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				// �Ѥ��s�b
				if(!find_booknum(sc.nextInt()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			default:
				System.out.println("Input Invalid!");
				return false;
		}
		// �R�����\
		for(int i = index;i<=num_of_book;i++)
			Book[i] = Book[i+1];
		num_of_book -= 1;
		System.out.println("\nDelete successfully!");
		return true;
	}

	// ��s���y���
	public static boolean Staff_bookedit(String choice)
	{
		switch(choice)
		{
			// ��J�ѦW
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				// �Ѥ��s�b
				if(!find_bookname(sc.nextLine()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			// ��J�Ѹ�
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				// �Ѥ��s�b
				if(!find_booknum(sc.nextInt()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			default:
				System.out.println("Input Invalid!");
				return false;
		}
		// �}�l�ק���
		System.out.println("\nInformations of the book:\n");
		System.out.printf("%-20s","Booknumber");
		System.out.printf("%-40s","Bookname");
		System.out.printf("%-20s","Type");
		System.out.printf("%-30s","Author");
		System.out.printf("%-15s","Page");
		System.out.printf("\n\n%-5s"," ");
		System.out.printf("%05d",Book[index].book_num);
		System.out.printf("%-10s"," ");
		System.out.printf("%-40s",Book[index].book_name);
		System.out.printf("%-20s",Book[index].book_type);
		System.out.printf("%-30s",Book[index].author);
		System.out.printf("%-15d",Book[index].page);
		System.out.println("\n\nEdit these informations in order --\nBooknumber:");
		Book[index].book_num = sc.nextInt();
		System.out.println("Bookname:");
		sc.nextLine();
		Book[index].book_name = sc.nextLine();
		System.out.println("Type:");
		Book[index].book_type = sc.nextLine();
		System.out.println("Author:");
		Book[index].author = sc.nextLine();
		System.out.println("Page:");
		Book[index].page = sc.nextInt();
		System.out.println("\nEdit successfully!");
		return true;
	}

	// �d�߾ǥ͸��
	public static void Staff_viewstudent()
	{
		boolean q = false,r = false;
		System.out.println("=============================\n\nStudent Number: 105502019\n\nBorrowed:\n");
		for(int i = 0;i<=num_of_book;i++)
		{
			if(Book[i].student_borrow)
			{
				if(!q)
				{
					System.out.printf("\t%-40s","Bookname");
					System.out.printf("%-20s","Booknumber");
				}
				System.out.printf("\n\n\t%-40s",Book[i].book_name);
				System.out.printf("%05d",Book[i].book_num);
				q = true;
			}
		}
		if(!q)
			System.out.print("\tNull!");
		System.out.println("\n\nReserve:\n");
		for(int i = 0;i<=num_of_book;i++)
		{
			if(Book[i].student_reserve)
			{
				if(!r)
				{
					System.out.printf("\t%-40s","Bookname");
					System.out.printf("%-20s","Booknumber");
				}
				System.out.printf("\n\n\t%-40s",Book[i].book_name);
				System.out.printf("%05d",Book[i].book_num);
				r = true;
			}
		}
		if(!r)
			System.out.println("\tNull!");
		return;
	}

	// �ήѦW���
	public static boolean find_bookname(String input)
	{
		for(int i = 0;i<=num_of_book+1;i++)
		{
			// �S����
			if(i==num_of_book+1)
				break;
			// ����
			else if(input.equals(Book[i].book_name))
			{
				index = i;
				return true;
			}
		}
		return false;
	}

	// �ήѸ����
	public static boolean find_booknum(int input)
	{
		for(int i = 0;i<=num_of_book+1;i++)
		{
			// �S����
			if(i==num_of_book+1)
				break;
			// ����
			else if(input==Book[i].book_num)
			{
				index = i;
				return true;
			}
		}
		return false;
	}

	// �D�{��
	public static void main(String[] args)
	{
		// �w�]�ѳ�
		Book[0] = new Book(82101,"Cihai","Reference","Shu Xincheng",20000);
		Book[1] = new Book(80001,"WW2 History","History","Winston Churchill",971);
		Book[2] = new Book(00003,"Egg 100","Cookbook","Su yuan ma",104);
		Book[3] = new Book(50001,"Be a honest man","Political","Ma Ying jeou",520);
		Book[4] = new Book(85719,"Sword Art Online","Novel","Reki Kawahara",8763);
		Book[5] = new Book(85728,"Spice and Wolf","Novel","Isuna Hasekura",510);
		Book[6] = new Book(85707,"The Old Man and the Sea","Novel","Ernest Hemingway",127);
		Book[7] = new Book(85703,"Romance of the Three Kingdoms","Novel","Luo Guanzhong",480);
		Book[8] = new Book(80005,"Records of the Grand Historian","History","Sima Qian",6000);
		System.out.println("\n<<Library>>");
		// �i�J�n�J����
		identity();
	}
}
