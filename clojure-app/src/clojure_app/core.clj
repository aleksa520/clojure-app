(ns clojure-app.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [config.core :refer [env]])
  (:gen-class))

(defroutes app
  (GET "/" [] "<h1>Hello World!</h1>")
  (GET "/users" [] "<h1>There are no users!</h1>"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-jetty (wrap-defaults app site-defaults) {:port (:port env)}))
