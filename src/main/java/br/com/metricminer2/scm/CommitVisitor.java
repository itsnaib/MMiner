

package br.com.metricminer2.scm;

import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.persistence.PersistenceMechanism;

public interface CommitVisitor {
	void process(SCMRepository repo, Commit commit, PersistenceMechanism writer);
	String name();
}
