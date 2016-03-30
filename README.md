### Create binaries

`sbt assembly`

### Start recommendation
`./run-recommendation.sh`

### Start product service
`./run-products.sh -http.port=:3001 -admin.port=:3991`

`./run-products.sh -http.port=:3002 -admin.port=:3992`

`./run-products.sh -http.port=:3003 -admin.port=:3993`

### Start user profile service

`./run-user-profiles.sh -http.port=:4001 -admin.port=:4991`

`./run-user-profiles.sh -http.port=:4002 -admin.port=:4992`

`./run-user-profiles.sh -http.port=:4003 -admin.port=:4993`


### Zipkin

Best way it to use docker for this.
Clone [this](https://github.com/openzipkin/docker-zipkin.git)

Invoke `docker-compose up` in cloned repo.

`run-*.sh` scripts are configured to report to Zipkin.
The web UI is served at port `:8080`.


### Test it
`curl -v -H"x-auth: 222" 127.0.0.1:2001/recommend/32/12`



