package cn.codeyang.demo.exception;

public class UserNotExistException extends RuntimeException {
    private Long id;

    public UserNotExistException(Long id) {
        super("user is not exist");
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
