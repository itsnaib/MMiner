
package br.com.metricminer2.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Commit {

	private String hash;
	private Developer author;
	private Developer committer;
	private String msg;
	private List<Modification> modifications;
	private String parent;
	private Calendar date;
	private Set<String> branches;
	private boolean merge;
	private boolean inMainBranch;

	public Commit(String hash, Developer author, Developer committer, Calendar date, String msg, String parent) {
		this(hash, author, committer, date, msg, parent, false, new HashSet<>(), false);
	}

	public Commit(String hash, Developer author, Developer committer, Calendar date, String msg, String parent, boolean merge, Set<String> branches, boolean isCommitInMainBranch) {
		this.hash = hash;
		this.author = author;
		this.committer = committer;
		this.date = date;
		this.msg = msg;
		this.parent = parent;
		this.merge = merge;
		this.modifications = new ArrayList<Modification>();
		this.branches = branches;
		this.inMainBranch = isCommitInMainBranch;
	}

	public boolean isMerge() {
		return merge;
	}
	
	public String getHash() {
		return hash;
	}

	public Developer getAuthor() {
		return author;
	}

	public String getMsg() {
		return msg;
	}
	
	public Developer getCommitter() {
		return committer;
	}

	public String getParent() {
		return parent;
	}
	
	public void addModification(String oldPath, String newPath, ModificationType change, String diff, String sc) {
		Modification m = new Modification(oldPath, newPath, change, diff, sc);
		modifications.add(m);
		
	}
	
	public void addModifications(List<Modification> modifications) {
		this.modifications.addAll(modifications);
	}
	
	public List<Modification> getModifications() {
		return Collections.unmodifiableList(modifications);
	}

	@Override
	public String toString() {
		return "Commit [hash=" + hash + ", parent=" + parent + ", author=" + author + ", msg=" + msg + ", modifications="
				+ modifications + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Commit)) {
			return false;
		} else if (other == this) {
			return true;
		} else {
			Commit c = (Commit) other;
			return this.getHash().equals(c.getHash());
		}
	}
	
	public Calendar getDate() {
		return date;
	}

	public Set<String> getBranches() {
		return Collections.unmodifiableSet(branches);
	}
	
	public boolean isInMainBranch() {
		return inMainBranch;
	}

}
