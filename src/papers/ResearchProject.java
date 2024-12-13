package papers;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import exceptions.InvalidResearchPaperException;
import exceptions.InvalidSupervisorException;
import users.Researcher;

import static enums.CitationFormat.PLAIN_TEXT;

public class ResearchProject implements Serializable {

    private String topic;
    private List<ResearchPaper> publishedPapers;
    private Researcher researchSupervisor;
    private List<Researcher> participants;

    // Default Constructor
    public ResearchProject() {
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    // Parameterized Constructor
    public ResearchProject(String topic, Researcher researchSupervisor, List<ResearchPaper> publishedPapers, List<Researcher> participants)
            throws InvalidSupervisorException, InvalidResearchPaperException {
        if (researchSupervisor.calculateHIndex() < 3) {
            throw new InvalidSupervisorException("Research supervisor must have an h-index of at least 3.");
        }
        for (ResearchPaper paper : publishedPapers) {
            boolean hasParticipantAuthor = participants.stream()
                    .anyMatch(participant -> paper.getAuthors().contains(participant));
            if (!hasParticipantAuthor) {
                throw new InvalidResearchPaperException("At least one participant must be an author of the paper.");
            }
        }
        this.topic = topic;
        this.researchSupervisor = researchSupervisor;
        this.publishedPapers = new ArrayList<>(publishedPapers);
        this.participants = new ArrayList<>(participants);
    }

    // Getters and Setters
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public void setPublishedPapers(List<ResearchPaper> publishedPapers) {
        this.publishedPapers = publishedPapers;
    }

    public Researcher getResearchSupervisor() {
        return researchSupervisor;
    }

    public void setResearchSupervisor(Researcher researchSupervisor) throws InvalidSupervisorException {
        if (researchSupervisor.calculateHIndex() < 3) {
            throw new InvalidSupervisorException("Research supervisor must have an h-index of at least 3.");
        }
        this.researchSupervisor = researchSupervisor;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Researcher> participants) {
        this.participants = participants;
    }

    // Add a participant to the project
    public void addParticipant(Researcher participant) {
        if (!participants.contains(participant)) {
            participants.add(participant);
        } else {
            throw new IllegalArgumentException("Participant already exists in the project.");
        }
    }

    // Remove a participant from the project
    public void removeParticipant(Researcher participant) {
        if (participants.contains(participant)) {
            participants.remove(participant);
        } else {
            throw new IllegalArgumentException("Participant does not exist in the project.");
        }
    }

    // Add a research paper to the project
    public void addResearchPaper(ResearchPaper paper) throws InvalidResearchPaperException {
        boolean hasParticipantAuthor = participants.stream()
                .anyMatch(participant -> paper.getAuthors().contains(participant));
        if (!hasParticipantAuthor) {
            throw new InvalidResearchPaperException("At least one participant must be an author of the paper.");
        }
        if (!publishedPapers.contains(paper)) {
            publishedPapers.add(paper);
        } else {
            throw new IllegalArgumentException("Paper already exists in the project.");
        }
    }

    // Print project details
    public void printProjectDetails() {
        System.out.println("Project Topic: " + topic);
        System.out.println("Supervisor: " + researchSupervisor);
        System.out.println("Participants: " + participants.stream().map(Researcher::toString).collect(Collectors.joining(", ")));
        System.out.println("Published Papers:");
        publishedPapers.forEach(paper -> System.out.println(" - " + paper.getTitle()));
    }

    // Print papers sorted by a given comparator
    public void printPapersSorted(Comparator<ResearchPaper> comparator) {
        publishedPapers.stream()
                .sorted(comparator)
                .forEach(paper -> System.out.println(paper.getCitation(PLAIN_TEXT)));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ResearchProject that = (ResearchProject) obj;
        return Objects.equals(topic, that.topic) &&
                Objects.equals(researchSupervisor, that.researchSupervisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, researchSupervisor);
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "topic='" + topic + '\'' +
                ", researchSupervisor=" + researchSupervisor +
                ", participants=" + participants +
                '}';
    }
}
