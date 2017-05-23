package org.jboss.vertx.perf.harness;

import org.jboss.vertx.perf.driver.PacketDistributionDriver;

import com.sun.faban.driver.BenchmarkDefinition;
import com.sun.faban.driver.CycleType;
import com.sun.faban.driver.FixedTime;

@BenchmarkDefinition (
    name      = "Vertx Uniform Operation Benchmark",
    version   = "0.1",
    drivers   = {
        PacketDistributionDriver.class
        },
    scaleName = "IR",
    metric    = "metric"
)

@FixedTime(cycleType = CycleType.CYCLETIME, cycleTime=100, cycleDeviation = 2)

public class VertxBenchmark {

  public VertxBenchmark()  throws Exception {}

}
