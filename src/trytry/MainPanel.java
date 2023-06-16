package trytry;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
 
import java.lang.NumberFormatException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	
	//creating all the array's that are needed for this program in particular
	private final static String[] comboBox_selection = {
			"Pound to Japanese Yen"
			,"Pound to Euro"
			,"Pound to US Dollars"
			,"Pounds to Australian Dollars"
			,"Pound to Canadian Dollars"
			,"Pound to South Korean Won"
			,"Pound to Thai Baht"
			,"Pound to United Arab Emirates Dirham"};
	
	
	private static Double[] newFactorArray = {137.52, 1.09, 1.29, 1.78, 1.70, 1537.75, 40.52, 4.75}; //storing the value of pound in different currency in an array
	
	private static String[] currency_Symbol = {"¥", "€", "$", "A$", "C$", "₩", "฿", "د إ"}; //storing the symbol of different currency in an array
	
	String textSplitter[] = new String[3]; //
	
	String name [] = new String[40];
	
	double price [] = new double[40];
	
	String symbol [] = new String[40];
	
	private JTextField Text;
	
	private JLabel Result,Result1, total_Conversion;
	
	private JComboBox<String> comboBox;
	
	private JCheckBox reverse_checkBox;
	
	
	int total_conversion = 0; 
	
	int count=0; //an integer for the 'for loop'

	JButton ConBtn, ClrBtn; //declaring buttons for converting and clearing


	JMenuBar setupMenu() { 
		
		
		JMenuBar menuBar = new JMenuBar();
		
		//creating menu named as file, edit and help
		JMenu fileOption = new JMenu("File");
		JMenu editOption = new JMenu("Edit");
		JMenu helpOption = new JMenu("About");
		
		
		menuBar.add(fileOption);
		menuBar.add(editOption);
		menuBar.add(helpOption);

		//{adding actions, shortcut keys and icons for the 'File' Menu
		
		//{declaring menu item 'New' for menu
		
			JMenuItem File = new JMenuItem("New");
		
			File.setIcon(new ImageIcon("file.png"));	 //adding icons to menu item
			File.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK)); 
		
		//} end of menu item 'New'
		
		//{declaring menu item 'Open' for menu
		
			JMenuItem Open = new JMenuItem("Load File");
		
			Open.setIcon(new ImageIcon("open.png")); //adding icons to menu item
			Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK)); //adding shortcut keys to menu item
			
			//adding action listener into 'Open' menu item so that user can select which file to be accessed as per their desire
			Open.addActionListener(new ActionListener()
			{
				@Override
					public void actionPerformed(ActionEvent e) 
					{
						File file;
						JFileChooser fileChooser = new JFileChooser();
						int fileStat = fileChooser.showOpenDialog(null);
						
						if (fileStat == JFileChooser.APPROVE_OPTION) 
						{
							file = fileChooser.getSelectedFile(); //selects the file that user selected and stores the file in 'file'
							
								try { //exception handling for reading the file
									
										BufferedReader fileOpener = new BufferedReader(new InputStreamReader( new FileInputStream(file),"UTF8")); //reads the file
										Object[] line = fileOpener.lines().toArray(); //reads the content on the file, line by line and stores it in an array
										comboBox.removeAllItems(); //removes and replaces all the content within the comboBox
										ArrayList<Double> arrList = new ArrayList<Double>();
										ArrayList<String> arrLists = new ArrayList<String>();
											for(int i=0; i < line.length; i++)
												{
							
													try { //exception handling for validating the data
														
															String s = line[i].toString(); //converts all the file contents into String
							
															textSplitter=s.split(","); //delimeter is used to split between the lines
							
															if(textSplitter.length == 3) //using delimeter to seperate data
															{
								
																name[count]=textSplitter[0].trim(); //storing the first value of the splitted item to an array
																price[count]=Double.parseDouble(textSplitter[1].trim()); //storing the second value of the splitted item to an array
																symbol[count]=textSplitter[2].trim();
																arrList.add(price[count]);
																arrLists.add(symbol[count]);
																symbol[count]=textSplitter[2].trim(); //storing the third value of the splitted item to an array
								
																if(name[count].isEmpty() || symbol[count].isEmpty()) //in case the name and symbol fields are empty, they are ignored and the further processes are carried out
																{
																	continue;
																}
								
																else
																	comboBox.addItem(s); //adding items into the combobox after exception handling of the data
								
															}
							
														}	
							
													catch (NumberFormatException er)
													{
														JOptionPane.showMessageDialog(null,"Exception : Price - Invalid Format");
													}
							
							
							
												}
						
											newFactorArray = arrList.toArray(new Double[0]);
											currency_Symbol = arrLists.toArray(new String[0]);
											fileOpener.close(); //stops accessing the file which the user selected
								}
					
								catch(Exception er){
									JOptionPane.showMessageDialog(null,"Exception: Empty File"); //exception that show's up once file is not found
								}
						}
		
					}
			});
		
		//} end of menu item 'Open'
		
		
		//{declaring menu item 'Save' for menu
		
			JMenuItem Save = new JMenuItem("Save");
			
			Save.setIcon(new ImageIcon("save.png")); //adding shortcut keys to menu item
			Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK)); //adding icons to menu item
			
		//} end of menu item 'Save'
		
		//{declaring menu item 'Exit' for menu
			
			JMenuItem Exit = new JMenuItem("Exit"); 
		
			Exit.setIcon(new ImageIcon("exit.png")); //adding icons to menu item
			Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK)); //adding shortcut keys to menu item
		
			Exit.addActionListener(e->{ //adding action listener for the 'Exit' menu item

			//confirms the user's decision of exiting the application by making sure, they really want to exit the application with the help of confirm dialog
			int appExit = JOptionPane.showConfirmDialog(null , "\t\t Do you want to exit the Application?", "Exit Application",JOptionPane.YES_NO_OPTION);

			//exits the application once 'yes' option is selected
			if (appExit == JOptionPane.YES_OPTION){
				
				System.exit(0); //command for exiting the application
				
			}
			});
		
		//} end of menu item 'Exit'
			
		//}end of the 'File' Menu
	
		//{adding shortcut keys and icons for the 'Edit' Menu
			
		//{declaring menu item 'Copy' for menu
			
			JMenuItem Copy = new JMenuItem("Copy");
		
			Copy.setIcon(new ImageIcon("paste.png")); //adding icons to menu item
			Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK)); //adding shortcut keys to menu item
		
		//} end of menu item 'Copy'
		
		//{declaring menu item 'Paste' for menu
			
			JMenuItem Paste = new JMenuItem("Paste");
			Paste.setIcon(new ImageIcon("paste.png")); //adding icons to menu item
			Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK)); //adding shortcut keys to menu item
		
			//} end of menu item 'Paste'
		
		//}end of the 'Edit' Menu
			
		//{declaring menu item 'About' for menu
		
		//{declaring menu item 'Contact_Us' for menu
			
		JMenuItem Contact_Us = new JMenuItem("Contact Us");
		
		Contact_Us.setIcon(new ImageIcon("about.png")); //adding icons to menu item
		Contact_Us.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK)); //adding shortcut keys to menu item
		Contact_Us.addActionListener(e-> { //adding action listener to
			JOptionPane.showMessageDialog(null, "Currency Convertor Application \n\n(Developed by: EkataGhimire)\n\n ©Copyright 2020"); //message is displayed once 'About' menu item is clicked.

		});
		
		//}end of the 'Contact_Us'
		
		//}end of the 'About' Menu

		//declaring separators for proper spacing between menu items
		
		JSeparator seperator1 = new JSeparator();
		JSeparator seperator2 = new JSeparator();
		JSeparator seperator3 = new JSeparator();
		JSeparator seperator4 = new JSeparator();
		

		//adding menubar named as 'File' to the frame
		fileOption.add(File);
		//adding seperators for proper spacing
		fileOption.add(seperator1); 
		
		//adding menu item named as 'Open' to the frame
		fileOption.add(Open);
		//adding seperators for proper spacing
		fileOption.add(seperator2);

		//adding menu item named as 'save' to the frame
		fileOption.add(Save);
		//adding seperators for proper spacing
		fileOption.add(seperator3);

		//adding menu item named as 'exit' to the frame
		fileOption.add(Exit);
		
		//adding menu item named as 'copy' to the frame
		editOption.add(Copy);
		//declaring separators for proper spacing between menu items
		editOption.add(seperator4);
		
		//adding menu item named as 'paste' to the frame
		editOption.add(Paste);
		
		//adding menu item named as 'contact_us' to the frame
		helpOption.add(Contact_Us);

		return menuBar;

	}


	MainPanel() { //a constructor which handles action listeners, tooltiptexts and addition of the components to the frame

		ActionListener Convert = new ConvertListener();

		JLabel inputLabel1 = new JLabel("Currency Convertor"); //a label for displaying where to enter the value
		JLabel inputLabel = new JLabel("Insert your Value"); //a label for displaying where to enter the value
		
		//adding action listeners and tooltiptext to buttons
		
		ConBtn = new JButton("Convert"); //convert button
		ConBtn.setToolTipText("Convert"); //when hovered around convert buttons perimeter, message is shown

		ClrBtn = new JButton("Clear"); //clear button
		ClrBtn.setToolTipText("Clear All Data"); //when hovered around convert buttons perimeter, message is shown
		
		ConBtn.addActionListener(Convert); // converts values when action
		ClrBtn.addActionListener(Convert); // clears all the text values when pressed
		
		
		//adding action listener, tooltiptext and combobox
		comboBox = new JComboBox<String>(comboBox_selection);
		comboBox.setToolTipText("ComboBox"); // when hovered around combobox perimeter, message is shown
		
		
		
		reverse_checkBox = new JCheckBox("Reverse Conversion"); //declaring a checkbox for reverse calculation purpose
		reverse_checkBox.setToolTipText("Reverse Conversion"); //when hovered around checkbox perimeter, message is shown
		
		Result1 = new JLabel("Result : "); // a label for result
		Result = new JLabel(""); // a label for result
		total_Conversion = new JLabel("Total Number of Conversion : 0"); //a label for displaying total number of conversions
		Text = new JTextField(5); //a text field for entering the data for the conversion
		Text.addKeyListener(new KeyAdapter() { 		
			public void keyPressed(KeyEvent a) {
				if (a.getKeyCode() == KeyEvent.VK_ENTER) { //key mapping 'ENTER' key to convert button
					ConBtn.doClick();
						
				}
				
			}
			
		});

		//adding all the components into the frame
		
		add(inputLabel1);
		inputLabel1.setBounds(210,30,200,40);
		inputLabel1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 22));
		
		add(comboBox);
		comboBox.setBounds(200,100,200,40);
		
		add(inputLabel);
		inputLabel.setBounds(235,160,200,40);
		inputLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		
		add(Text);
		Text.setBounds(200,220,200,30);
		
		add(ConBtn);
		ConBtn.setBounds(205,290,90,40);
		
		add(ClrBtn);
		ClrBtn.setBounds(305,290,90,40);
		
		add(reverse_checkBox);
		reverse_checkBox.setBounds(210,340,240,40);
		reverse_checkBox.setBackground(new Color(124,185,232));
		reverse_checkBox.setFont(new Font("Berlin Sans FB", Font.PLAIN, 19));
		
		add(Result1);
		Result1.setBounds(240,390,90,40);
		Result1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		
		add(Result);
		Result.setBounds(310,390,90,40);
		Result.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		
		add(total_Conversion);
		total_Conversion.setBounds(200,440,250,40);
		total_Conversion.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));

		//settings for the frame
		setPreferredSize(new Dimension(600, 600));
		setBackground(new Color(124,185,232));
	}

	private class ConvertListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent b) {
			
			Object object = b.getSource();
					
			if (object == ClrBtn) {
				Text.setText(null); //clears the textfield to null once clear button is pressed
				total_Conversion.setText("Total Number Of Conversion : 0" ); //sets the total_Conversion text field to default
				Result1.setText("Result: "); //sets the Result1 text field to default
				Result.setText(" "); //sets the Result text field to null

			}
			
			//removes white spaces and gets the text value that's in the text field provided by the user
			String text = Text.getText().trim();
			if (object == ConBtn) {
							
			//displays the total number of times conversion is performed
			total_Conversion.setText("Total Number Of Conversion : " + Integer.toString(++total_conversion));

				if (text.isEmpty() == true)
					{
				
					//displays a message dialog if the text field value is null
					JOptionPane.showMessageDialog(null, "Exception: Null Value Entered");

					}
			}

			
				if (text.isEmpty() == false) { //checks for condition and proceeds to another step once the condition is satisfied

					//checks if the checkbox is selected, if it is selected then it goes to another stepSSS
					if (reverse_checkBox.isSelected()){

						try { //exception handling

							//converting string into value using wrapper class
							double value = Double.parseDouble(text);

							//calculating the end result
							double result = (1/newFactorArray[comboBox.getSelectedIndex()])* value;
							//making the output so that it only has 2 decimal places
							DecimalFormat f = new DecimalFormat("0.00");
							String output = f.format(result); 
							//displaying the end result
							Result.setText(output );	//converting the end result to string from double

						} catch (Exception e) { //handling exception in case the value entered by the user is of invalid data type

							//displays a message dialog in case user inputs a invalid data type
							JOptionPane.showMessageDialog(null, "Exception: Incorrect Data Type Entered!");

						}

					}

					else {

						try {

							//converting string into value using wrapper class
							double value = Double.parseDouble(text);

							//calculating the end result
							double result = newFactorArray[comboBox.getSelectedIndex()] * value;
							
							//making the output so that it only has 2 decimal places
							DecimalFormat f = new DecimalFormat("0.00");
							String output = f.format(result); 
							//displaying the end result
							Result.setText(output );	//converting the end result to string from double
						} 
						catch(Exception e) { //handling exception in case the value entered by the user is of invalid data type
							
							//displays a message dialog in case user inputs a invalid data type
							JOptionPane.showMessageDialog(null, "Exception: Incorrect Data Type Entered!");

					}
				}
			}
		}	
	}
}

