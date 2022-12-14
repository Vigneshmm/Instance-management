package com.freshworks.SpringProject.Instances;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring"
)
public interface InstancesMapper {
    Instances dtoToModel(InstancesDto instancesDto);
}
