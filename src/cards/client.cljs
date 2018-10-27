(ns cards.client
  (:require
    [cards.client.events] ;; rf register
    [cards.client.subs] ;; rf register
    [cards.client.views :as views]
    [reagent.core :as r]
    [re-frame.core :as rf]
    [taoensso.timbre :as t]))

(defn render []
  (r/render [views/app] (.getElementById js/document "app")))

(defn ^:export main []
  (t/info "Hello")
  (rf/dispatch [:init-db])
  (render))


(defn ^:dev/after-load reload []
  (t/info "Reload")
  ;; clear cache
  (render))
