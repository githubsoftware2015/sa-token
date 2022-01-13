package com.common.sso;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.sso.SaSsoHandle;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.common.utils.security.Md5Utils;
import com.dongao.system.domain.SysUser;
import com.dongao.system.service.ISysUserService;
import com.ejlchina.okhttps.OkHttps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Sa-Token-SSO Server端 Controller 
 * @author kong
 *
 */
@RestController
public class SsoServerController {

	@Autowired
	private ISysUserService sysUserService;

	// 配置SSO相关参数
	@Autowired
	private void configSso(SaTokenConfig cfg) {

		// 配置：未登录时返回的View
		cfg.sso.setNotLoginView(() -> {
			return new ModelAndView("sa-login.html");
		});

		// 配置：登录处理函数
		cfg.sso.setDoLoginHandle((name, pwd) -> {
			// 获取用户信息
			SysUser user = sysUserService.selectUserByLoginName(name);
			if (user != null) {
				String encryptedPassword = "";
				if(user.getSalt() != null){
					encryptedPassword = Md5Utils.hash(name + pwd + user.getSalt());
				}else{
					encryptedPassword = Md5Utils.hash(name + pwd);
				}
				if (user.getPassword() != null && encryptedPassword.equals(user.getPassword())) {
					StpUtil.login(user.getUserId(),false);
					return SaResult.ok("登录成功！").setData(StpUtil.getTokenValue());
				}else {
					return SaResult.error("用户名或密码错误！");
				}
			}else {
				return SaResult.error("未查询到当前用户信息！");
			}
		});

		// 配置 Http 请求处理器 （在模式三的单点注销功能下用到，如不需要可以注释掉）
		cfg.sso.setSendHttp(url -> {
			return OkHttps.sync(url).get().getBody().toString();
		});
	}
	/*
	 * SSO-Server端：处理所有SSO相关请求 
	 * 		http://{host}:{port}/sso/auth			-- 单点登录授权地址，接受参数：redirect=授权重定向地址 
	 * 		http://{host}:{port}/sso/doLogin		-- 账号密码登录接口，接受参数：name、pwd 
	 * 		http://{host}:{port}/sso/checkTicket	-- Ticket校验接口（isHttp=true时打开），接受参数：ticket=ticket码、ssoLogoutCall=单点注销回调地址 [可选] 
	 * 		http://{host}:{port}/sso/logout			-- 单点注销地址（isSlo=true时打开），接受参数：loginId=账号id、secretkey=接口调用秘钥 
	 */
	@RequestMapping("/login")
	public Object ssoRequest() {
		return SaSsoHandle.ssoAuth();
	}

	// SSO-Server：RestAPI 登录接口
	@RequestMapping("/sso/doLogin")
	public Object ssoDoLogin() {
		return SaSsoHandle.ssoDoLogin();
	}

	// SSO-Server：校验ticket 获取账号id
	@RequestMapping("/sso/checkTicket")
	public Object ssoCheckTicket() {
		return SaSsoHandle.ssoCheckTicket();
	}

	// SSO-Server：单点注销
	@RequestMapping("/sso/logout")
	public Object ssoLogout() {
		return SaSsoHandle.ssoServerLogout();
	}

}
