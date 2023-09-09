package com.task.irose.facet;

public interface Converter<DTO,MODEL>
{

   MODEL dtoToModel(DTO dto);

   DTO modelToDto(MODEL model);
}
