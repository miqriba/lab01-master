architecture-beta
    group api(server)[Spring Boot Server]
    group local(database)[Local]
    

    service db(database)[H2] in local
    service disk1(test)[Test] in api
    service server1(server)[Service] in api
    service model(server)[Model] in api
    service server2(server)[Repository] in api

    model:B -- L:server1
    disk1:T -- B:server1
    server1:R -- L:server2
    server2:R -- L:db
