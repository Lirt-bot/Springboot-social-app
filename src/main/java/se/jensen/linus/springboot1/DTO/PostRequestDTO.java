package se.jensen.linus.springboot1.DTO;

import java.time.LocalDateTime;

public record PostRequestDTO(String text, LocalDateTime createdAt) {
}


