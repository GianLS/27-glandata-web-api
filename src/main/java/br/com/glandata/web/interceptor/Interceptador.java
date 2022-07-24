package br.com.glandata.web.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import br.com.glandata.web.model.Acesso;

public class Interceptador implements HandlerInterceptor {

	public static List<Acesso> acessos = new ArrayList<Acesso>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Acesso acesso = new Acesso();
		acesso.setPath(request.getRequestURI());
		acesso.setData(LocalDateTime.now());

		request.setAttribute("acesso", acesso);

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		Acesso acesso = (Acesso) request.getAttribute("acesso");
		acesso.setDuracao(Duration.between(acesso.getData(), LocalDateTime.now()));
		acessos.add(acesso);
	}
}
