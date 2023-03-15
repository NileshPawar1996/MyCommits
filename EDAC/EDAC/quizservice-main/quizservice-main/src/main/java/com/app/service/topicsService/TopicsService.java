//CREATED BY RAJVARDHAN SHINDE
package com.app.service.topicsService;

import java.util.List;

import com.app.pojos.Topic;

public interface TopicsService {
	List<Topic> getAllTopicsByModule(Long moduleId);
	void addTopic(Topic topic);
	void editTopic(Topic topic);
	void deleteTopic(Long id);
}
