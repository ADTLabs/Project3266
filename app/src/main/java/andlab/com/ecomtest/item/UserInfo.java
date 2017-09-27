package andlab.com.ecomtest.item;

/**
 * Created by Ravi Prakash on 23-07-2017.
 */

public class UserInfo {
    String UserId;
    String UserName;
    String UserEmail;
    String Mobile;

    public UserInfo(String userId, String userName, String userEmail, String mobile) {
        UserId = userId;
        UserName = userName;
        UserEmail = userEmail;
        Mobile = mobile;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
