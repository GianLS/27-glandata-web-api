package br.com.glandata.web.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NavigationSession {
	
	@Getter
	@Setter
	private Boolean darkMode = false;

	@Getter
	@Setter
	private Boolean menuHide = false;
	
	@Getter
	@Setter
	private String textoSession;
}
