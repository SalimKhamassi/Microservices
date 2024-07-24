package com.example.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Event {
    @Id
    private String id;
    private String title;
    private Long userId;


}
