package com.leon.study.teacher;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TeacherTitleConverter implements AttributeConverter<Title, String> {

    @Override
    public String convertToDatabaseColumn(Title title) {
        return title.getShortName();
    }

    @Override
    public Title convertToEntityAttribute(String input) {
        return Title.fromShortName(input);
    }

}
