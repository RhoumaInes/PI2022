package tn.esprit.asi.services;

import tn.esprit.asi.entities.UserRole;

import java.util.List;

public interface IUserRoleService {
    boolean CreateRole(UserRole userRole);

    List<UserRole> roleList();
}
