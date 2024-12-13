package comparators;

import java.util.Comparator;
import papers.ResearchPaper;

public class DateResearchPaperComparator implements Comparator<ResearchPaper> {
	public int compare(ResearchPaper o1, ResearchPaper o2) {
		return Integer.compare(o1.getYear(), o2.getYear());
	}   
}
