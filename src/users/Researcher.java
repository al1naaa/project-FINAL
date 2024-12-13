package users;

import papers.ResearchPaper;
import papers.ResearchProject;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Researcher extends User {
    private String department;
    private boolean isProfessor;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;
    private int hIndex;
    private int publicationsCount;

    public Researcher(String userID, String name, String email, String department, boolean isProfessor) {
        super(userID, name, email); 
        this.department = department;
        this.isProfessor = isProfessor;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.hIndex = 0;
        this.publicationsCount = 0;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public boolean isProfessor() { return isProfessor; }
    public void setProfessor(boolean isProfessor) { this.isProfessor = isProfessor; }

    public int getHIndex() { return hIndex; }
    public void setHIndex(int hIndex) { this.hIndex = hIndex; }

    public void submitResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
        publicationsCount++;
        calculateHIndex();
    }

    public void removeResearchPaper(ResearchPaper paper) {
        researchPapers.remove(paper);
        publicationsCount--;
        calculateHIndex();
    }

    public void addResearchProject(ResearchProject project) {
        researchProjects.add(project);
    }

    public void removeResearchProject(ResearchProject project) {
        researchProjects.remove(project);
    }

    public void printResearchPapers() {
        for (ResearchPaper paper : researchPapers) {
            paper.printPaperDetails();
        }
    }

    public void printResearchProjects() {
        for (ResearchProject project : researchProjects) {
            project.printProjectDetails();
        }
    }

    public int calculateHIndex() {
        List<Integer> citations = new ArrayList<>();
        for (ResearchPaper paper : researchPapers) {
            citations.add(paper.getCitations());
        }
        citations.sort(Comparator.reverseOrder());

        int hIndex = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) {
                hIndex++;
            } else {
                break;
            }
        }
        return hIndex;
    }

    @Override
    public void printUserDetails() {
        super.printUserDetails(); // Call the method from User
        System.out.println("Department: " + department);
        System.out.println("Is Professor: " + (isProfessor ? "Yes" : "No"));
        System.out.println("Publications Count: " + publicationsCount);
        System.out.println("H-Index: " + hIndex);
    }
}
