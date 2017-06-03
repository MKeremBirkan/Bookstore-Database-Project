import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CSE348BookStoreApp extends JFrame {

	private JPanel contentPane;
	BookStoreDataBase DATABASE;
	private JTextField name;
	private JTextField surname;
	private JTable table;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CSE348BookStoreApp frame = new CSE348BookStoreApp();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CSE348BookStoreApp() {
		setTitle("CSE348 Book Store APP");
		try {
			DATABASE = new BookStoreDataBase();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1475, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnInsert = new JButton("CREATE TABLES");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					DATABASE.INSERT_TABLES();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnInsert.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnInsert);
		
		JButton btnNewButton = new JButton("INSERT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DATABASE.INSERT_DATA();
				} catch (Exception x) {
					// TODO Auto-generated catch block
					x.printStackTrace();
				}
				
				
			}
		});
		panel.add(btnNewButton);
		
		JComboBox Districts = new JComboBox();
		Districts.setModel(new DefaultComboBoxModel(new String[] {"KARADENIZ BOLGESI", "MARMARA BOLGESI", "EGE BOLGESI", "AKDENIZ BOLGESI", "IC ANADOLU BOLGESI", "DOGU ANADOLU BOLGESI", "GUNEYDOGU ANADOLU BOLGESI"}));
		panel.add(Districts);
		
		
		
		JButton btnA = new JButton("C1a");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int District = Districts.getSelectedIndex() + 1;
				//System.out.println(District);
				
			}
		});
		panel.add(btnA);
		
		JButton btnA_1 = new JButton("C1b");
		panel.add(btnA_1);
		
		JLabel lblChooseDistrict = new JLabel("Choose District");
		panel.add(lblChooseDistrict);
		
		
		
		
		
		JButton btnC = new JButton("C2");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				
					String customername = name.getText();
					String customerSurname = surname.getText();
	
					List<CustomerSaleTableNode> SALELIST = null;
					
					if (customername != null && customername.trim().length() > 0 && customerSurname != null && customerSurname.trim().length() > 0) {
						
						SALELIST = DATABASE.searchCustomer_Name(customername,customerSurname);
					}
					
					CustomerSaleTableSet model = new CustomerSaleTableSet(SALELIST);
					
					table.setModel(model);
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(CSE348BookStoreApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		panel.add(btnC);
		
		JLabel lblEnterName = new JLabel("Enter Name");
		panel.add(lblEnterName);
		
		name = new JTextField();
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblEnterSurname = new JLabel("Enter Surname");
		panel.add(lblEnterSurname);
		
		surname = new JTextField();
		panel.add(surname);
		surname.setColumns(10);
		
		JButton btnC_1 = new JButton("C3a");
		panel.add(btnC_1);
		
		JButton btnCb = new JButton("C3b");
		panel.add(btnCb);
		
		JLabel lblChooseBranch = new JLabel("Choose Branch");
		panel.add(lblChooseBranch);
		
		JComboBox branchs = new JComboBox();
		branchs.setModel(new DefaultComboBoxModel(new String[] {"candy wrapper", "floor", "playing card", "canvas", "flag", "cell phone", "street lights", "greeting card", "deodorant", "lace", "drill press", "sand paper", "video games", "remote", "bed", "paint brush", "shoe lace", "thermostat", "eraser", "soap", "bottle cap", "helmet", "truck", "tissue box", "washing machine", "charger", "lip gloss", "hair tie", "watch", "eye liner", "bottle", "buckel", "computer", "stockings", "picture frame", "socks", "knife", "food", "mp3 player", "leg warmers", "toothbrush", "fridge", "bread", "paper", "coasters", "slipper", "cork", "twezzers", "table", "puddle", "shirt", "credit card", "shoes", "milk", "shampoo", "toilet", "rubber duck", "pillow", "sponge", "sharpie", "hair brush", "thermometer", "headphones", "stop sign", "glass", "pencil", "clothes", "blanket", "car", "rubber band", "window", "tomato", "bag", "button", "flowers", "house", "magnet", "rusty nail", "sofa", "lamp shade", "key chain", "door", "pen", "cat", "fork", "perfume", "balloon", "keyboard", "speakers", "chocolate", "lotion", "teddies", "sketch pad", "conditioner", "soy sauce packet", "clay pot", "vase", "desk", "hanger", "chair", "checkbook", "candle", "air freshener", "purse", "fake flowers", "ring", "newspaper", "mirror", "plate", "glasses", "packing peanuts", "chapter book", "ipod", "pool stick", "soda can", "sun glasses", "book", "doll", "tire swing", "scotch tape", "mop", "white out", "monitor", "phone", "cinder block", "clamp", "boom box", "nail file", "radio", "shovel", "money", "toothpaste", "plastic fork", "tree", "drawer", "face wash", "beef", "thread", "couch", "mouse pad", "tv", "shawl", "bookmark", "water bottle", "nail clippers", "blouse", "spoon", "rug", "sticky note", "camera", "bracelet", "tooth picks", "toe ring", "twister", "outlet", "zipper", "chalk", "brocolli", "spring", "sidewalk", "carrots", "grid paper", "bowl", "cup", "apple", "screw", "pants", "lamp", "box", "glow stick", "keys", "ice cube tray", "seat belt", "sandal", "clock", "bananas", "wallet", "USB drive", "television", "cookie jar", "towel", "wagon", "bow", "model car", "needle", "controller", "piano", "sailboat", "photo album", "CD", "creature", "spade", "hobbies", "liquid", "shake", "dinosaurs", "lettuce", "friction", "chance", "pie", "passenger", "fowl", "beginner", "sand", "lunchroom", "nut", "children", "limit", "ear", "measure", "glass", "mark", "side", "acoustics", "tramp", "doctor", "match", "jail", "pies", "shoes", "suit", "stick", "rake", "touch", "angle", "animal", "bear", "magic", "vegetable", "horn", "step", "station", "amount", "structure", "throat", "thing", "aunt", "volleyball", "bubble", "sheet", "discussion", "water", "holiday", "substance", "pest", "boat", "morning", "men", "mist", "celery", "snail", "cat", "battle", "turkey", "silver", "division", "flight", "fact", "mitten", "songs", "snow", "decision", "basket", "bells", "sock", "wire", "partner", "pen", "quartz", "boundary", "kick", "rabbit", "feeling", "carriage", "tray", "toothbrush", "color", "cough", "zinc", "pig", "man", "hole", "grade", "power", "humor", "trick", "relation", "cub", "grass", "unit", "behavior", "yam", "field", "weight", "smile", "snakes", "star", "sidewalk", "bike", "muscle", "truck", "foot", "competition", "blade", "cow", "thought", "sack", "trains", "advertisement", "trees", "view", "wish", "squirrel", "cherries", "show", "tomatoes", "car", "brother", "houses", "crack", "donkey", "grip", "example", "stop", "quarter", "rhythm", "pancake", "sea", "surprise", "expert", "potato", "join", "bee", "way", "approval", "watch", "earth", "canvas", "spot", "interest", "yard", "cave", "throne", "drain", "apparel", "scent", "letter", "horses", "jar", "toad", "tin", "laugh", "skirt", "air", "history", "pear", "vest", "sisters", "minute", "coat", "toes", "pizzas", "rest", "knot", "machine", "end", "geese", "debt", "thumb", "friends", "snails", "guitar", "religion", "girl", "wing", "cactus", "plane", "price", "mint", "offer", "request", "mice", "authority", "meeting", "profit", "fruit", "ladybug", "person", "distance", "territory", "hook", "dog", "stop", "cable", "twist", "hat", "existence", "laugh", "glove", "rainstorm", "bells", "activity", "celery", "donkey", "woman"}));
		panel.add(branchs);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
