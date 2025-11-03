package org.knowm.xchange.coinbasepro;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.client.ResilienceUtils;

import java.time.Duration;

public class CoinbaseProResilience {

  public static final String PUBLIC_REST_ENDPOINT_RATE_LIMITER = "publicEndpointLimit";

  public static final String PRIVATE_REST_ENDPOINT_RATE_LIMITER = "privateEndpointLimit";

  public static ResilienceRegistries createRegistries() {
    final ResilienceRegistries registries = new ResilienceRegistries();

    registries
        .rateLimiters()
        .rateLimiter(
            PUBLIC_REST_ENDPOINT_RATE_LIMITER,
            RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(10)
                .drainPermissionsOnResult(
                    e -> ResilienceUtils.matchesHttpCode(e, 429))
                .build());

    registries
        .rateLimiters()
        .rateLimiter(
            PRIVATE_REST_ENDPOINT_RATE_LIMITER,
            RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(15)
                .drainPermissionsOnResult(
                    e -> ResilienceUtils.matchesHttpCode(e, 429))
                .build());

    return registries;
  }
}
