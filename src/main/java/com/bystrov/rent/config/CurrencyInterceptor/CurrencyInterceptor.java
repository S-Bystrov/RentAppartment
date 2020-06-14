package com.bystrov.rent.config.CurrencyInterceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CurrencyInterceptor extends HandlerInterceptorAdapter {

    public static final String DEFAULT_PARAM_NAME = "currency";
    protected final Log logger = LogFactory.getLog(this.getClass());
    private String paramName = "currency";
    @Nullable
    private String[] httpMethods;
    private boolean ignoreInvalidCurrency = false;

    public CurrencyInterceptor() {
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return this.paramName;
    }

    public void setHttpMethods(@Nullable String... httpMethods) {
        this.httpMethods = httpMethods;
    }

    @Nullable
    public String[] getHttpMethods() {
        return this.httpMethods;
    }

    public void setIgnoreInvalidCurrency(boolean ignoreInvalidCurrency) {
        this.ignoreInvalidCurrency = ignoreInvalidCurrency;
    }

    public boolean isIgnoreInvalidCurrency() {
        return this.ignoreInvalidCurrency;
    }
}
