package com.bookSharingPlatform.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document ( collection = "user")
public class ApplicationUser extends RootEntity {
    private String name;
    private String email;
    private List<String> contactNumber;
    private String password;
    private String accountStatus;
    private boolean isEmailVerified;
    private boolean isPhoneVerified;
}
