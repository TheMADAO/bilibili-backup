package top.ybgnb.bilibili.backup.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description
 * @Author hzhilong
 * @Time 2024/9/22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String uid;
    private String cookie;
    private String bili_jct;

    public User(String cookie) {
        int tempIndex = cookie.indexOf("DedeUserID=");
        this.uid = cookie.substring(tempIndex + 11, tempIndex + 11 + cookie.substring(tempIndex + 11).indexOf(";"));
        this.cookie = cookie;
        tempIndex = cookie.lastIndexOf("bili_jct=");
        this.bili_jct = cookie.substring(tempIndex + 9, tempIndex + 41);
    }

}
