package reaml;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

    public String getName() {
        return "myrealm1";
    }


    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        if(!"zhang".equals(username)) {
            System.out.println("userName wrong");
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            System.out.println("passWord wrong");
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}