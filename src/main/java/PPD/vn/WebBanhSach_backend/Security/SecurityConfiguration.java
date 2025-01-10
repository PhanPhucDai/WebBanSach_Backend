package PPD.vn.WebBanhSach_backend.Security;

import PPD.vn.WebBanhSach_backend.Fillter.JWTfillter;
import PPD.vn.WebBanhSach_backend.Service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private JWTfillter jwTfillter;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //cấu hình mã hóa mật khẩu
    @Bean
    public DaoAuthenticationProvider authenticationProvider(NguoiDungService userService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
       security.authorizeHttpRequests(
               configurer->configurer
                       .requestMatchers(HttpMethod.GET, Endpoints.PUBLIC_GET_ENPOINT).permitAll()
                       .requestMatchers(HttpMethod.POST,Endpoints.PUBLIC_POST_ENPOINT_DangKi).permitAll()
                       .requestMatchers(HttpMethod.POST,Endpoints.ADMIN_POST_ENPOINT_DangKi).hasAuthority("Admin")


       );
       security.cors(
               cors->{
                   cors.configurationSource(
                           request -> {
                               CorsConfiguration corsConfig= new CorsConfiguration();
                               corsConfig.addAllowedOrigin(Endpoints.frontEnd_host);
                               corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                               corsConfig.addAllowedHeader("*");
                               return corsConfig;
                           });
               });
//       security.addFilterBefore(jwTfillter, UsernamePasswordAuthenticationFilter.class);
       security.sessionManagement((s)->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       security.httpBasic(Customizer.withDefaults());
       security.csrf(csrf -> csrf.disable());
       return  security.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
