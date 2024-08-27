package com.trung.projectmanagementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
    @Autowired
    protected ModelMapper modelMapper;

    public <TYPE1, TYPE2> List<TYPE2> convertObjectToObject(final TYPE1 type1, final Class<TYPE2> type2Class) {
        return modelMapper.map(type1, type2Class);
    }

    public <TYPE1, TYPE2> List<TYPE2> convertListObjectToLiStObject(final List<TYPE1> type1List,
            final Class<TYPE2> type2Class) {
        return type1List.stream()
                .map(type1 -> converObjectToObject(type1, type2class))
                .collect(Collectors.toList());
    }

    protected <TYPE1, TYPE2> Page<TYPE2> convertObjectPageToObjectPage(final List<TYPE1> type1List,
            final Class<TYPE2> type2Class) {
        List<TYPE1> type1List = type1Page.getContent();
        List<TYPE2> type2List = convertListObjectToListObject(type1List, type2Class);
        return new PageImpl<>(type2List, pageable, type1Page.getTotalElemets());
    }

}
