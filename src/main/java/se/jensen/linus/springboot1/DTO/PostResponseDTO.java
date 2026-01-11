package se.jensen.linus.springboot1.DTO;

import java.time.LocalDateTime;

public record PostResponseDTO(Long Long_id, String text, LocalDateTime createdAt) {

}
