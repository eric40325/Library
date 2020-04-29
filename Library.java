import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// 查詢書籍排序
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
	static int index = -1;// 索引值
	static int num_of_book = 8;// 已登錄書籍數-1(即索引值最大值)
	static Scanner sc = new Scanner(System.in);
	public static class Book
	{
		int book_num;// 書號
		String book_name;// 書名
		String book_type;// 種類
		String author;// 作者
		int page;// 頁數
		boolean book_borrow;// 書籍是否借出
		int reserve_people;// 書籍預約人數
		boolean student_borrow;// 學生是否借書
		boolean student_reserve;// 學生是否預約

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

	// 登入介面
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

	// 操作介面
	public static boolean identity_choice(String choice)
	{
		switch(choice)
		{
			// 學生操作介面
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
			// 圖書館人員操作介面
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

	// 學生功能
	public static boolean Student_choice(String choice)
	{
		switch(choice)
		{
			// 借書
			case "borrow":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\nAns: ");
					if(Student_borrow(sc.next()))
						break;
				}
				break;
			// 還書
			case "return":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\nAns: ");
					if(Student_return(sc.next()))
						break;
				}
				break;
			// 預約書籍
			case "reserve":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\nAns: ");
					if(Student_reserve(sc.next()))
						break;
				}
				break;
			// 取消預約書籍
			case "reservecancel":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\nAns: ");
					if(Student_reserve_cancel(sc.next()))
						break;
				}
				break;
			// 查詢書單
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

	// 圖書館人員功能
	public static boolean Staff_choice(String choice)
	{
		switch(choice)
		{
			// 登錄新書籍，， ，，
			case "bookregister":
				Staff_bookregister();
				break;
			// 刪除書籍
			case "bookdelete":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\n* exit\nAns: ");
					if(Staff_bookdelete(sc.next()))
						break;
				}
				break;
			// 更新書籍資料
			case "bookedit":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to enter:\n* bookname\n* booknum\n* exit\nAns: ");
					if(Staff_bookedit(sc.next()))
						break;
				}
				break;
			// 查詢書單
			case "search":
				while(true)
				{
					System.out.println(
							"=============================\n\nChoose one type to search:\n1. Bookname\n2. Booknumber\n3. Type\n4. List All Books\n* exit\nAns: ");
					if(Search(sc.next()))
						break;
				}
				break;
			// 查詢學生資料
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

	// 借書
	public static boolean Student_borrow(String choice)
	{
		switch(choice)
		{
			// 輸入書名
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				// 書不存在
				if(!find_bookname(sc.nextLine()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			// 輸入書號
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				// 書不存在
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
		// 借書成功
		if(!Book[index].book_borrow)
		{
			Book[index].book_borrow = Book[index].student_borrow = true;
			System.out.println("\nBorrow successfully!");
		}
		// 借書失敗
		else
		{
			System.out.println("\nThe book has been borrowed!");
			// 預約失敗
			if(Book[index].reserve_people==10)
				System.out.println("\nThe number of reservation is full!");
			// 預約成功
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

	// 還書
	public static boolean Student_return(String choice)
	{
		String a;
		int b;
		switch(choice)
		{
			// 輸入書名
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				a = sc.nextLine();
				for(int i = 0;i<=num_of_book+1;i++)
				{
					// 沒有借書
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
			// 輸入書號
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				b = sc.nextInt();
				for(int i = 0;i<=num_of_book+1;i++)
				{
					// 沒有借書
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
		// 還書成功
		Book[index].student_borrow = Book[index].book_borrow = false;
		System.out.println("\nReturn successfully!");
		return true;
	}

	// 預約書籍
	public static boolean Student_reserve(String choice)
	{
		switch(choice)
		{
			// 輸入書名
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				// 書不存在
				if(!find_bookname(sc.nextLine()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			// 輸入書號
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				// 書不存在
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
		// 不能預約
		if(!Book[index].book_borrow)
		{
			System.out.println(
					"\nYou can't reserve this book!\n\nThe book can be borrowed! Do you want?\n1. Yes\n2. No\nAns: ");
			// 借書成功
			if(sc.nextInt()==1)
			{
				Book[index].book_borrow = Book[index].student_borrow = true;
				System.out.println("\nBorrow successfully!");
			}
		}
		// 預約失敗
		else if(Book[index].reserve_people==10)
			System.out.println("\nThe number of reservation is full!");
		// 預約成功
		else
		{
			Book[index].student_reserve = true;
			Book[index].reserve_people += 1;
			System.out.println("\nReserve successfully!");
		}
		return true;
	}

	// 取消預約書籍
	public static boolean Student_reserve_cancel(String choice)
	{
		String a;
		int b;
		switch(choice)
		{
			// 輸入書名
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				a = sc.nextLine();
				for(int i = 0;i<=num_of_book+1;i++)
				{
					// 沒有預約
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
			// 輸入書號
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				b = sc.nextInt();
				for(int i = 0;i<=num_of_book+1;i++)
				{
					// 沒有預約
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
		// 取消成功
		Book[index].student_reserve = false;
		Book[index].reserve_people -= 1;
		System.out.println("\nCancel successfully!");
		return true;
	}

	// 查詢書單
	public static boolean Search(String choice)
	{
		String a;
		int x = 0;
		ArrayList<String> result_a = new ArrayList<String>();
		switch(choice)
		{
			// 用書名查詢
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
				// 書不存在
				if(x==0)
				{
					System.out.println("\tThe book doesn't exist!");
					return false;
				}
				// 輸出結果
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
			// 用書號查詢
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
				// 書不存在
				if(x==0)
				{
					System.out.println("\tThe book doesn't exist!");
					return false;
				}
				// 輸出結果
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
			// 用種類查詢
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
				// 書不存在
				if(x==0)
				{
					System.out.println("\tThe book doesn't exist!");
					return false;
				}
				// 輸出結果
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
			// 輸出全部書籍
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

	// 登錄新書籍
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
			// 書已存在
			if(find_booknum(Integer.valueOf(a)))
				System.out.println("\nThe book has already existed!");
			else
			{
				System.out.println("Bookname:");
				sc.nextLine();
				b = sc.nextLine();
				// 書已存在
				if(find_bookname(b))
					System.out.println("\nThe book has already existed!");
				// 登錄成功
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

	// 刪除書籍
	public static boolean Staff_bookdelete(String choice)
	{
		switch(choice)
		{
			// 輸入書名
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				// 書不存在
				if(!find_bookname(sc.nextLine()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			// 輸入書號
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				// 書不存在
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
		// 刪除成功
		for(int i = index;i<=num_of_book;i++)
			Book[i] = Book[i+1];
		num_of_book -= 1;
		System.out.println("\nDelete successfully!");
		return true;
	}

	// 更新書籍資料
	public static boolean Staff_bookedit(String choice)
	{
		switch(choice)
		{
			// 輸入書名
			case "bookname":
				System.out.println("\nEnter the Bookname: ");
				sc.nextLine();
				// 書不存在
				if(!find_bookname(sc.nextLine()))
				{
					System.out.println("\nThe book doesn't exist!");
					return true;
				}
				break;
			// 輸入書號
			case "booknum":
				System.out.println("\nEnter the Booknumber: ");
				// 書不存在
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
		// 開始修改資料
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

	// 查詢學生資料
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

	// 用書名找書
	public static boolean find_bookname(String input)
	{
		for(int i = 0;i<=num_of_book+1;i++)
		{
			// 沒有書
			if(i==num_of_book+1)
				break;
			// 有書
			else if(input.equals(Book[i].book_name))
			{
				index = i;
				return true;
			}
		}
		return false;
	}

	// 用書號找書
	public static boolean find_booknum(int input)
	{
		for(int i = 0;i<=num_of_book+1;i++)
		{
			// 沒有書
			if(i==num_of_book+1)
				break;
			// 有書
			else if(input==Book[i].book_num)
			{
				index = i;
				return true;
			}
		}
		return false;
	}

	// 主程式
	public static void main(String[] args)
	{
		// 預設書單
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
		// 進入登入介面
		identity();
	}
}
