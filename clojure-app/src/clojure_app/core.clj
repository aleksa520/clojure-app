(ns clojure-app.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [config.core :refer [env]]
            [rum.core :refer [defc render-static-markup]])
  (:gen-class))

(defc main-page []
  [:div {:id "main-div"
        :class "main-page-div"}
    [:h1 "This is main page"]])

(defc users-page[]
  [:div {:id "main-div"
          :class "main-page-div"}
    [:h1 "This is users page"]])

(defroutes app
  (GET "/" [] (render-static-markup (main-page)))
  (GET "/users" [] (render-static-markup (users-page))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-jetty (wrap-defaults app site-defaults) {:port (:port env)}))
