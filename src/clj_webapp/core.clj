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
  {:body (pr-str req)})

(defn on-init []
    (println "Initialising web app"))

(defn on-destroy []
    (println "Shutting down webapp"))
