package com.jushi.web.routers;

import com.jushi.api.routers.BaseRouters;
import com.jushi.web.handler.ArticleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author 80795
 * @date 2019/6/23 21:20
 */
@Configuration
public class ArticleRouters extends BaseRouters<ArticleHandler> {
    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction(ArticleHandler articleHandler) {
        RouterFunction<ServerResponse> route = RouterFunctions.route(
                RequestPredicates.GET("/queryPageByPlate"),
                articleHandler::articleQueryPageByPlate)
                .andRoute(
                        RequestPredicates.GET("/queryPageByPlate/SSE"),
                        articleHandler::articleQueryPageByPlateSSE
                )
                .andRoute(
                        RequestPredicates.GET("/search"),
                        articleHandler::articleSearchQueryPage
                )
                .andRoute(
                        RequestPredicates.GET("/search/SSE"),
                        articleHandler::articleSearchQueryPageSSE
                );
        return RouterFunctions.nest(
                //相当于类上面的@RequestMapping
                RequestPredicates.path("/article"),
                baseRoute(route,articleHandler)
        );
    }
}