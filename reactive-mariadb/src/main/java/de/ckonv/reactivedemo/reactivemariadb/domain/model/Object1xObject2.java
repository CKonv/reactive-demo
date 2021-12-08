package de.ckonv.reactivedemo.reactivemariadb.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Object1xObject2 {

  @NonNull Integer businessObject1Id;
  @NonNull List<String> businessObject2NameKeys;
}
