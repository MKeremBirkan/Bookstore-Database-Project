
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BookStoreDataBase {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/CSE348Bookstore";
	
	static final String USER = "root";
	static final String PASS = "mysql";
	
	private Connection Myconn = null;


	public Random generator = new Random(); 
	


	
	public BookStoreDataBase() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		System.out.println("Connecting to database...");
		Myconn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		
		System.out.println("DB connection successful to: " +DB_URL +"\n");
		
		
	}
	
	
	public List<CustomerSaleTableNode> searchCustomer_Name(String c_name,String c_Surname) throws Exception {
		
		List<CustomerSaleTableNode> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			//myStmt = Myconn.prepareStatement("select * from emp where ename like ?");
			
			//myStmt.setString(1, emp_name);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				CustomerSaleTableNode tempEmployee = convertRow(myRs);
				list.add(tempEmployee);
			}
			
			return list;
		}
		finally {
			closeALL((Statement) myStmt, myRs);
		}
	}
	private CustomerSaleTableNode convertRow(ResultSet ReSet) throws SQLException {
		
		String customer_name = ReSet.getString("C_NAME");
		String customer_surname = ReSet.getString("C_Surname");
		String district_name = ReSet.getString("districtName");
		String city_name = ReSet.getString("CityName");
		String branch_name = ReSet.getString("branches_name");
		String salesman_name = ReSet.getString("salesman_name");
		String sale_date = ReSet.getString("sale_date");
		String book_price = ReSet.getString("book_price");
		
		
		CustomerSaleTableNode the_row = new CustomerSaleTableNode(customer_name,customer_surname,district_name,city_name,branch_name,salesman_name,sale_date,book_price);
		
		return the_row;
	}
	public void INSERT_DATA() throws Exception {
		
		INSERT_Districts();
		INSERT_Cities();
		INSERT_Branches();										
		INSERT_Salesman();
		INSERT_CustomerName();
		INSERT_CustomerAdress();
		INSERT_Book();
		INSERT_Sale();
		System.out.println("---------INSERTION SUCCESS----------");
	}
	public void INSERT_TABLES() throws Exception {
		
		PreparedStatement myStmt = null;

		try {
			CLEAR_TABLES();
			System.out.println("DATABASE TABLES CLEARED !");
			
			myStmt = Myconn.prepareStatement("CREATE TABLE `CSE348Bookstore`.`Districts` ( `districtId` INT NOT NULL , `districtName` VARCHAR(45) NOT NULL , PRIMARY KEY (`districtId`)) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_turkish_ci;");
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("CREATE TABLE `CSE348Bookstore`.`Cities` ( `cityId` INT NOT NULL , `CityName` VARCHAR(60) NOT NULL , `districtId` INT NOT NULL , PRIMARY KEY (`cityId`),FOREIGN KEY(`districtId`) REFERENCES `CSE348Bookstore`.`Districts`(`districtId`)) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_turkish_ci;");				
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("CREATE TABLE `CSE348Bookstore`.`branches` ( `B_id` INT NOT NULL , `branches_name` VARCHAR(255) NOT NULL ,`city_id` INT NOT NULL, PRIMARY KEY (`B_id`),FOREIGN KEY(`city_id`) REFERENCES `CSE348Bookstore`.`Cities`(`cityId`)) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_turkish_ci;");				
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("CREATE TABLE `CSE348Bookstore`.`salesmans` ( `S_id` INT NOT NULL  , `salesman_name` VARCHAR(255) NOT NULL , `salesman_surname` VARCHAR(255) NOT NULL ,`branches_id` INT NOT NULL, PRIMARY KEY (`S_id`),FOREIGN KEY(`branches_id`) REFERENCES `CSE348Bookstore`.`branches`(`B_id`)) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_turkish_ci;");				
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("CREATE TABLE `CSE348Bookstore`.`customer_name` (`C_ID` INT NOT NULL , `C_NAME` VARCHAR(60) NOT NULL ,`C_Surname` VARCHAR(60) NOT NULL ,`branches_id` INT NOT NULL, PRIMARY KEY (`C_ID`),FOREIGN KEY(`branches_id`) REFERENCES `CSE348Bookstore`.`branches`(`B_id`)) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_turkish_ci;");				
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("CREATE TABLE `CSE348Bookstore`.`Customer_Adresses` ( `C_ID` INT NOT NULL , `C_place` VARCHAR(60) NOT NULL ,`CityName` VARCHAR(60) NOT NULL, `DistrictId` INT NOT NULL ,`C_Plaka`  INT NOT NULL , `C_Phone` VARCHAR(60) NOT NULL , PRIMARY KEY (`C_ID`),FOREIGN KEY(`C_ID`) REFERENCES `CSE348Bookstore`.`customer_name`(`C_ID`))ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_turkish_ci;");				
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("CREATE TABLE `CSE348Bookstore`.`book` ( `Book_id` INT NOT NULL  , `book_name` VARCHAR(255) NOT NULL , `isbn` VARCHAR(55) NOT NULL ,`book_price` INT NOT NULL,`branches_id` INT NOT NULL, PRIMARY KEY (`Book_id`),FOREIGN KEY(`branches_id`) REFERENCES `CSE348Bookstore`.`branches`(`B_id`)) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_turkish_ci;");				
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("CREATE TABLE `CSE348Bookstore`.`Sale` ( `id` INT NOT NULL AUTO_INCREMENT , `salesman_id` INT NOT NULL , `customer_id` INT NOT NULL ,`book_id` INT NOT NULL,`sale_amount` INT NOT NULL,`sale_date` DATE NOT NULL,`branches_id` INT NOT NULL, PRIMARY KEY (`id`),FOREIGN KEY (`salesman_id`) REFERENCES `CSE348Bookstore`.`salesmans`(`S_id`),FOREIGN KEY(`customer_id`) REFERENCES `CSE348Bookstore`.`customer_name`(`C_ID`),FOREIGN KEY(`book_id`) REFERENCES `CSE348Bookstore`.`book`(`Book_id`),FOREIGN KEY(`branches_id`) REFERENCES `CSE348Bookstore`.`branches`(`B_id`)) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_turkish_ci;");				
			myStmt.executeUpdate();
			
			System.out.println("DATABASE TABLES CREATED !");
			
		}
		catch (Exception exc) { exc.printStackTrace(); }
		finally {
			closeALL((Statement)myStmt);
		}
			
	}
	public void CLEAR_TABLES() throws Exception {
		
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = Myconn.prepareStatement("DROP TABLE IF EXISTS `CSE348Bookstore`.`Sale`;");
			myStmt.executeUpdate();	
			myStmt = Myconn.prepareStatement("DROP TABLE IF EXISTS `CSE348Bookstore`.`book`;");
			myStmt.executeUpdate();	
			myStmt = Myconn.prepareStatement("DROP TABLE IF EXISTS `CSE348Bookstore`.`Customer_Adresses`;");
			myStmt.executeUpdate();	
			myStmt = Myconn.prepareStatement("DROP TABLE IF EXISTS `CSE348Bookstore`.`customer_name`;");
			myStmt.executeUpdate();	
			myStmt = Myconn.prepareStatement("DROP TABLE IF EXISTS `CSE348Bookstore`.`salesmans`;");
			myStmt.executeUpdate();	
			myStmt = Myconn.prepareStatement("DROP TABLE IF EXISTS `CSE348Bookstore`.`branches`;");
			myStmt.executeUpdate();	
			myStmt = Myconn.prepareStatement("DROP TABLE IF EXISTS `CSE348Bookstore`.`Cities`;");
			myStmt.executeUpdate();	
			myStmt = Myconn.prepareStatement("DROP TABLE IF EXISTS `CSE348Bookstore`.`Districts`;");
			myStmt.executeUpdate();	
													
		}
		catch (Exception exc) {exc.printStackTrace(); }
		finally {
			closeALL((Statement)myStmt);
		}
			
	}
	public void INSERT_Sale() throws SQLException{
		
		PreparedStatement myStmt = null;
		String Statement;
		int k=1,i=1;
		int rand1,rand2,rand3;
		int salesman_id,branch_id,book_id,sale_am;
		int books = 10;
		int totalbooks = 2025 * books;//2025 customers
		
		
		
		try {
			while (totalbooks >= k) {

				rand1 = generator.nextInt(9) +3;//month

				rand2 = generator.nextInt(29) +1;//day

				rand3 = generator.nextInt(7) + 2008;//year

				String date;
				
				date = "'"+rand3+"-"+rand1+"-"+rand2+"'";
			

				rand1 =  generator.nextInt(4) + 1 ;//branch

				rand2 =  generator.nextInt(80) + 1;//city
				
				rand3 =  generator.nextInt(1) + 1 ;//salesman

				branch_id = rand1+rand2*10;// branch id = city-branchNO Example : 2. branch in 71. City  = 712
				
				salesman_id = (rand2-1)*20 + ((rand1*4)-rand3);

				book_id =  generator.nextInt(16199) + 1;// 16200 books in database

				sale_am =  generator.nextInt(15) + 1 ; 

				Statement = "INSERT INTO Sale (salesman_id,customer_id,book_id,sale_amount,branches_id,sale_date) VALUES ("+salesman_id+","+i+","+book_id+","+sale_am+","+branch_id+",STR_TO_DATE("+date+", '%Y-%m-%d'));\n";
		    	myStmt = Myconn.prepareStatement(Statement);
		    	//System.out.println(Statement);
				myStmt.executeUpdate();
					
				if(k%books==0)
					i++;
				k++;
				
			}
			
			System.out.println("SALE TABLE INSERTED !");										
		}
		catch (Exception exc) {exc.printStackTrace(); }
		finally {
			closeALL((Statement)myStmt);
			
			
		}

		
	}
	public void INSERT_Book() throws Exception {
		
		PreparedStatement myStmt = null;
		BufferedReader br1 = null,br2 = null;
		String line,line2 = null,Statement;
		int k=1;
		
		try {
			br1 = new BufferedReader(new FileReader("branc_ID.txt"));
			br2 = new BufferedReader(new FileReader("book_names.txt"));
			
			int price,isbn;
			
		    while ((line = br2.readLine()) != null) {
		
		    	if((k-1)%40==0)
		    		line2 = br1.readLine();
		    	
		    	price = generator.nextInt(50) + 10;
		    	
		    	isbn = generator.nextInt(99999) + 1;
		    	
		    	String ISBN = "978-" + isbn;
		    	    	
		    	Statement = "INSERT INTO book (Book_id,book_price,branches_id,book_name,isbn) VALUES ("+k+","+price+","+line2+",\""+line+"\",\""+ISBN+"\");";
		    	myStmt = Myconn.prepareStatement(Statement);
				myStmt.executeUpdate();
				
				k++;
		    }
			
		
			
			System.out.println("BOOK TABLE INSERTED !");										
		}
		catch (Exception exc) {exc.printStackTrace(); }
		finally {
			closeALL((Statement)myStmt);
			if (br1 != null) {
		        try {
		            br1.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
			if (br2 != null) {
		        try {
		            br2.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
			
		}
			
	}
	public void INSERT_CustomerAdress() throws Exception {
		
		PreparedStatement myStmt = null;
		BufferedReader br1 = null,br2 = null,br3 = null;
		String line,line2 = null,line3,Statement;
		int k,i=0,customers=1;
		
		String  bolge[] = new String[81];
		String  plaka[] = new String[81];
		String  sehir[] = new String[81];

		try {
			br1 = new BufferedReader(new FileReader("adress2025.txt"));
			br2 = new BufferedReader(new FileReader("iller.txt"));
			br3 = new BufferedReader(new FileReader("tel_numbers.txt"));
			
	        while ((line = br2.readLine()) != null) {

	        	
	        	String[] CT = line.split(" ");
	        	
	        	bolge[i] = CT[0];
	    		plaka[i] = CT[1];
	    		sehir[i] = CT[2];
				i++;
	        }
	        int random; 
	        
	        while ((line2 = br1.readLine()) != null) {
	        	
	        	line3 = br3.readLine();

	        	random = generator.nextInt(80) + 1;
	        	
	        	
	        	Statement = "INSERT INTO Customer_Adresses (C_ID,C_place,CityName,DistrictId,C_Plaka,C_Phone) VALUES ("+customers+" , \""+line2+"\" , \""+sehir[random]+"\" , "+bolge[random]+" , "+plaka[random]+" , \""+line3+"\");";
	        	myStmt = Myconn.prepareStatement(Statement);
				myStmt.executeUpdate();
				
				customers++;
	        	
	        }
		
			
			System.out.println("CUSTOMER_ADRESS TABLE INSERTED !");										
		}
		catch (Exception exc) {exc.printStackTrace(); }
		finally {
			closeALL((Statement)myStmt);
			if (br1 != null) {
	            try {
	                br1.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			if (br2 != null) {
	            try {
	                br2.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			if (br3 != null) {
	            try {
	                br3.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			
		}
			
	}
	public void INSERT_CustomerName() throws Exception {
		
		PreparedStatement myStmt = null;
		BufferedReader br1 = null,br2 = null;
		String line,line2 = null,Statement;
		int i=1;

		try {
			br1 = new BufferedReader(new FileReader("5k_isimler.txt"));
			br2 = new BufferedReader(new FileReader("branc_ID.txt"));
	        while ((line = br1.readLine()) != null) {

	        	
	        	String[] CT = line.split(" ");
	        	
	        	if((i-1)%5==0)
	        		line2 = br2.readLine();
	        	
	        	Statement = "INSERT INTO customer_name (C_ID,branches_id,C_NAME,C_Surname) VALUES ("+i+" , "+line2+",\""+CT[0]+"\",\""+CT[1]+"\");";
	        	myStmt = Myconn.prepareStatement(Statement);
				myStmt.executeUpdate();
				
				i++;
	        }
			
		
			
			System.out.println("CUSTOMER_NAMES TABLE INSERTED !");										
		}
		catch (Exception exc) {exc.printStackTrace(); }
		finally {
			closeALL((Statement)myStmt);
			if (br1 != null) {
	            try {
	                br1.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			if (br2 != null) {
	            try {
	                br2.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			
		}
			
	}
	public void INSERT_Salesman() throws Exception {
		
		PreparedStatement myStmt = null;
		BufferedReader br1 = null,br2 = null;
		String line,line2 = null,Statement;
		int i=1;

		try {
			br1 = new BufferedReader(new FileReader("salesman_names.txt"));
			br2 = new BufferedReader(new FileReader("branc_ID.txt"));
	        while ((line = br1.readLine()) != null) {

	        	
	        	String[] CT = line.split(" ");
	        	
	        	if((i-1)%4==0)
	        		line2 = br2.readLine();
	        	
	        	Statement = "INSERT INTO salesmans (S_id,branches_id,salesman_name,salesman_surname) VALUES ("+i+" , "+line2+",\""+CT[0]+"\",\""+CT[1]+"\");";
	        	myStmt = Myconn.prepareStatement(Statement);
				myStmt.executeUpdate();
				
				i++;
	        }
			
		
			
			System.out.println("SALESMAN TABLE INSERTED !");										
		}
		catch (Exception exc) {exc.printStackTrace(); }
		finally {
			closeALL((Statement)myStmt);
			if (br1 != null) {
	            try {
	                br1.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			if (br2 != null) {
	            try {
	                br2.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			
		}
			
	}
	public void INSERT_Branches() throws Exception {
		
		PreparedStatement myStmt = null;
		BufferedReader br1 = null,br2 = null;
		String line,line2,Statement;
		int i=1,k=1;

		try {
			br1 = new BufferedReader(new FileReader("branc_ID.txt"));
			br2 = new BufferedReader(new FileReader("sirketler.txt"));
	        while ((line = br1.readLine()) != null) {
	        	
	        	line2 = br2.readLine();
	        	//System.out.println(line);
	        	
	        	Statement = "INSERT INTO branches (B_id,branches_name,city_id) VALUES ("+line+",\""+line2+" Bookstore\", "+i+" );";
	        	myStmt = Myconn.prepareStatement(Statement);
				myStmt.executeUpdate();
				
				if(k%5==0)
					i++;
				k++;
	        }
			
		
			
			System.out.println("BRANCHES TABLE INSERTED !");										
		}
		catch (Exception exc) {exc.printStackTrace(); }
		finally {
			closeALL((Statement)myStmt);
			if (br1 != null) {
	            try {
	                br1.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			if (br2 != null) {
	            try {
	                br2.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}
			
	}
	public void INSERT_Cities() throws Exception {
		
		PreparedStatement myStmt = null;
		BufferedReader br = null;
		String line,Statement;

		try {
			br = new BufferedReader(new FileReader("iller.txt"));
	        while ((line = br.readLine()) != null) {

	        	//System.out.println(line);
	        	String[] CT = line.split(" ");
	        	Statement = "INSERT INTO  Cities ( districtId ,  cityId , CityName) VALUES ("+ CT[0] +" , " + CT[1] +" , \"" +CT[2]+"\");";
	        	myStmt = Myconn.prepareStatement(Statement);
				myStmt.executeUpdate();
	        }
			
		
			
			System.out.println("CITIES TABLE INSERTED !");										
		}
		catch (Exception exc) {exc.printStackTrace(); }
		finally {
			closeALL((Statement)myStmt);
			if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}
			
	}
	
	public void INSERT_Districts() throws Exception {
		
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			
		
			myStmt = Myconn.prepareStatement("INSERT INTO Districts ( districtId, districtName) VALUES (1,\"KARADENIZ BOLGESI\");");
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("INSERT INTO Districts ( districtId, districtName) VALUES (2,\"MARMARA BOLGESI\");");
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("INSERT INTO Districts ( districtId, districtName) VALUES (3,\"EGE BOLGESI\");");
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("INSERT INTO Districts ( districtId, districtName) VALUES (4,\"AKDENIZ BOLGESI\");");
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("INSERT INTO Districts ( districtId, districtName) VALUES (5,\"IC ANADOLU  BOLGESI\");");
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("INSERT INTO Districts ( districtId, districtName) VALUES (6,\"DOGU ANADOLU BOLGESI\");");
			myStmt.executeUpdate();
			myStmt = Myconn.prepareStatement("INSERT INTO Districts ( districtId, districtName) VALUES (7,\"GUNEYDOGU ANADOLU BOLGESI\");");
			myStmt.executeUpdate();
				
			System.out.println("DISTRICT TABLE INSERTED !");										
		}
		catch (Exception exc) {exc.printStackTrace(); }
		finally {
			closeALL((Statement)myStmt);
		}
			
	}
	
	private void closeALL(Statement myStmt) throws SQLException {
		Close(null, myStmt, null);		
	}
	private void closeALL(Statement Stmt, ResultSet ReSET) throws SQLException {
		Close(null, Stmt, ReSET);		
	}
	
	
	private static void Close(Connection Connect, Statement Stmt, ResultSet ReSET)
			throws SQLException {

		if (ReSET != null) {
			ReSET.close();
		}

		if (Stmt != null) {
			Stmt.close();
		}
		
		if (Connect != null) {
			Connect.close();
		}
	}
	

}
