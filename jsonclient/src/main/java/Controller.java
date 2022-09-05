// Controller.java - Controller
//    Handles user interaction with listeners.
//    Calls View and Model as needed.
// Fred Swartz -- December 2004

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {
    //... The Controller needs to interact with both the Model and View.
    private Model m_model;
    private View m_view;
    private JSONRPCClient jsonrpcClient = new JSONRPCClient();
    
    //========================================================== constructor
    /** Constructor */
    Controller(Model model, View view) {
        m_model = model;
        m_view  = view;
        
        //... Add listeners to the view.
        view.addCreateCharDataListener(new CreateCharDataListener());
        view.addProgramHourListener(new ProgramHoursListener());
        view.addAverageEnergyConsumption(new AverageEnergyListener());

        //view.addClearListener(new ClearListener());
    }
    
    
    ////////////////////////////////////////// inner class MultiplyListener
    /** When a mulitplication is requested.
     *  1. Get the user input number from the View.
     *  2. Call the model to mulitply by this number.
     *  3. Get the result from the Model.
     *  4. Tell the View to display the result.
     * If there was an error, tell the View to display it.
     */
    class CreateCharDataListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput = "";
            try {
                userInput = m_view.getUserInput();
                m_model.getChartData(userInput);
                System.out.println("Days introduced: " + m_model.getChartDays());

                //System.out.println("Result returned as: " + );

                RecordDTO[] recordDTOList = (RecordDTO[]) jsonrpcClient.getRecords(m_model.getChartDays());
                /*
                String showStuff = "";
                for(RecordDTO aux : recordDTOList) {
                    showStuff += aux.getId() + " ";
                }
                System.out.println("Stuff: " + showStuff);
                */
                BarChart ex = new BarChart(recordDTOList);
                ex.setVisible(true);

            } catch (NumberFormatException nfex) {
                m_view.showError("Data introduced is not a number: '" + userInput + "'");
            }
        }
    }//end inner class MultiplyListener

    class AverageEnergyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput = "";
            Double baseline = 0.0;

            //receive data from database
            //baseline = m_model.calculateBaseline();
            //baseline = (Double) jsonrpcClient.getBaseline();
            RecordDTOBaseline[] recordDTOList = (RecordDTOBaseline[]) jsonrpcClient.getBaselineGraph();
            BaselineChart ex = new BaselineChart(recordDTOList);
            ex.setVisible(true);

            //show calculated data
            m_view.showAverageBaseline("Calculated Baseline is: " + baseline);
        }
    }//end inner class MultiplyListener

    class ProgramHoursListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput = "";
            try {
                userInput = m_view.getUserInputProgramHours();
                m_model.getChartDataProgram(userInput);

                RecordDTO[] recordDTOList = (RecordDTO[]) jsonrpcClient.getRecordsByProgram(m_model.getChartDays());

                BarChart ex = new BarChart(recordDTOList);
                ex.setVisible(true);

            } catch (NumberFormatException nfex) {
                m_view.showError("Bad input: '" + userInput + "'");
            }
        }
    }//end inner class MultiplyListener


    //////////////////////////////////////////// inner class ClearListener
    /**  1. Reset model.
     *   2. Reset View.
     */    
    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            m_model.reset();
            m_view.reset();
        }
    }// end inner class ClearListener
}
