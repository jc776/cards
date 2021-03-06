(ns cards.server
  (:require [cards.server.ws :as ws]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [org.httpkit.server :as http]
            [mount.core :refer [defstate]]
            [taoensso.timbre :as t]))

(defroutes app
  (GET "/" []
    (resp/content-type (resp/resource-response "index.html" {:root "public"}) "text/html"))
  (GET "/ws" [] ws/ws-handler)
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))

(defn handler [req] (app req))

(defstate server
  :start (http/run-server #'handler {:port 8080})
  :stop (server))
