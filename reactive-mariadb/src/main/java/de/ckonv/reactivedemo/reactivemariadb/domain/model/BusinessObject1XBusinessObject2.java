package de.ckonv.reactivedemo.reactivemariadb.domain.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("business_object_1_x_business_object_2")
public class BusinessObject1XBusinessObject2 {

  @Id private Integer id;
  @NonNull private Integer o1_id;
  @NonNull private Integer o2_id;
  @NonNull private LocalDateTime createdAt;
  @NonNull private LocalDateTime changedAt;
}
