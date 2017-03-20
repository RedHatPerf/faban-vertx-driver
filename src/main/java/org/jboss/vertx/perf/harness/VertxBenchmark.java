package org.jboss.vertx.perf.harness;

import org.jboss.vertx.perf.driver.Bucket01;
import org.jboss.vertx.perf.driver.Bucket02;

import com.sun.faban.driver.BenchmarkDefinition;
import com.sun.faban.driver.CycleType;
import com.sun.faban.driver.FixedTime;
import com.sun.faban.driver.MatrixMix;
import com.sun.faban.driver.Row;

@BenchmarkDefinition (
    name      = "Vertx Benchmark",
    version   = "0.1",
    drivers   = {
          Bucket01.class, Bucket02.class
        },
    scaleName = "IR",
    metric    = "metric"
)


@MatrixMix(operations = { "postBucket01" }, mix = { @Row({ 100 }) }, deviation = 2)

@FixedTime(cycleType = CycleType.CYCLETIME, cycleTime=100, cycleDeviation = 2)


public class VertxBenchmark {

  public VertxBenchmark()  throws Exception {}

}
