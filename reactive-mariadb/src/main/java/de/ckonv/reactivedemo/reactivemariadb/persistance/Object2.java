package de.ckonv.reactivedemo.reactivemariadb.persistance;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@RequiredArgsConstructor
@Table("object2")
public class Object2 {
  @Id private Integer id;
  @NonNull private String name_key;
}
