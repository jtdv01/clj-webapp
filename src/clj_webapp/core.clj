(ns clj-webapp.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
;;
;; Request handlers
;; :uri :host :headers :remote-addr :body
;;
(defn example-handler [req]
  {:body "Hello clojure"})

(defn uri-handler [{:keys [uri] :as req}]
  {:body (str "URI is:" uri)} )

(defn file-handler [req]
    {:body (java.io.File ".gitignore")
    :status 500})

(defn cookie-handler [req]
    {:headers {"Location" "http://github.com"
        "Set-cookie" "test=1"}
        :status 500
    })
(defn on-init []
    (println "Initialising web app"))

(defn on-destroy []
    (println "Shutting down webapp"))
