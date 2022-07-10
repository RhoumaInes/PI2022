package tn.esprit.asi.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.asi.entities.UserRole;
import tn.esprit.asi.reposetories.UserRoleRepo;

import java.util.List;

@Service
@Slf4j
public class UserRoleService implements IUserRoleService {

    @Autowired
    UserRoleRepo userRoleRep;

    @Override
    public boolean CreateRole(UserRole userRole) {
        boolean returnedValue = true;

        try {
            userRoleRep.save(userRole);
        } catch (Exception e) {
            log.error(e.getMessage());
            returnedValue = false;
        }

        return returnedValue;
    }

    @Override
    public List<UserRole> roleList() {
        List<UserRole> reternedValue = null;
        try {
            reternedValue = (List<UserRole>) userRoleRep.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return reternedValue;
    }
}
