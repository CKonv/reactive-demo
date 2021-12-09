package de.ckonv.reactivedemo.reactivemariadb.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("business_object_2")
public class BusinessObject2 {

  @Id private Integer id;

  @NonNull
  @Column("nameKey")
  private String nameKey;

  @NonNull
  @Column("createdAt")
  private LocalDateTime createdAt;

  @NonNull
  @Column("changedAt")
  private LocalDateTime changedAt;

  private String description;
}
