package com.home.fitness.documents;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import org.apache.catalina.connector.Response;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;


@Document(collection = "Users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class User {

        private static final long serialVersionUID = 1L;
        @Id
        @Field("id")
        private String id;
        @Indexed(unique = true, direction = IndexDirection.DESCENDING)
        private String email;
        private String password;
        private boolean status;
        private List<Role> roles;
        @NotNull
        private Date createdDate = new Date();
}




        class ErrorResponse extends Response {
                private String errorMessage;

                public ErrorResponse(String errorMessage) {
                        this.errorMessage = errorMessage;
                }

                @JsonGetter("error_message")
                public String getErrorMessage() {
                        return errorMessage;
                }

                @JsonSetter("error_message")
                public void setErrorMessage(String errorMessage) {
                        this.errorMessage = errorMessage;
                }
}
