(defproject cards "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 ;; server
                 [http-kit "2.3.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 ;; shared
                 [com.taoensso/timbre "4.10.0"]
                 [mount "0.1.14"]]
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [;; client
                                  [thheller/shadow-cljs "2.6.19"]
                                  [reagent "0.8.1"]
                                  [re-frame "0.10.6"]
                                  ;; dev
                                  [proto-repl "0.3.1"]
                                  [com.cemerick/pomegranate "1.1.0"]]}})
