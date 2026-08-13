package com.travelmate.auth;

import com.travelmate.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    //jwt 유효시간을 1시간으로 설정
    private final long expiration = 1000L * 60 * 60;

    //jwt.sectret 값 가져와서 저장
    @Value("${jwt.secret}")
    private String secret;

    public SecretKey getSecretKey(){
        //YAML에서 가져온 String 형태의 Secret을 JWT에서 사용할 수 있는 SecretKey 객체로 변환
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String createToken(User user){
        Date now = new Date();
        Date expire = new Date(now.getTime()+expiration);

        return Jwts.builder()                   //Jwt Builder 객체 생성
                .subject(user.getLoginId())     //사용자 식별 정보 넣기(Login ID) -> sub
                .issuedAt(now)                  //발급시간 -> iat
                .expiration(expire)             //만료시간 -> exp
                .signWith(getSecretKey())       //SecretKey로 서명(전자서명 붙이기)
                .compact();                     //최종 문자열로 생성
    }

    //JWT 정상 여부 확인
    public boolean validateToken(String token){
        try {
            Jwts.parser()                       //JWT 분석 준비
                    .verifyWith(getSecretKey()) //SecretKey로 서명 검증
                    .build()                    //JWT 구조 및 Claims(JWT Payload에 들어있는 데이터-sub,iat,exp) 파싱
                    .parseSignedClaims(token);  //만료시간 확인
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //JWT loginId 꺼내기
    public String getLoginId(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()       //Claims 가져오기
                .getSubject();      //Subject로 저장했던 값 가져오기
    }
}