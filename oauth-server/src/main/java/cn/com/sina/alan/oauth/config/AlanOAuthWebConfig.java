package cn.com.sina.alan.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import cn.com.sina.alan.oauth.service.UserService;

/**
 * 配置用户信息，以及受保护路径、允许访问路径
 */
//@Configuration
//public class AlanOAuthWebConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    public DataSource dataSource;
//
//    @Autowired
//    private UserService userService;
//    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/favor.ico");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //        auth.inMemoryAuthentication()
//        //                .withUser("reader")
//        //                .password("reader")
//        //                .authorities("FOO_READ")
//        //                .and()
//        //                .withUser("writer")
//        //                .password("writer")
//        //                .authorities("FOO_READ", "FOO_WRITE");
//
//        //        UserDetails userDetails = userDetailsService().loadUserByUsername("reader");
//        //        System.out.println(userDetails.getPassword());
//
////        auth.jdbcAuthentication().dataSource(dataSource);
//        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
//        auth.userDetailsService(userService).passwordEncoder(md5PasswordEncoder);
////        UserDetails userDetails = userDetailsService().loadUserByUsername("reader");
////        System.out.println(userDetails.getPassword());
//    }
//
//}
