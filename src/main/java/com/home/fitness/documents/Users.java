package com.home.fitness.documents;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Document(collection = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
        @Id
        @Field("id")
        private String id;
        @Indexed(unique = true, direction = IndexDirection.DESCENDING)
        private String email;
        private String password;
        private boolean status;
        @NotNull
        private Date createdDate = new Date();
        @DBRef
        private Set<Roles> roles;

        public void setRoles(Set<Roles> roles) {
                this.roles = roles;
        }
}
