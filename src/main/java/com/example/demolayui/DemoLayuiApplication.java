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
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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

            // deny 不允许iframe嵌入、sameOrigin 只允许本站iframe嵌入，allowfrom 只能被指定站点嵌入
            http.headers().frameOptions().sameOrigin()
                    .and().csrf().disable().authorizeRequests()
                    .antMatchers("/swagger**/**", "/**/api-docs/**", "/swagger-resources/**", "/webjars/**", "/login", "/css/**", "/js/**", "/lib/**", "images/**", "/api/**").permitAll()
                    .anyRequest().access("@rbacService.hasPermission(request, authentication)")
                    .and().formLogin().loginPage("/login").loginProcessingUrl("/loginPost").failureForwardUrl("/login?error").permitAll()
                    .and().logout().logoutSuccessUrl("/login").permitAll()
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
            // 设置请求页面大于最后页操作，true回到首页，false继续请求，默认false
            paginationInterceptor.setOverflow(false);
            // 单页最大上限
            paginationInterceptor.setLimit(500);
            // count join优化
            paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
            return paginationInterceptor;
        }

//        @Bean
//        public MetaObjectHandler metaObjectHandler() {
//            return new MetaObjectHandler() {
//                @Override
//                public void insertFill(MetaObject metaObject) {
//                    this.setFieldValByName("createTime", new Date(), metaObject);
//                    this.setFieldValByName("updateTime", new Date(), metaObject);
//                }
//
//                @Override
//                public void updateFill(MetaObject metaObject) {
//                    this.setFieldValByName("updateTime", new Date(), metaObject);
//                }
//            };
//        }
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
            return new ApiInfo("接口文档",
                    "用于前端开发人员对接的接口文档",
                    "1.0",
                    "http://localhost:8080",
                    new Contact("", "", ""),
                    "",
                    "",
                    new ArrayList<>());
        }
    }
}
