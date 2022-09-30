

package br.com.metricminer2.examples;

import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.metric.ClassLevelMetricCalculator;
import br.com.metricminer2.metric.MethodLevelMetricCalculator;
import br.com.metricminer2.metric.java8.cc.MethodLevelCyclomaticComplexityFactory;
import br.com.metricminer2.metric.java8.methods.NumberOfMethodsFactory;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.GitRepository;

public class Example3 implements Study {

	@Override
	public void execute() {
		String outPath = "/output/dir/"; 
		
		new RepositoryMining()
			.in(GitRepository.allProjectsIn("/path/projects/"))
			.process(new MethodLevelMetricCalculator(new MethodLevelCyclomaticComplexityFactory()), new CSVFile(outPath, "cc.csv"))
			.process(new ClassLevelMetricCalculator(new NumberOfMethodsFactory()), new CSVFile(outPath, "loc.csv"))
			.mine();		
	}
}
