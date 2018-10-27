(ns cards.client
  (:require [taoensso.timbre :as t]))

(defn render []
  (println "render"))

(defn ^:export main []
  (t/info "Hello")
  ;; init
  (render))

(defn ^:dev/after-load reload []
  (t/info "Reload")
  ;; clear cache
  (render))
