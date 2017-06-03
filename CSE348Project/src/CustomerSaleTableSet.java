

import java.util.List;
import javax.swing.table.AbstractTableModel;


class CustomerSaleTableSet extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int CUSTOMER_NAME = 0;
	private static final int CUSTOMER_SURNAME = 1;
	private static final int DISTRICT_NAME = 2;
	private static final int CITY_NAME = 3;
	private static final int BRANCH_NAME = 4;
	private static final int SALESMAN_NAME = 5;
	private static final int SALE_DATE = 6;
	private static final int BOOK_PRICE = 7;

	private String[] columnNames = { "CUSTOMER_NAME", "CUSTOMER_SURNAME", "DISTRICT_NAME","CITY_NAME","BRANCH_NAME","SALESMAN_NAME","SALE_DATE","BOOK_PRICE"};
	
	private List<CustomerSaleTableNode> SALELIST;

	public CustomerSaleTableSet(List<CustomerSaleTableNode> LIST) {
		SALELIST = LIST;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return SALELIST.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		CustomerSaleTableNode TEMPSALENODE = SALELIST.get(row);

		switch (col) {
		case CUSTOMER_NAME:
			return TEMPSALENODE.getCustomer_name();
		case CUSTOMER_SURNAME:
			return TEMPSALENODE.getCustomer_surname();
		case DISTRICT_NAME:
			return TEMPSALENODE.getDistrict_name();
		case CITY_NAME:
			return TEMPSALENODE.getCity_name();
		case BRANCH_NAME:
			return TEMPSALENODE.getBranch_name();
		case SALESMAN_NAME:
			return TEMPSALENODE.getSalesman_name();
		case SALE_DATE:
			return TEMPSALENODE.getSale_date();
		case BOOK_PRICE:
			return TEMPSALENODE.getBook_price();
		case OBJECT_COL:
			return TEMPSALENODE;
		default:
			return TEMPSALENODE.getCustomer_name();
		}
	}

	/*@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}*/
}
