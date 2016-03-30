#!/usr/bin/env bash
java -jar products/target/scala-2.11/products-assembly-1.0.0.jar \
-http.port=:3001 \
-admin.port=:3991 \
-com.twitter.finagle.zipkin.host=192.168.99.100:9410 \
-com.twitter.finagle.tracing.debugTrace=true \
-com.twitter.finagle.zipkin.initialSampleRate=1.0 -tracingEnabled=true \
$@