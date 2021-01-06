(ns clojure-app.core
  (:require [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn app-handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "<html><body><h1>Hello!</h1></body></html>"})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-jetty app-handler {:port 80})
  )
