package com.freshworks.SpringProject.teamMembers;

import org.mapstruct.*;

import java.lang.annotation.Target;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface TeamMemberMapper {

     TeamMembers dtoToModel(TeamMembersDto teamMembersDto);
}
