package de.ckonv.reactivedemo.reactivemariadb.persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table("dto")
public class DTOEntity {

  @Id private Integer id;
  private Integer o1_id;
  private String name_keys;
}
