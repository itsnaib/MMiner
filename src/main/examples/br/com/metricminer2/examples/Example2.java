

package br.com.metricminer2.examples;

import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.metric.MethodLevelMetricCalculator;
import br.com.metricminer2.metric.java8.cc.MethodLevelCyclomaticComplexityFactory;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.GitRepository;
import br.com.metricminer2.scm.commitrange.Commits;

public class Example2 implements Study {

	@Override
	public void execute() {
		
		new RepositoryMining()
		.in(GitRepository.singleProject("/path/to/single/project"))
		.through(Commits.onlyInHead())
		.process(new MethodLevelMetricCalculator(new MethodLevelCyclomaticComplexityFactory()), 
				new CSVFile("/absolute/path/file.csv"))
		.mine();
		
	}
}
