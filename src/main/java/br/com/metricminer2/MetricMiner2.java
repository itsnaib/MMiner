

package br.com.metricminer2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class MetricMiner2 {

	public static void main(String[] args) {
		System.out.println("You should not run me! :/");
	}
	
	private static Logger log = Logger.getLogger(MetricMiner2.class);

	public void start(Study study) {

		try {
			
			Calendar startDate = Calendar.getInstance();
			
			log.info("# -------------------------------------------------- #");
			log.info("#                   MetricMiner                      #");
			log.info("#                      v2.0                          #");
			log.info("#             www.metricminer.org.br                 #");
			log.info("# -------------------------------------------------- #");
			log.info("Starting engine: " + new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(startDate.getTime()));
			
			try {
				study.execute();
			} catch (Throwable t) {
				log.error("some study error came to me", t);
			}
			
			Calendar finishDate = Calendar.getInstance();
			log.info("Finished: " + new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(finishDate.getTime()));
			long seconds = (finishDate.getTimeInMillis() - startDate.getTimeInMillis())/1000;
			log.info("It took " + seconds + " seconds (~" + seconds/60 + " minutes).");
			log.info("Brought to you by MetricMiner2 (metricminer.org.br)");
			log.info("# -------------------------------------------------- #");
			
		} catch(Throwable ex) {
			log.error("Some error ocurred", ex);
		}
	}


}
