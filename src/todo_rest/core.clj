(ns todo-rest.core
  (:require
   [todo-rest.db :refer [init-db]]
   [todo-rest.handlers :as handlers]
   [compojure.core :refer :all]
   [compojure.route :as route]
   [ring.util.response :refer [response]]
   [ring.middleware.json :refer [wrap-json-response wrap-json-body]]))

(defroutes app-routes  
  (context "/todos" []
    (GET "/" [] (response (handlers/handle-get-all-todos)))
    (GET "/:id" [id] (response (handlers/handle-get-todo-by-id id)))
    (POST "/" {body :body} (response (first (handlers/handle-insert-new-todo body))))
    (DELETE "/:id" [id] (response (handlers/handle-delete-todo-by-id id)))
    (PUT "/:id" {{id :id} :params body :body} (response (handlers/handle-edit-todo-by-id id body))))
  (route/not-found "Not Found"))


(def handler
  (-> app-routes
      wrap-json-response
      wrap-json-body))
