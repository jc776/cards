# Cards
- http-kit server
- re-frame client, a single "username/pass" screen
- websocket connection, send/receive `[:login "name" "pass"]`

## Dev
```bash
$ npm install
# terminal 1
$ npx shadow-cljs server
# terminal 2 or your editor
$ lein repl :connect 3333
user=> (mount/start)
# - cards.server/server - server on port 8080
# - user/cljs - reload frontend on save
# e.g. cljs repl, once browser is open
user=> (shadow/nrepl-select :app)
cljs.user=> (js/alert "Hello!")
```
