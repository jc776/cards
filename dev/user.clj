(ns user
  (:require [cards.server]
            [mount.core :as mount :refer [defstate]]
            [shadow.cljs.devtools.server :as server]
            [shadow.cljs.devtools.api :as shadow]
            [taoensso.timbre :as t]))
(defn reset
  "proto-repl tns/reset is incompatible with shadow-cljs"
  []
  (t/info "reset is disabled"))

(defstate cljs :start (shadow/watch :app)
               :stop (shadow/stop-worker :app))

;; (mount/start)
