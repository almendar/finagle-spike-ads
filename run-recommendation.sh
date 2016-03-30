#!/usr/bin/env bash
java -jar recommendation/target/scala-2.11/recommendation-assembly-1.0.0.jar \
-http.port=:2001 \
-admin.port=:2991 \
-com.twitter.finagle.zipkin.host=192.168.99.100:9410 \
-com.twitter.finagle.tracing.debugTrace=true \
-com.twitter.finagle.zipkin.initialSampleRate=1.0 -tracingEnabled=true \
$@