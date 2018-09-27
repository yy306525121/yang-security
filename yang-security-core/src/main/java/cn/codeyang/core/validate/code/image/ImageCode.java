package cn.codeyang.core.validate.code.image;

import cn.codeyang.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode extends ValidateCode {

    private static final long serialVersionUID = -54355899183593678L;

    public ImageCode(BufferedImage image, String code, int expireInSecond) {
        super(code, expireInSecond);
        this.image = image;
    }
    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    private transient BufferedImage image;


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
