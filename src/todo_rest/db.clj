(ns todo-rest.db
  (:require [clojure.java.jdbc :as j]
            [ring.util.response :refer [not-found]]))

(def db-spec
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/database.db"})

(def todos-table-ddl
  (fn []
      (j/create-table-ddl :todos
                          [[:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
                           [:title :varchar]
                           [:description :varchar]
                           [:state :varchar]
                           [:created_at :datetime "DEFAULT CURRENT_TIMESTAMP"]
                           [:updated_at :datetime "DEFAULT CURRENT_TIMESTAMP"]])))

(defn insert-new-todo [payloads]
  (j/insert! db-spec :todos payloads))

(defn get-all-todos []
    (j/query db-spec ["SELECT * FROM todos ORDER BY created_at DESC"]))

(defn get-todo-by-id [id]
    (j/get-by-id db-spec :todos id))

(defn delete-todo-by-id [id]
  (j/delete! db-spec :todos ["id = ?" id]))

(defn edit-todo-by-id [id payloads]
  (let [title (get payloads "title")
        description (get payloads "description")
        state (get payloads "state")]
    (j/update! db-spec :todos {:title title
                               :description description
                               :state state
                               :updated_at (java.time.LocalDateTime/now)}
               ["id = ?" id])))

(defn init-db []
  (try
    (j/db-do-commands db-spec
                      [(todos-table-ddl)])
    (catch Exception e
      (println (.getMessage e)))))