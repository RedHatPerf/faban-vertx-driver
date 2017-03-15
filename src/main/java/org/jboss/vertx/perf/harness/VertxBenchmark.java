package org.jboss.vertx.perf.harness;

import org.jboss.vertx.perf.driver.VertxDriver;

import com.sun.faban.driver.BenchmarkDefinition;

@BenchmarkDefinition (
    name      = "Vertx Benchmark",
    version   = "0.1",
    drivers   = {
        VertxDriver.class
        },
    scaleName = "IR",
    metric    = "metric"
)

public class VertxBenchmark {

  public VertxBenchmark()  throws Exception {}

}
