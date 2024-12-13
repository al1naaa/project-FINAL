package comparators;

import java.util.Comparator;
import papers.ResearchPaper;

public class CitationsResearchPaperComparator implements Comparator<ResearchPaper> {

	public int compare(ResearchPaper o1, ResearchPaper o2) {
		return Integer.compare(o1.getCitations(), o2.getCitations());
	}

}
