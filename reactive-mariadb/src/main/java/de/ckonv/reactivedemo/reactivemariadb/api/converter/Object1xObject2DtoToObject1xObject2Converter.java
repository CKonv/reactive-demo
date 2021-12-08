package de.ckonv.reactivedemo.reactivemariadb.api.converter;

import de.ckonv.reactivedemo.reactivemariadb.api.dto.Object1xObject2Dto;
import de.ckonv.reactivedemo.reactivemariadb.domain.model.Object1xObject2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class Object1xObject2DtoToObject1xObject2Converter
    implements Converter<Object1xObject2Dto, Object1xObject2> {

  @Override
  public Object1xObject2 convert(Object1xObject2Dto source) {

    return Object1xObject2.builder()
        .businessObject1Id(source.getBusinessObject1Id())
        .businessObject2NameKeys(source.getNameKeys())
        .build();
  }
}
