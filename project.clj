(defproject clj-webapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.6.3"]]

  :plugins [[lein-ring "0.12.4"]] 

  :ring {
         :handler clj-webapp.core/full-handler
         ;;:handler clj-webapp.core/wrapping-handler
         ;;:handler clj-webapp.core/route-handler
         :init clj-webapp.core/on-init
         :port 4001
         :destroy clj-webapp.core/on-destroy
         }
  )
