package org.example.pojo.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTeskPar {

    private String userId;

    private Integer userLevel;

    private Integer userCredit;

}
