package tn.esprit.asi.services;


import tn.esprit.asi.entities.Badge;
import tn.esprit.asi.entities.User;

public interface BadgeService {
	Long addBadge(Badge badge);

	Long transformToTrophe(Badge badge);
	
	Badge getBadgeByUser(User user);
}
