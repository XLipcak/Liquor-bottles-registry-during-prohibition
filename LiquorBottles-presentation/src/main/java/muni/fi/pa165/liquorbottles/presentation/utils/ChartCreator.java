package muni.fi.pa165.liquorbottles.presentation.utils;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class ChartCreator {

    private static final String RELATIVE_IMAGE_FILE_PATH1 = "target/LiquorBottles-presentation-1.0-SNAPSHOT/images/chart.jpg";
    private static final String RELATIVE_IMAGE_FILE_PATH2 = "src/main/webapp/images/chart.jpg";
    
    public static void createPieChart(int toxic, int unchecked, int nonToxic) {

        //Prepare Chart
        JFreeChart chart = preparePieChart(toxic, unchecked, nonToxic);
        
        //Create path to images directory
        String path1 = "";
        String path2 = "";
        try {
            path1 = ChartCreator.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().toString();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ChartCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        int index = path1.indexOf("LiquorBottles-presentation") + 27;
        path1 = new String(path1.substring(0, /*path.length() +*/ index));
        
        path2 = path1.concat(RELATIVE_IMAGE_FILE_PATH2);
        path1 = path1.concat(RELATIVE_IMAGE_FILE_PATH1);
        
        
        File file1 = new File(path1);
        File file2 = new File(path2);
        

        try {
            ChartUtilities.saveChartAsJPEG(file1, chart, 1140, 300);
            ChartUtilities.saveChartAsJPEG(file2, chart, 1140, 300);
        } catch (IOException ex) {
            Logger.getLogger(ChartCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static JFreeChart preparePieChart(int toxic, int unchecked, int nonToxic) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        if (toxic > 0) {
            pieDataset.setValue("TOXIC = " + toxic, toxic);
        }

        if (unchecked > 0) {
            pieDataset.setValue("UNCHECKED = " + unchecked, unchecked);
        }

        if (nonToxic > 0) {
            pieDataset.setValue("NON_TOXIC = " + nonToxic, nonToxic);
        }

        JFreeChart chart = ChartFactory.createPieChart("Štatistika", pieDataset, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);

        return chart;
    }

}
