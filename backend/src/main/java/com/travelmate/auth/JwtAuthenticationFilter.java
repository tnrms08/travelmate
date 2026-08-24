package com.travelmate.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        //HTTP 요청의 Authorization 헤더 가져오기
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);

            //JWT 검증
            if(jwtTokenProvider.validateToken(token)){
                String loginId = jwtTokenProvider.getLoginId(token);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                loginId,
                                null,   //비밀번호(JWT 이미 검증했기 때문에 필요 없음)
                                null             //권한(임시로 null로 설정)
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                System.out.println("인증된 사용자: " + loginId);
            }
        }

        filterChain.doFilter(request, response);
    }
}
