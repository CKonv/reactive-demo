package de.ckonv.reactivedemo.reactivemariadb;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ObjectDTO {

  private Integer o1_id;
  private List<String> name_keys;
}
