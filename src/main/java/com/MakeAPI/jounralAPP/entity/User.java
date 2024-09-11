package com.MakeAPI.jounralAPP.entity;

import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "Users")
@Data
public class User {
    @Id
    @Generated
    private ObjectId id;

    private String email;
    private boolean sentimentAnalysis;

    @NonNull
    private String userName;
    @NonNull
    private String password;

    @DBRef //creating reference of journalEntries in users
    private List<JournalEntry> journalEntries = new ArrayList<>();
    //it is behaving like foreign key
    private List<String> roles;


}
