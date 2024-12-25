package papers;

import java.util.Comparator;

public interface Researcher {
    void conductResearch();
    void publishPaper();
    void attendConference();
    String getResearchField();
    void printPapers(Comparator<ResearchPaper> comparator);
    int calculateHIndex();
}

