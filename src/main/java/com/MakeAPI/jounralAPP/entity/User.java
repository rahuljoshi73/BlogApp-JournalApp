package com.MakeAPI.jounralAPP.entity;

import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Document(collection = "Users")
@Data
public class User implements UserDetails {
    @Id
    @Generated
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String email;

    private boolean sentimentAnalysis;

    @NonNull
    @Indexed(unique = true)
    private String userName;
    @NonNull
    private String password;

    @DBRef //creating reference of journalEntries in users
    private List<JournalEntry> journalEntries = new ArrayList<>();
    //it is behaving like foreign key
    private List<String> roles;
    @Indexed(name = "verification_code")
    private String verificationCode;
    @Indexed(name = "verification_expiration")
    private LocalDateTime verificationCodeExpiresAt;
    private boolean enabled;

    public User(String username, String email, String password, boolean sentimentAnalysis) {

        this.userName = username;
        this.email = email;
        this.password = password;
        this.sentimentAnalysis = sentimentAnalysis;
    }
    public User(){

    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return userName;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
