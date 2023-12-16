
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc.MyFrameConnect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ButtonGroup;

public class MyFrame extends JFrame {

	private JPanel contentPane;
	private JTextField first_name_tf;
	private JTextField last_name_tf;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox declare;
	private JTextArea address_ta;
	private JComboBox highest_qualification;
	private JComboBox job_profile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel first_name = new JLabel("First Name");
		first_name.setBounds(45, 62, 71, 13);
		contentPane.add(first_name);
		
		first_name_tf = new JTextField();
		first_name_tf.setBounds(190, 59, 161, 19);
		contentPane.add(first_name_tf);
		first_name_tf.setColumns(10);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		submit.setBounds(150, 455, 85, 21);
		contentPane.add(submit);
		
		JLabel lblNewLabel_1 = new JLabel("Job Application");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(111, 10, 150, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel last_name = new JLabel("Last Name");
		last_name.setBounds(45, 102, 71, 13);
		contentPane.add(last_name);
		
		last_name_tf = new JTextField();
		last_name_tf.setBounds(190, 99, 161, 19);
		contentPane.add(last_name_tf);
		last_name_tf.setColumns(10);
		
		JLabel gender_label = new JLabel("Gender");
		gender_label.setBounds(45, 153, 71, 13);
		contentPane.add(gender_label);
		
		JRadioButton male = new JRadioButton("Male");
		male.setActionCommand("Male");
		buttonGroup.add(male);
		male.setBounds(45, 172, 103, 21);
		contentPane.add(male);
		
		JRadioButton female = new JRadioButton("Female");
		female.setActionCommand("Female");
		buttonGroup.add(female);
		female.setBounds(150, 172, 111, 21);
		contentPane.add(female);
		
		JRadioButton other = new JRadioButton("Other");
		other.setActionCommand("Other");
		buttonGroup.add(other);
		other.setBounds(261, 172, 103, 21);
		contentPane.add(other);
				
		JLabel highest_qualification_label = new JLabel("Highest Qualification");
		highest_qualification_label.setBounds(45, 295, 122, 13);
		contentPane.add(highest_qualification_label);
		
		String[] qualifications= {"B.Tech", "M.Tech", "M.S.", "Ph.D", "Diploma"};
		highest_qualification = new JComboBox(qualifications);
		highest_qualification.setBounds(190, 291, 161, 21);
		contentPane.add(highest_qualification);
		
		JLabel address_label = new JLabel("Address");
		address_label.setBounds(45, 211, 71, 13);
		contentPane.add(address_label);
		
		address_ta = new JTextArea();
		address_ta.setBounds(45, 233, 306, 39);
		contentPane.add(address_ta);
		
		JLabel job_profile_label = new JLabel("Job Profile");
		job_profile_label.setBounds(45, 339, 71, 13);
		contentPane.add(job_profile_label);
		
		String[] jobs= {"Java Developer", "Software Tester", "UI/UX Designer", "C/C++ Developer", "Data Scientist", "Data Analyst"};
		job_profile = new JComboBox(jobs);
		job_profile.setBounds(190, 335, 161, 21);
		contentPane.add(job_profile);
		
		declare = new JCheckBox("I declare that all the information given above is accurate to the best of my knowledge.");
		declare.setBounds(45, 395, 306, 21);
		contentPane.add(declare);
		
		submit.addActionListener(new SubmitListener());
	}
	
	private class SubmitListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(declare.isSelected())
			{
				try
				{
					String fname=first_name_tf.getText();
					String lname=last_name_tf.getText();
					String name=fname+" "+lname;
					String gender=buttonGroup.getSelection().getActionCommand();
					String address=address_ta.getText();
					String qua=highest_qualification.getSelectedItem().toString();
					String job=job_profile.getSelectedItem().toString();
					
					if(fname.isBlank() || lname.isBlank() || address.isBlank())
						JOptionPane.showMessageDialog(null, "Enter all the fields.");
					else
					{
						System.out.println(name+"\n"+gender+"\n"+address+"\n"+qua+"\n"+job);
						MyFrameConnect obj=new MyFrameConnect(name, gender, address, qua, job);
						
							obj.insert();
						
						System.exit(EXIT_ON_CLOSE);
						
					}
				}
				catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Enter all the fields.");
				}
				catch(ClassNotFoundException | SQLException e2)
				{
					e2.getStackTrace();
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Cannot proceed unless declaration is ticked.");
			
		}
		
	}
}
