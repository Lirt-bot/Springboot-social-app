package se.jensen.linus.springboot1.DTO;

public record UserRequestDTO(String username,
                             String email,
                             String password,
                             String role,
                             String displayName,
                             String bio,
                             String profileImagePath) {
}
