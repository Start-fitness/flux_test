package com.home.fitness.documents;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
public enum  Roles {
  ROLE_USER, ROLE_ADMIN
}
