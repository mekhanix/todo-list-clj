(ns todo-rest.handlers
  (:require 
   [todo-rest.db :refer :all]
   [compojure.route :refer [not-found]]))

(defn handle-get-all-todos []
  (let [result (get-all-todos)]
    result))

(defn handle-get-todo-by-id [id]
  (let [result (get-todo-by-id id)]
    (if (nil? result) (not-found "Not Found")
        result)))

(defn handle-insert-new-todo [payloads]
  (if (nil? payloads) (not-found "Not Found")
      (insert-new-todo payloads)))

(defn handle-delete-todo-by-id [id]
  {:id (first (delete-todo-by-id id))})

(defn handle-edit-todo-by-id [id payloads]
  (edit-todo-by-id id payloads))