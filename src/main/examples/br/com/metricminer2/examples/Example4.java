

package br.com.metricminer2.examples;

import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.persistence.PersistenceMechanism;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.CommitVisitor;
import br.com.metricminer2.scm.GitRepository;
import br.com.metricminer2.scm.SCMRepository;
import br.com.metricminer2.scm.commitrange.Commits;

public class Example4 implements Study {

	@Override
	public void execute() {
		PersistenceMechanism pm = new CSVFile("/path/to/file.csv");
		
		new RepositoryMining()
			.in(GitRepository.allProjectsIn("/path/to/projects"))
			.through(Commits.all())
			.process(new CommitVisitor() {
				
				@Override
				public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
					writer.write(commit.getHash(), commit.getMsg());
				}
				
				@Override
				public String name() {
					return "commit messages";
				}
			}, pm)
			.mine();
		
	}
}
