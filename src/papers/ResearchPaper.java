package papers;

import java.io.Serializable;
import java.util.*;

import enums.CitationFormat;

public class ResearchPaper implements Comparable<ResearchPaper>, Serializable {

    private String doi;
    private String title;
    private List<String> authors;
    private List<String> pages;
    private int year;
    private int citations;
    private String journal;

    // Default Constructor
    public ResearchPaper() {
        this.doi = UUID.randomUUID().toString();
        this.citations = 0;
    }

    // Parameterized Constructor
    public ResearchPaper(String title, List<String> authors, List<String> pages, int year, String journal) {
        this();
        this.title = title;
        this.authors = authors;
        this.pages = pages;
        this.year = year;
        this.journal = journal;
    }

    // Getters and Setters
    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCitations() {
        return citations;
    }

    public void setCitations(int citations) {
        this.citations = citations;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    // Generate citation in the specified format
    public String getCitation(CitationFormat format) {
        if (format == CitationFormat.BIBTEX) {
            return "@article{\n  title={" + title + "},\n  author={" + String.join(" and ", authors) + "},\n  journal={" + journal + "},\n  year={" + year + "},\n  doi={" + doi + "}\n}";
        } else {
            return title + " by " + String.join(", ", authors) + ", published in " + journal + " (" + year + "), DOI: " + doi + ", cited " + citations + " times.";
        }
    }

    // Read content of all pages
    public String read() {
        return String.join("\n\n", pages);
    }

    public void printPaperDetails() {
        System.out.println("Title: " + title);
        System.out.println("Authors: " + String.join(", ", authors));
        System.out.println("Journal: " + journal);
        System.out.println("Year: " + year);
        System.out.println("Citations: " + citations);
        System.out.println("DOI: " + doi);
        System.out.println("Pages: \n" + read());
    }

    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(this.citations, other.citations);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ResearchPaper that = (ResearchPaper) obj;
        return Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doi);
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "title='" + title + '\'' +
                ", authors=" + String.join(", ", authors) +
                ", year=" + year +
                ", journal='" + journal + '\'' +
                '}';
    }
}
