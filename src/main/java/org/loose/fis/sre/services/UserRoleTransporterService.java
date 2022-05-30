package org.loose.fis.sre.services;

public class UserRoleTransporterService {
    private static boolean roleAvailable = false;

    private static String role;

    public static boolean isRoleAvailable() {
        return roleAvailable;
    }

    public static void setRole(String role) {
        UserRoleTransporterService.role = role;
        roleAvailable = true;
    }

    public static String getRole() {
        roleAvailable = false;
        return role;
    }
}
