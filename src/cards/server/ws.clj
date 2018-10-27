(ns cards.server.ws
  (:require [cognitect.transit :as transit]
            [org.httpkit.server :as http]
            [taoensso.timbre :as t])
  (:import [java.io ByteArrayOutputStream ByteArrayInputStream]))

;; really!?
(defn read-transit [s]
  (let [bais (ByteArrayInputStream. (.getBytes s))]
    (transit/read (transit/reader bais :json))))

(defn write-transit [s]
  (let [baos (ByteArrayOutputStream.)]
    (transit/write (transit/writer baos :json) s)
    (.toString baos)))

(defonce channels (atom {}))

(defn channel-info [channel]
  (get @channels channel))

(defn new-channel-info! []
  {:id (java.util.UUID/randomUUID)})

(defn connect! [channel]
  (let [{:keys [id] :as info} (new-channel-info!)]
    (t/info id "channel open")
    (swap! channels assoc channel info)))

(defn disconnect! [channel status]
  (let [{:keys [id]} (channel-info channel)]
    (t/info id "channel closed:" status)
    (swap! channels dissoc channel)))

(defn send-message [channel value]
  (let [{:keys [id]} (channel-info channel)
        json (write-transit value)]
    (t/info id "<=" json)
    (http/send! channel json)))

(defn receive-message [channel json]
  (let [{:keys [id]} (channel-info channel)
        value (read-transit json)]
    (t/info id "=>" value)
    (send-message channel value)))

(defn ws-handler [request]
  (http/with-channel request channel
    (connect! channel)
    (http/on-close channel #(disconnect! channel %))
    (http/on-receive channel #(#'receive-message channel %))))
