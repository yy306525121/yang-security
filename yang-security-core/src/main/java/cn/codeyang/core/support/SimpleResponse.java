package cn.codeyang.core.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class SimpleResponse {

    private Object content;
}
