package com.george.demo.springdemo.springdemo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.george.demo.springdemo.springdemo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, COURSE_READ, COURSE_WRITE)),
    TEACHER(Sets.newHashSet(STUDENT_READ, STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
