package com.csci5308.stocki5.config;

public interface IWebMvcConfigurer
{
	public boolean propertySourcesPlaceholderConfigurer();

	public boolean resolver();

	public boolean addViewControllers();
}
