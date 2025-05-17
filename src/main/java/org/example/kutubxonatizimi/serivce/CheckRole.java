package org.example.kutubxonatizimi.serivce;

import org.example.kutubxonatizimi.entities.Roles;
import org.example.kutubxonatizimi.entities.Users;


public class CheckRole {
    public static void requireAdminOrOperator(Users users) {
        if(users.getRoles() != Roles.ADMIN && users.getRoles() != Roles.OPERATOR) {
            throw new SecurityException("Access denied");
        }
    }
    public static void requireAdmin(Users users) {
        if(users.getRoles() != Roles.ADMIN) {
            throw new SecurityException("Admin access needed");
        }
    }

}
