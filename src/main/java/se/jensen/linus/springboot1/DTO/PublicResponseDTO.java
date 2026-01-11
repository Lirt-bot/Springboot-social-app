package se.jensen.linus.springboot1.DTO;

public record PublicResponseDTO(Long id,
                                String username,
                                String email,
                                String role,
                                String displayName,
                                String bio,
                                String profileImagePath) {
}
