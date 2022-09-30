

package br.com.metricminer2.examples;

import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.metric.ClassLevelMetricCalculator;
import br.com.metricminer2.metric.java8.cc.ClassLevelCyclomaticComplexityFactory;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.GitRepository;
import br.com.metricminer2.scm.commitrange.Commits;

public class Example1 implements Study {

	@Override
	public void execute() {
		
		new RepositoryMining()
		.in(GitRepository.allProjectsIn("/some/path/to/many/projects/"))
		.through(Commits.all())
		.process(new ClassLevelMetricCalculator(new ClassLevelCyclomaticComplexityFactory()), 
				new CSVFile("/outputdir/", "file.csv"))
		.mine();
	}
}
