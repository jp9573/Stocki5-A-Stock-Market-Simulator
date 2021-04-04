package com.csci5308.stocki5.config;

public abstract class WebSecurityConfigurerAdapterMock
{
	public abstract boolean configureGlobal();
	
	public abstract boolean passwordEncoder();
	
	public abstract boolean configure();
}
