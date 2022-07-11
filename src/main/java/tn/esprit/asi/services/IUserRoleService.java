package tn.esprit.asi.services;

import tn.esprit.asi.entities.UserRole;

import java.util.List;

public interface IUserRoleService {
    UserRole CreateRole(UserRole userRole);

    List<UserRole> roleList();
}
