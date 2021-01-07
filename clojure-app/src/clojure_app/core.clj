(ns clojure-app.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer :all]
            [compojure.route :as route])
  (:gen-class))

(defroutes app
  (GET "/" [] "<h1>Hello World!</h1>")
  (GET "/users" [] "<h1>There are no users!</h1>"))

; (defn app-handler [request]
;   {:status 200
;    :headers {"Content-Type" "text/html"}
;    :body "<html><body><h1>Hello!</h1></body></html>"})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-jetty app {:port 80}))
