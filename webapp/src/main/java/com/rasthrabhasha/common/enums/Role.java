package com.rasthrabhasha.common.enums;

import java.util.EnumSet;
import java.util.Set;

public enum Role {

    ADMIN(EnumSet.allOf(Permission.class)),

    STUDENT(EnumSet.of(
        Permission.VIEW_EXAMS,
        Permission.SUBMIT_APPLICATION,
        Permission.VIEW_OWN_PROFILE,
        Permission.VIEW_RESULTS,
        Permission.UPLOAD_FILES,
        Permission.EDIT_OWN_PROFILE
    )),

    EXAM_OFFICER(EnumSet.of(
        Permission.VIEW_STUDENTS,
        Permission.VIEW_EXAMS,
        Permission.VIEW_APPLICATIONS,
        Permission.MANAGE_APPLICATIONS,
        Permission.PUBLISH_RESULTS,
        Permission.VIEW_RESULTS
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }
}
