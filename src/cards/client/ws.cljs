(ns cards.client.ws
  (:require [cognitect.transit :as transit]
            [re-frame.core :as rf]
            [taoensso.timbre :as t]))

;; no chord, no async

(defn read-transit [s]
  (transit/read (transit/reader :json) s))

(defn write-transit [s]
  (transit/write (transit/writer :json) s))

(rf/reg-fx ::send
  (fn [[websocket value]]
    (if websocket
      (let [json (write-transit value)]
        (t/info "=>" json)
        (.send websocket json))
      (t/error "No websocket connected =>" value))))

(rf/reg-event-db ::on-open
  (fn [db [_ ws]]
    (t/info "Websocket connected.")
    (assoc db :ws ws)))

(rf/reg-event-db ::on-close
  (fn [db _]
    (t/info "Websocket closed.")
    (dissoc db :ws)))

(defn on-ws-message [msg]
  (let [json (.-data msg)
        value (read-transit json)]
    (t/info "<=" value)
    (rf/dispatch [::on-message value])))

(defn on-open [ws]
  (fn [e]
    (rf/dispatch [::on-open ws])))

(defn on-close [e]
  (rf/dispatch [::on-close]))

(defn on-error [e]
  ;; event?
  (t/error "Websocket error" e))

(defn ws-url [url]
  (let [location (.-location js/window)
        websocket-protocol (if (= (.-protocol location) "https:") "wss:" "ws:")]
    (str websocket-protocol "//" (.-host location) url)))

(rf/reg-fx ::connect
  (fn [path]
    (let [ws-url (ws-url path)
          ws (js/WebSocket. ws-url)]
      (t/info "Connect to" path)
      (aset ws "onmessage" #(#'on-ws-message %))
      (aset ws "onopen" (on-open ws))
      (aset ws "onclose" on-close)
      (aset ws "onerror" on-error))))

(rf/reg-fx ::disconnect
  (fn [[ws]]
     (.close ws)))
