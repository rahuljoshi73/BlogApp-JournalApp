package com.MakeAPI.jounralAPP.entity;

import com.MakeAPI.jounralAPP.enums.Sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Document(collection = "journalEntry")
@Data
@NoArgsConstructor//it is imp for call json pojo

public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;

}
/* *****What is lombok*****
 * lombok is a popular library n java ecosystem, often used
 * in spring boot application
 * it aims to reduce that boilerplate code that developers
 * have to write such as getters ,setters , constructor and more
 *
 *
 * lombok achieves this by generating this
 * code automatically during compilation ,
 * based on annotation you add to your java classes*/