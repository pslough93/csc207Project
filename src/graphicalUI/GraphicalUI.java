package graphicalUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Label;
/**
 * 
 * @author Samee Zahid, Noah Schlager and Patrick Slough
 * 
 * Used tutorials from the following two webpages:
 * 
 * https://docs.oracle.com/javase/tutorial/uiswing/
 * http://zetcode.com/tutorials/javaswingtutorial/
 *
 */
public class GraphicalUI extends JPanel implements ActionListener
{
  public String confFile;
  public String dateFile;
  public String mileFile;
  
  public String numTeams;
  public String numDates;
  public String nameFile;
  
  public JFrame frame;
  private JTextField txtConferenceInfo;
  private JButton dateButton;
  private JTextField txtDateInformationFile;
  private JTextField txtMilageInformationFile;
  private JButton  mileageButton;
  private JButton conferenceButton;
  private JButton submitButton;
  private JTextField txtNumberOfTeams;
  private JTextField txtGameDays;
  private JFileChooser fc;
  static GraphicalUI window;
  private JTextField txtOutputTextFile;



  /**
   * Launch the application.
   */
  public static void main(String[] args)
  {
    //Run asynchoronously on a different thread
    EventQueue.invokeLater(new Runnable()
      {
        public void run()
        {
          try
            {
              window = new GraphicalUI();
              //Make our frame visible
              window.frame.setVisible(true);
            }//try
          catch (Exception e)
            {
              e.printStackTrace();
            }//catch
        }//run
      });//new Runnable()
  }//main

