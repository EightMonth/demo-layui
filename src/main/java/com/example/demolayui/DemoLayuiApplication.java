package com.example.demolayui;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.example.demolayui.rbac.CustomUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.sql.DataSource;
import java.util.ArrayList;

@SpringBootApplication
public class DemoLayuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoLayuiApplication.class, args);
    }


    @Configuration
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomUserService userService;
        @Autowired
        private DataSource dataSource;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService);
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            // deny ?????????iframe?????????sameOrigin ???????????????iframe?????????allowfrom ???????????????????????????
            http.headers().frameOptions().sameOrigin()
                    .and().csrf().disable().authorizeRequests()
                    .antMatchers("/swagger**/**", "/**/api-docs/**", "/swagger-resources/**", "/webjars/**", "/login", "/css/**", "/js/**", "/lib/**", "/images/**", "/api/**").permitAll()
                    .anyRequest().access("@rbacService.hasPermission(request, authentication)")
                    .and().formLogin().loginPage("/login").loginProcessingUrl("/loginPost").permitAll()
                    .and().logout().logoutSuccessUrl("/login").permitAll()
                    .and().exceptionHandling().accessDeniedPage("/page/white")
                    ;
        }

    }

    @Configuration
    @EnableWebMvc
    public static class MvcConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        }
    }

    @Configuration
    @MapperScan("com.example.demolayui.mapper")
    public static class MybatisPlusConfig {

        @Bean
        public PaginationInterceptor paginationInterceptor() {
            PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
            // ??????????????????????????????????????????true???????????????false?????????????????????false
            paginationInterceptor.setOverflow(false);
            // ??????????????????
            paginationInterceptor.setLimit(500);
            // count join??????
            paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
            return paginationInterceptor;
        }

    }

    @Configuration
    @EnableOpenApi
    public static class swaggerConfig{

        @Bean
        public Docket docket(Environment env) {
            Profiles profiles = Profiles.of("dev", "test", "default");

            boolean b = env.acceptsProfiles(profiles);

            return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("group")
                    .apiInfo(apiInfo())
                    .enable(b)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com"))
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfo("????????????",
                    "?????????????????????????????????????????????",
                    "1.0",
                    "http://localhost:8080",
                    new Contact("", "", ""),
                    "",
                    "",
                    new ArrayList<>());
        }
    }
}
