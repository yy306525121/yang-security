package cn.codeyang.core.properties;

import lombok.Data;

@Data
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    int width = 67;
    int height = 23;
}
