package com.thetechmaddy.authservice.models;

public class RequestContextHolder {

    private static final ThreadLocal<RequestContext> CONTEXT_HOLDER = new ThreadLocal<>();

    public static RequestContext getContext() {
        return CONTEXT_HOLDER.get();
    }

    public static void setContext(RequestContext requestContext) {
        CONTEXT_HOLDER.set(requestContext);
    }

    public static void clearContext() {
        CONTEXT_HOLDER.remove();
    }

}
