package de.ckonv.reactivedemo.reactivemariadb.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("business_object_1_x_business_object_2")
public class BusinessObject1XBusinessObject2 {

  @Id private Integer id;
  @NonNull private Integer o1_id;
  @NonNull private Integer o2_id;

  @Column("createdAt")
  private LocalDateTime createdAt;

  @Column("changedAt")
  private LocalDateTime changedAt;
}
