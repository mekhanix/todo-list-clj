(defproject todo-rest "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring "1.7.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.5.0-beta1"]
                 [org.clojure/java.jdbc "0.7.8"]
                 [compojure "1.6.1"]
                 [org.xerial/sqlite-jdbc "3.25.2"]]
  :plugins [[lein-ring "0.12.5"]
            [cider/cider-nrepl "0.21.1"]]
  :ring {:handler todo-rest.core/handler}
  :main ^:skip-aot todo-rest.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
