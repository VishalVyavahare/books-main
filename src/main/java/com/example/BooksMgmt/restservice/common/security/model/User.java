package com.example.BooksMgmt.restservice.common.security.model;

import com.example.BooksMgmt.restservice.common.constants.DBConstants;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "User", schema = "books_lib", catalog = "books_lib")
@Where(clause = "is_deleted = 0")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(generator = "platform-uuid")
    @GenericGenerator(name = "platform-uuid",
        strategy = "com.example.BooksMgmt.restservice.common.validation.PlatformUUIDGenerator")
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user")
    private String user;

    @Column(name = "password")
    private String password;

    @Transient
    private String token;

    @Column(name = "creation_date")
    @CreatedDate
    private Instant createTimestamp;

    @Column(name = "update_date")
    @LastModifiedDate
    private Instant updateTimestamp;

    @Column(name = "is_deleted")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Builder.Default
    private Boolean deleted = DBConstants.DELETED_FALSE;
    ;

//    @Column(name = "created_by")
//    @CreatedBy
//    private String createdBy;
//
//    @Column(name = "updated_by")
//    @LastModifiedBy
//    private String updatedBy;

}
