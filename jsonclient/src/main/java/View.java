// View.java - View component
//    Presentation only.  No user actions.
// Fred Swartz -- December 2004

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class View extends JFrame {
    //... Components
    private JTextField m_userInputTf = new JTextField(5);
    private JTextField m_userInputProgramHours = new JTextField(5);
    private JTextField m_totalTf     = new JTextField(5);
    private JButton    m_multiplyBtn = new JButton("Enter");
    private JButton    m_inputProgramHoursBtn = new JButton("Enter");
    private JButton    m_averageEnergyBtn = new JButton("Calculate");
    
    private Model m_model;
    
    //======================================================= constructor
    /** Constructor */
    View(Model model) {
        //... Set up the logic
        m_model = model;
        m_model.setValue(Model.INITIAL_VALUE);
        
        //... Initialize components
        m_totalTf.setText(m_model.getValue());
        m_totalTf.setEditable(false);
        
        //... Layout the components.      
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(0,1));

        JPanel content2 = new JPanel();
        content2.setLayout(new FlowLayout());
        content2.add(new JLabel("Select Days for Chart View: "));
        content2.add(m_userInputTf);
        content2.add(m_multiplyBtn);

        JPanel content3 = new JPanel();
        content3.add(new JLabel("Calculate BaseLine(Average Energy Consumption over 7 days): "));
        content3.add(m_averageEnergyBtn);

        JPanel content4 = new JPanel();
        content4.add(new JLabel("Select Program Duration(Hours): "));
        content4.add(m_userInputProgramHours);
        content4.add(m_inputProgramHoursBtn);

        content.add(content2);
        content.add(content3);
        content.add(content4);
        
        //... finalize layout
        this.setPreferredSize(new Dimension(600,300));
        this.setContentPane(content);
        this.pack();
        this.setResizable(false);
        this.setTitle("Client Assignment 3");
        // The window closing event should probably be passed to the 
        // Controller in a real program, but this is a short example.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    void reset() {
        m_totalTf.setText(Model.INITIAL_VALUE);
    }
    
    String getUserInput() {
        return m_userInputTf.getText();
    }

    String getUserInputProgramHours() {
        return m_userInputProgramHours.getText();
    }
    
    void setTotal(String newTotal) {
        m_totalTf.setText(newTotal);
    }
    
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

    void showAverageBaseline(String message) {JOptionPane.showMessageDialog(this, message);}
    
    void addCreateCharDataListener(ActionListener mal) {
        m_multiplyBtn.addActionListener(mal);
    }

    void addProgramHourListener(ActionListener mal) {
        m_inputProgramHoursBtn.addActionListener(mal);
    }

    void addAverageEnergyConsumption(ActionListener mal) {
        m_averageEnergyBtn.addActionListener(mal);
    }

    void addClearListener(ActionListener cal) {
        //m_averageEnergyBtn.addActionListener(cal);
    }
}
