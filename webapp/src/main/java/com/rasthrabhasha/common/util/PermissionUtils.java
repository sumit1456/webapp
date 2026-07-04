package com.rasthrabhasha.common.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

import com.rasthrabhasha.common.enums.Permission;
import com.rasthrabhasha.common.enums.Role;

public class PermissionUtils {

    public static ResponseEntity<String> checkPermission(Permission permission) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"message\": \"Not authenticated\"}");
        }
        String role = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .map(r -> r.replace("ROLE_", ""))
                .orElse("");
        if (role.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"message\": \"No role found\"}");
        }
        try {
            Role roleEnum = Role.valueOf(role);
            if (!roleEnum.hasPermission(permission)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("{\"message\": \"Insufficient permissions: " + permission.name() + "\"}");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"message\": \"Invalid role: " + role + "\"}");
        }
        return null;
    }
}
