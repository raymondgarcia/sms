package com.demo.sms.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppUser implements Serializable {
    private String name;

    private String username;

    private String password;
}
