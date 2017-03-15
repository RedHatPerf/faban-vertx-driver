# Faban driver for Vertx Perf Server

## Overview

A faban driver implementation that can push load at a Vertx application.

## How to build deploy and run the benchmark

### Configure benchmark

Create a new file called gradle.properties using the sample file 'gradle.sample.properties'

Next step is to update the property values in the new file.

### Build benchmark

./gradlew harnessJar

### Deploy benchmark

 Before deploying Start the Faban Harness.

 Next you can deploy the benchmark driver like so

./gradlew faban.harness.deploy

### Run Benchmark

 Using fabancli tool start a benchmark run.
 The webui currently is not implemented.