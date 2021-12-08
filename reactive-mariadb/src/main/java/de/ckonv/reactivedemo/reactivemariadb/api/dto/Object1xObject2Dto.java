package de.ckonv.reactivedemo.reactivemariadb.api.dto;

import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
public class Object1xObject2Dto {

  @NonNull Integer businessObject1Id;
  @NonNull List<String> nameKeys;
}
