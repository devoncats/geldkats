package com.devoncats.tracker.mapper.interfaces;

public interface Mapper<Entity, Dto> {

        Dto toDto(Entity entity);

        Entity toEntity(Dto dto);

}
