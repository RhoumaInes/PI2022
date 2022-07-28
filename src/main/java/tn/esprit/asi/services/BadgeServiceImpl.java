package tn.esprit.asi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;
import tn.esprit.asi.entities.Badge;
import tn.esprit.asi.entities.User;
import tn.esprit.asi.reposetories.BadgeRepo;

@Slf4j
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

	@Override
	public void deleteBadge(Long badgeId) {
		try {
			badgeRepo.deleteById(badgeId);
		}catch (Exception e) {
			log.error("error while deleting badge "+e.getMessage());
		}		
	}

}
