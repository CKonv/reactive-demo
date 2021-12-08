package de.ckonv.reactivedemo.reactivemariadb.domain.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("business_object_2")
public class BusinessObject2 {

  @Id private Integer id;
  @NonNull private String nameKey;
  @NonNull private LocalDateTime createdAt;
  @NonNull private LocalDateTime changedAt;
  private String description;
}
