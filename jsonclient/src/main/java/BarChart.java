import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class BarChart extends JFrame {

    private String hourlyData = "Energy Consumption";
    private RecordDTO[] recordDTOS = null;

    public BarChart() {
        initUI();
    }

    public BarChart(RecordDTO[] recordDTOS) {
        this.recordDTOS = recordDTOS;
        initUI();
    }

    private void initUI() {

        // send database data
        CategoryDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Charted Energy Data");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
    }

    private CategoryDataset createDataset() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(recordDTOS != null) {
            for (RecordDTO aux : recordDTOS) {
                if (aux.getDate() != null)
                    dataset.setValue(aux.getRecordedValue(), hourlyData, aux.getDate());
            }
        }
        if(recordDTOS == null) {
            dataset.setValue(46, hourlyData, "USA");
            dataset.setValue(38, hourlyData, "China");
            dataset.setValue(29, hourlyData, "UK");
            dataset.setValue(22, hourlyData, "Russia");
            dataset.setValue(13, hourlyData, "South Korea");
            dataset.setValue(11, hourlyData, "Germany");
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Historical Energy Consumption Data",
                "",
                hourlyData,
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }
}
