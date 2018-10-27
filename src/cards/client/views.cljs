(ns cards.client.views
  (:require [re-frame.core :as rf]))

(defn listen [query] @(rf/subscribe query))

(defn on-enter [ev-enter]
  #(case (.-which %)
      13 (rf/dispatch [ev-enter])
      nil))

(defn on-change [ev-change]
  #(rf/dispatch [ev-change (-> % .-target .-value)]))
  

(defn login [{:keys [name pass loading]}]
  [:div
    [:input {:type "text"
             :placeholder "Username"
             :auto-focus true ;; "first load" only. Refs?
             :value name
             :on-change (on-change :login-name)
             :on-key-down (on-enter :login-submit)}]
    [:input {:type "password"
             :placeholder "Password"
             :value pass
             :on-change (on-change :login-pass)
             :on-key-down (on-enter :login-submit)}]
    [:button {:on-click #(rf/dispatch [:login-submit])} "Log in"]])

(defn app []
  [login (listen [:login])])
