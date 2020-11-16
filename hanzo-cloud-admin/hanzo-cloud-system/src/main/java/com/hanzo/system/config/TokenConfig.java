//package com.hanzo.system.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
///**
// * @Author thy
// * @Date 2020/11/15 17:03
// * @Description:
// */
//@Configuration
//public class TokenConfig {
//
//    @Bean
//    public TokenStore tokenStore() {
//       return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    /**
//     * Jwt资源令牌转换器
//     * 配置参数access_token.store-jwt为true时调用
//     * @return
//     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter
//                .getAccessTokenConverter();
//        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
//        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
//        //设置一个,多个会出现意想不到的问题 access_token将解析错误
//        jwtAccessTokenConverter.setSigningKey("hanzo>_<cloud");//对称秘钥，资源服务器使用该秘钥来验证
//        return jwtAccessTokenConverter;
//    }
//}
