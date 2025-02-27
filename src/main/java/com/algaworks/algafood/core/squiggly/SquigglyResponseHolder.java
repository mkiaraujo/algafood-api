//package com.algaworks.algafood.core.squiggly;
//
//import jakarta.servlet.http.HttpServletResponse;
//import net.jcip.annotations.ThreadSafe;
//
//
///**
// * Provides a thread-local for holding a servlet response.
// */
//@ThreadSafe
//public class SquigglyResponseHolder {
//    private static final ThreadLocal<HttpServletResponse> HOLDER = new ThreadLocal<>();
//
//    public static HttpServletResponse getResponse() {
//        return HOLDER.get();
//    }
//
//    public static void setResponse(HttpServletResponse response) {
//        HOLDER.set(response);
//    }
//
//    public static void removeResponse() {
//        HOLDER.remove();
//    }
//}
