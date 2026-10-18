package com.example.studentmanagement.auth;

public record AuthResponse(String token, String tokenType, String message) {
    public AuthResponse(String token, String message) { this(token, "Bearer", message); }
}
