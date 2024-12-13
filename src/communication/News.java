package communication;

import java.util.*;
import java.time.LocalDateTime;

public class News {

    private String title;
    private String content;
    private String topic;
    private List<String> comments;
    private boolean isPinned;
    private LocalDateTime timestamp;
    private String visibility;
    private int likes;
    private int dislikes;

    public News(String title, String content, String topic) {
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.comments = new ArrayList<>();
        this.isPinned = false;
        this.timestamp = LocalDateTime.now();
        this.visibility = "Public";
        this.likes = 0;
        this.dislikes = 0;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void showComments() {
        for (String comment : comments) {
            System.out.println("- " + comment);
        }
    }

    public void displayNews() {
        System.out.println("Title: " + title);
        System.out.println("Topic: " + topic);
        System.out.println("Content: " + content);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Visibility: " + visibility);
    }

    public void pinNews() {
        if (this.topic.equalsIgnoreCase("Research")) {
            this.isPinned = true;
        }
    }

    public void displayPinnedStatus() {
        if (isPinned) {
            System.out.println("This news is pinned.");
        } else {
            System.out.println("This news is not pinned.");
        }
    }

    public void likeNews() {
        this.likes++;
    }

    public void dislikeNews() {
        this.dislikes++;
    }

    public void changeVisibility(String visibility) {
        this.visibility = visibility;
    }

    public static News announceResearcherPaper(String researcherName, String paperTitle) {
        String title = "New Paper Published by " + researcherName;
        String content = researcherName + " has just published a new paper titled: '" + paperTitle + "'.";
        News researchAnnouncement = new News(title, content, "Research");
        researchAnnouncement.pinNews();
        return researchAnnouncement;
    }

    public static News announceTopCitedResearcher(String researcherName, int citationCount) {
        String title = "Top Cited Researcher: " + researcherName;
        String content = researcherName + " is the top-cited researcher with " + citationCount + " citations.";
        News topCitedResearcher = new News(title, content, "Research");
        topCitedResearcher.pinNews();
        return topCitedResearcher;
    }

    public static void displayTrendingTopics(List<News> newsList) {
        Map<String, Integer> topicCount = new HashMap<>();
        for (News news : newsList) {
            topicCount.put(news.topic, topicCount.getOrDefault(news.topic, 0) + 1);
        }

        System.out.println("Trending Topics:");
        topicCount.forEach((topic, count) -> {
            System.out.println(topic + ": " + count + " news articles");
        });
    }

    @Override
    public String toString() {
        return "communication.News: " +
                "Title = '" + title + '\'' +
                ", Topic = '" + topic + '\'' +
                ", Content = '" + content + '\'' +
                ", Timestamp = " + timestamp +
                ", Pinned = " + isPinned +
                ", Visibility = '" + visibility + '\'' +
                ", Likes = " + likes +
                ", Dislikes = " + dislikes +
                ", Comments = " + comments.size();
    }

    public String getTitle() {
        return title;
    }
}