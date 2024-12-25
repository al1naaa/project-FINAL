package patterns;

import users.Teacher;

public interface RatingStrategy {
	void rate(Teacher teacher, double score);
}
