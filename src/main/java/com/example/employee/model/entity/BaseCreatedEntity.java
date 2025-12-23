package com.example.employee.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

@Getter
@Setter
@ToString
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseCreatedEntity {
    @JsonProperty ( access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    @Column(name =" CREATE_AT" ,nullable = false)
    private Date createdDate;
    @JsonProperty (access = JsonProperty.Access.READ_ONLY)
    @CreatedBy
    @Column(name ="CREARE_BY" , nullable = false)
    private String createdBy;

}
