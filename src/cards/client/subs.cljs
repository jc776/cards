(ns cards.client.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub :login
  (fn [db path]
    (:login db)))
