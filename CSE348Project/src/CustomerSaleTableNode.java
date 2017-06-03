

public class CustomerSaleTableNode {

	private String customer_name;
	private String customer_surname;
	private String district_name;
	private String city_name;
	private String branch_name;
	private String salesman_name;
	private String sale_date;
	private String book_price;
	
	
	public CustomerSaleTableNode(String customer_name,
			String customer_surname,
			String district_name,
			String city_name,
			String branch_name,
			String salesman_name,
			String sale_date,
			String book_price ) {
		
		this.customer_surname = customer_surname;
		this.district_name = district_name;
		this.city_name = city_name;
		this.branch_name = branch_name;
		this.salesman_name =salesman_name;
		this.sale_date = sale_date;
		this.book_price =book_price;
	}


	public String getCustomer_name() {
		return customer_name;
	}


	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}


	public String getCustomer_surname() {
		return customer_surname;
	}


	public void setCustomer_surname(String customer_surname) {
		this.customer_surname = customer_surname;
	}


	public String getDistrict_name() {
		return district_name;
	}


	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}


	public String getCity_name() {
		return city_name;
	}


	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}


	public String getBranch_name() {
		return branch_name;
	}


	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}


	public String getSalesman_name() {
		return salesman_name;
	}


	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}


	public String getSale_date() {
		return sale_date;
	}


	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}


	public String getBook_price() {
		return book_price;
	}


	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}
	
	
	
		
}
