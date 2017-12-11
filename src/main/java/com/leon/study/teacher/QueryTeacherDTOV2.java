package com.leon.study.teacher;

import com.leon.study.utils.Versioned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryTeacherDTOV2 implements Versioned {

    private String name;

    private String title;

    @Override
    public Versioned toVersion(int version) {
        if (version <= 1) {
            QueryTeacherDTOV1 dto = new QueryTeacherDTOV1(name);
            return dto.toVersion(version);
        } else {
            return this;
        }
    }

}
