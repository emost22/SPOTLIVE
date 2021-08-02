package com.ssafy.spotlive.config;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

/**
 * @FileName : CustomOAuthProvider
 * @작성자 : 김민권
 * @Class 설명 : OAuth2를 위한 KAKAO API 설정 관련 값이 나열된 ENUM 클래스
 */
public enum CustomOAuthProvider {

    KAKAO {
        @Override
        public ClientRegistration.Builder getBuilder() {
            return getBuilder("kakao", ClientAuthenticationMethod.POST)
                    .scope("profile", "talk_message")
                    .authorizationUri("https://kauth.kakao.com/oauth/authorize")
                    .tokenUri("https://kauth.kakao.com/oauth/token")
                    .userInfoUri("https://kapi.kakao.com/v2/user/me")
                    .clientId(CustomOAuthProvider.CLIENT_KEY)
                    .clientSecret(CustomOAuthProvider.CLIENT_SECRET)
                    .userNameAttributeName("id")
                    .clientName("Kakao");
        }
    };

    private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";
    private static final String CLIENT_KEY = "68c5cb3f22fe800fc5606851a8bed982";
    private static final String CLIENT_SECRET = "GDOD65MV18g96VrQ4LtAlmKiA27S223S";

    public abstract ClientRegistration.Builder getBuilder();

    protected ClientRegistration.Builder getBuilder(String registrationId, ClientAuthenticationMethod method) {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUriTemplate(CustomOAuthProvider.DEFAULT_LOGIN_REDIRECT_URL);

        return builder;
    }
}
