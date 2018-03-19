package cn.com.sina.alan.oauth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableResourceServer
public class OAuthConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
    public DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    
	private TokenExtractor tokenExtractor = new BearerTokenExtractor();

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.addFilterAfter(new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {

				// We don't want to allow access to a resource with no token so clear
				// the security context in case it is actually an OAuth2Authentication
				if (tokenExtractor.extract(request) == null) {
					SecurityContextHolder.clearContext();
				}

				filterChain.doFilter(request, response);
			}
		}, AbstractPreAuthenticatedProcessingFilter.class);

		http.csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/service2").hasAuthority("ROLE_MOBILE");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/service22").hasAuthority("ROLE_UNITY");
//		http.authorizeRequests().anyRequest().authenticated();
	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer config) {
		config.tokenServices(tokenServices());
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

}
