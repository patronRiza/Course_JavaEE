package ru.prj.config;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.prj.repositories.impl.OrderRepo;

@Configuration
public class MetricsConfig {
    @Bean
    public MeterBinder counterMeterBinder(OrderRepo orderRepo) {
        return registry ->
                Gauge.builder("count.orders", orderRepo::getCountOfOrders)
                        .register(registry);
    }

    @Bean
    public MeterBinder averageSum(OrderRepo orderRepo) {
        return registry ->
                Gauge.builder("average.sum", orderRepo::getAverage)
                        .register(registry);
    }
}
