(ns clojure-app.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [config.core :refer [env]]
            [rum.core :refer [defc render-static-markup]]
            [next.jdbc :as jdbc])
  (:gen-class))

(def datasource (jdbc/get-datasource (:db env)))

(defc template [headline component]
  [:div {:id "main-div"
        :class "main-page-div"}
    [:h1 headline]
    [:ul {:class "nav"}
      [:li [:a {:href "/"} "Home"]]
      [:li [:a {:href "users"} "Users"]]]
    component])

(defc main-page [req]
  (let [result (jdbc/execute-one! datasource ["SELECT 'Hello' greeting"])]
    [:p (:greeting result)]))

(defc users-page []
  [:p "This is the users page!"])

(defroutes app
  (GET "/" [req] (render-static-markup (template "Main Page" (main-page {:request req}))))
  (GET "/users" [] (render-static-markup (template "Users" (users-page)))))

(defn -main
  [& args] 
  (run-jetty (wrap-defaults app site-defaults) {:port (:port env)}))
