package com.leon.study.teacher;

import com.leon.study.utils.Versioned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryTeacherDTOV1 implements Versioned {

    private String name;

    @Override
    public Versioned toVersion(int version) {
        return this;
    }

}
