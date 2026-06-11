package net.berkaygunduz.spring7restmvc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID id;

    @Version
    private Integer version;

    @NotNull
    @NotBlank
    private String customerName;

    @Column(length = 255)
    private String email;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
