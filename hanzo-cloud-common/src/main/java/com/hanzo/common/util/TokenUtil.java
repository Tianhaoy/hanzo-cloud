//package com.hanzo.common.util;
//
//import com.hanzo.common.constant.AuthConstants;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//
//import java.util.Base64;
//
///**
// * @Author thy
// * @Date 2020/11/17 10:22
// * @Description:token 公共类
// */
//public class TokenUtil {
//
//    public static String BEARER = "bearer";
//    public static Integer AUTH_LENGTH = 7;
//
//    /**
//     * 获取token串
//     * @param auth token
//     * @return String
//     */
//    public static String getToken(String auth) {
//        if ((auth != null) && (auth.length() > AUTH_LENGTH)) {
//            String headStr = auth.substring(0, 6).toLowerCase();
//            if (headStr.compareTo(BEARER) == 0) {
//                auth = auth.substring(7);
//            }
//            return auth;
//        }
//        return null;
//    }
//
//    /**
//     * 获取jwt中的claims信息
//     * @param token
//     * @return claim
//     */
//    public static Claims getClaims(String token) {
//        String key = Base64.getEncoder().encodeToString(AuthConstants.SIGN_KEY.getBytes());
//        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
//        return claims;
//    }
//
//}
