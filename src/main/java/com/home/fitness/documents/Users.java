package com.home.fitness.documents;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Document(collection = "Users")
@Data
public class Users {
        @Id
        @Field("id")
        private String id;
        @Size(min=4, max=30)
        private String login;
        private String password;
        @NotNull
        private Date createdDate = new Date();
}
