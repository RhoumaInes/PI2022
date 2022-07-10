package tn.esprit.asi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.asi.entities.Badge;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.BadgeRepo;

@Service
public class BadgeServiceImpl implements BadgeService {
	
	@Autowired
	BadgeRepo badgeRepo;

	@Override
	public Long addBadge(Badge badge) {
		badgeRepo.save(badge);
		return badge.getId();
	}

	@Override
	public Long transformToTrophe(Badge badge) {
		badgeRepo.save(badge);
		return badge.getId();
	}

	@Override
	public Badge getBadgeByUser(User user) {
		return badgeRepo.retrieveBadgeByUser(user);
	}

}
