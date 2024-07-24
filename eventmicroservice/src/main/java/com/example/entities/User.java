package com.example.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class User {
    @Id
    private Long id;
    private String name;
}