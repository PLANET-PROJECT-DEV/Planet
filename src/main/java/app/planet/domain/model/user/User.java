package app.planet.domain.model.user;

import app.planet.domain.exception.InvalidUserInfoException;
import app.planet.utils.Randoms;

import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;

public class User {

    private Long id;
    private String email;
    private String password;
    private String nickName;
    private String icon;
    private int cronies;
    private Gender gender;
    private OffsetDateTime birthday;
    private String city;
    private String introduce;
    private OffsetDateTime createTime;
    private OffsetDateTime updateTime;

    public static final String PASSWORD_REGEX = "^(?![a-zA-Z]+$)(?!\\d+$)(?![^\\da-zA-Z\s]+$).{6,12}$";
    public static final String NICKNAME_REGEX = "^[\\u4e00-\\u9fa5a-zA-Z0-9]{2,12}$";

    public enum Gender {
        MALE, FEMALE, UNKNOWN
    }

    public User(UserInfo userInfo) throws InvalidUserInfoException {
        if (!userInfo.isValid()) {
            throw new InvalidUserInfoException();
        }
        this.email = userInfo.email();
        this.password = "";
        this.nickName = "用户" + Randoms.aRandomText(10);
        this.icon = "1.jpg";
        this.cronies = 0;
        this.gender = Gender.UNKNOWN;
        this.birthday = null;
        this.city = null;
        this.introduce = null;
        this.createTime = now();
        this.updateTime = this.createTime;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public String getIcon() {
        return icon;
    }

    public int getCronies() {
        return cronies;
    }

    public Gender getGender() {
        return gender;
    }

    public OffsetDateTime getBirthday() {
        return birthday;
    }

    public String getCity() {
        return city;
    }

    public String getIntroduce() {
        return introduce;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }


}
