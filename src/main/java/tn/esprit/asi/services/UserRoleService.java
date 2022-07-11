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
    public UserRole CreateRole(UserRole userRole) {
        return userRoleRep.save(userRole);
    }

    @Override
    public List<UserRole> roleList() {
        return (List<UserRole>) userRoleRep.findAll();
    }
}
