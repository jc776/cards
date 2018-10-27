(ns user
  (:require [cards.server]
            [mount.core :as mount :refer [defstate]]
            [shadow.cljs.devtools.api :as shadow]
            [taoensso.timbre :as t]))
(defn reset
  "proto-repl tns/reset is incompatible with shadow-cljs"
  []
  (t/info "reset is disabled"))

;; (mount/start)
;; (shadow/watch :app)
