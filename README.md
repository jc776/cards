## Dev
```bash
$ npm install
# terminal 1
$ npx shadow-cljs server
# terminal 2 or your editor
$ lein repl :connect 3333
# build cljs
user=> (shadow/watch :app)
# start server (localhost:8080)
user=> (mount/start)
# e.g. cljs repl, once browser is open
user=> (shadow/nrepl-select :app)
cljs.user=> (js/alert "Hello!")
```
