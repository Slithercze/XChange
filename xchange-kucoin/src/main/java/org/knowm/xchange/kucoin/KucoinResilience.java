package org.knowm.xchange.kucoin;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import java.time.Duration;

import javax.ws.rs.core.Response;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.client.ResilienceUtils;

public class KucoinResilience {

  public static final String PUBLIC_REST_ENDPOINT_RATE_LIMITER = "publicEndpointLimit";

  public static final String PRIVATE_REST_ENDPOINT_RATE_LIMITER = "privateEndpointLimit";

  public static ResilienceRegistries createRegistries() {
    final ResilienceRegistries registries = new ResilienceRegistries();

    registries
        .rateLimiters()
        .rateLimiter(
            PUBLIC_REST_ENDPOINT_RATE_LIMITER,
            RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                .limitRefreshPeriod(Duration.ofSeconds(10))
                .limitForPeriod(30)
                .drainPermissionsOnResult(
                    e -> ResilienceUtils.matchesHttpCode(e, 429))
                .build());

    registries
        .rateLimiters()
        .rateLimiter(
            PRIVATE_REST_ENDPOINT_RATE_LIMITER,
            RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                .limitRefreshPeriod(Duration.ofSeconds(3))
                .limitForPeriod(30)
                .drainPermissionsOnResult(
                    e -> ResilienceUtils.matchesHttpCode(e, 429))
                .build());

    return registries;
  }
}