  /**
   * Create the application.
   */
  public GraphicalUI()
  {
    initialize();
    
  }//GraphicalUI()
  
  
  //Helper method to obtain images from a path
  //and choose them as icons
  protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = GraphicalUI.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    }//if
    else {
      System.err.println("Couldn't find file at : " + path);
      return null;
    }//else
  }//ImageIcon

  /**
   * Initialize the contents of the frame.
   */
  private void initialize()
  {
    fc = new JFileChooser(System.getProperty("user.dir"));
    frame = new JFrame();
    frame.setBounds(100, 100, 450, 450);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    conferenceButton = new JButton("Choose", createImageIcon("/open.png"));
    conferenceButton .setBounds(263, 64, 81, 32);
    
    conferenceButton.addActionListener(this);
    frame.getContentPane().setLayout(null);
    frame.getContentPane().add(conferenceButton);
    
    txtConferenceInfo = new JTextField("Conference Information File");
    txtConferenceInfo.setBounds(24, 63, 200, 32);
    frame.getContentPane().add(txtConferenceInfo);
    txtConferenceInfo.setColumns(10);
    
    dateButton = new JButton("Choose", createImageIcon("/open.png"));
    dateButton.addActionListener(this);
    dateButton.setBounds(263, 118, 81, 32);
    frame.getContentPane().add(dateButton);
    
    txtDateInformationFile = new JTextField("Date Information File");
    txtDateInformationFile.setColumns(10);
    txtDateInformationFile.setBounds(24, 118, 200, 32);
    frame.getContentPane().add(txtDateInformationFile);
    
    txtMilageInformationFile = new JTextField("Milage Information File");
    txtMilageInformationFile.setColumns(10);
    txtMilageInformationFile.setBounds(24, 170, 200, 32);
    frame.getContentPane().add(txtMilageInformationFile);
    
    mileageButton = new JButton("Choose", createImageIcon("/open.png"));
    mileageButton.addActionListener(this);
    mileageButton.setBounds(263, 171, 81, 32);
    frame.getContentPane().add( mileageButton);
    
    submitButton = new JButton("Submit");
    submitButton.setBounds(301, 377, 122, 32);
    frame.getContentPane().add(submitButton);
    //What to do when the button is clicked
    submitButton.addActionListener(new ActionListener()
      {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
          confFile = txtConferenceInfo.getText();
          dateFile = txtDateInformationFile.getText();
          mileFile = txtMilageInformationFile.getText();
          
          numDates =txtNumberOfTeams.getText();
          numTeams = txtGameDays.getText();
          nameFile = txtOutputTextFile.getText();
          
          frame.setVisible(false);
          try
          {
            //Package our data and submit it - not sure this is the best way to do it.
            //Maybe an interface would have been better, but oh well.
            String[] output = new String[]{confFile, dateFile, mileFile, numDates, numTeams, nameFile};
            sportsScheduler.Expt.main(output);
          }//try
        catch (Exception e1)
          {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }//catch  
        }//action Performed
      });//new Action Listener()
    
    txtNumberOfTeams = new JTextField();
    txtNumberOfTeams.addFocusListener(new FocusListener()
      {
        
        @Override
        public void focusLost(FocusEvent e)
        {
          if(txtNumberOfTeams.getText().equals("")){
            txtNumberOfTeams.setFont(new Font("Courier New", Font.ITALIC, 12));
            txtNumberOfTeams.setText("Number of Teams");       
          }//if     
        }//focusLost
        
        @Override
        public void focusGained(FocusEvent e)
        {
          if(txtNumberOfTeams.getText().equals("Number of Teams")){
            txtGameDays.setFont(new Font("Courier New", Font.PLAIN, 12));
            txtNumberOfTeams.setText("");           
          }//if
          
        }//focusGained
      });//new Focus Listener
    txtNumberOfTeams.setText("Number of Teams");
    txtNumberOfTeams.setBounds(24, 300, 200, 28);
    frame.getContentPane().add(txtNumberOfTeams);
    txtNumberOfTeams.setColumns(10);
    
    txtGameDays = new JTextField();
    txtGameDays.setFont(new Font("Arial", Font.PLAIN, 12));
    txtGameDays.addFocusListener(new FocusListener()
      {
        //Tried to experiment with fonts and how
        //to implement custom hints inside an edit
        //box
        
        @Override
        public void focusLost(FocusEvent e)
        {
          if(txtGameDays.getText().equals("")){
            txtGameDays.setFont(new Font("Courier New", Font.ITALIC, 12));
            txtGameDays.setText("Game Days");
           
          }//if
          
        }//focusLost
        
        @Override
        public void focusGained(FocusEvent e)
        {
        if(txtGameDays.getText().equals("Game Days")){
          txtGameDays.setFont(new Font("Arial", Font.PLAIN, 12));
         txtGameDays.setText("");
        }
          
        }//focusGained
      });//new FocusListener()
    txtGameDays.setText("Game Days");
    txtGameDays.setColumns(10);
    txtGameDays.setBounds(24, 340, 200, 28);
    frame.getContentPane().add(txtGameDays);
    
    Label label = new Label("Sports Scheduler");
    label.setBounds(144, 20, 200, 16);
    label.setFont(new Font("Arial", Font.BOLD, 18));
    
    frame.getContentPane().add(label);
    
    txtOutputTextFile = new JTextField("Output File Name");
    txtOutputTextFile.setColumns(10);
    txtOutputTextFile.setBounds(24, 224, 200, 32);
    txtOutputTextFile.addFocusListener(new FocusListener()
      {
        
        @Override
        public void focusLost(FocusEvent e)
        {
          if(txtOutputTextFile.getText().equals("")){
            txtOutputTextFile.setText("Output File Name");
          
        }
        }
        
        @Override
        public void focusGained(FocusEvent e)
        {
          if(txtOutputTextFile.getText().equals("Output File Name")){
            txtOutputTextFile.setText("");
          
        }
        }
      });
    
      
    frame.getContentPane().add(txtOutputTextFile);
  }//intialize()

  @Override
  public void actionPerformed(ActionEvent e)
  {
    //Use the file chooser class so that the user can easily pick files
    int val = fc.showOpenDialog(null);
    
    if (val == JFileChooser.APPROVE_OPTION && e.getSource() == conferenceButton)
      txtConferenceInfo.setText(fc.getSelectedFile().toString());
    
    if (val == JFileChooser.APPROVE_OPTION && e.getSource() == mileageButton)
      txtMilageInformationFile.setText(fc.getSelectedFile().toString());
    
    if (val == JFileChooser.APPROVE_OPTION && e.getSource() == dateButton)
      txtDateInformationFile.setText(fc.getSelectedFile().toString());

  }//actionPerformed
}//class GraphicalUI
