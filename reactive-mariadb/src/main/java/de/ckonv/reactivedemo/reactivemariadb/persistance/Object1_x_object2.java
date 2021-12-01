package de.ckonv.reactivedemo.reactivemariadb.persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table("object1_x_object2")
public class Object1_x_object2 {
  @Id private Integer id;
  private Integer o1_id;
  private Integer o2_id;
}
