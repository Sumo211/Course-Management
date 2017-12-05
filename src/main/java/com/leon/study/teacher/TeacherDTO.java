package com.leon.study.teacher;

import com.leon.study.utils.IsCorrectName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
class TeacherDTO {

    //@Null(message = "Id does not mandatory", groups = TeacherDTO.New.class)
    //@NotNull(message = "Id cannot be null", groups = TeacherDTO.Existing.class)
    //private Long id;

    @IsCorrectName(min = 5, max = 30, groups = {TeacherDTO.New.class, TeacherDTO.Existing.class})
    private String name;

    @Length(min = 2, max = 2, message = "Title must be short name", groups = {TeacherDTO.New.class, TeacherDTO.Existing.class})
    private String title;

    interface New {
    }

    interface Existing {

    }

}
