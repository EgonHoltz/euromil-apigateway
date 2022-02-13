package pt.utad.egonh.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	private final String CREDIBANK_URL = "http://localhost:8002";
	private final String EUROMIL_URL = "http://localhost:8001";
	
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/credibank/**")
						.filters(f -> f.rewritePath("/credibank(?<segment>/?.*)", "$\\{segment}"))
						.uri(CREDIBANK_URL))
				.route(p -> p
						.path("/euromil/**")
						.filters(f -> f.rewritePath("/euromil(?<segment>/?.*)", "$\\{segment}"))
						.uri(EUROMIL_URL))
				.build();
	}
	
	

}
