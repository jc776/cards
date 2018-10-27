(ns cards.client.events
  (:require [cards.client.ws :as ws]
            [re-frame.core :as rf]
            [taoensso.timbre :as t]))


(def default-db
  {})

(rf/reg-event-fx :init-db
  (fn [cofx _]
    {:db default-db
     ::ws/connect "/ws"}))

(rf/reg-event-db :error
  (fn [db [_ msg]]
    (t/error msg)
    db))

(rf/reg-event-db :login-name
  (fn [db [_ name]] (assoc-in db [:login :name] name)))

(rf/reg-event-db :login-pass
  (fn [db [_ pass]] (assoc-in db [:login :pass] pass)))

(rf/reg-event-fx :login-submit
  (fn [{db :db} _]
    (let [{{:keys [name pass]} :login} db]
      {:db (assoc-in db [:login :loading] true)
       ::ws/send [(:ws db) [:login name pass]]})))
