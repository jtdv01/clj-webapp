(ns clj-webapp.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
;;
;; Request handlers
;; :uri :host :headers :remote-addr :body
;;
(defn file-handler [req]
    {:body (java.io.File. "test.txt")
     :status 500})

(defn cookie-handler [req]
    {:headers {"Location" "http://github.com"
        "Set-cookie" "monster=32"}
        :status 500
    })

(defn example-handler [req]
  (file-handler req) 
  (cookie-handler req))
  ;;  (default-handler req))
  ;;{:body "Hello clojure"})

(defn default-handler [req]
  {:body (pr-str req)})

(defn uri-handler [{:keys [uri] :as req}]
  {:body (str "URI is:" uri)} )


(defn on-init []
    (println "Initialising web app"))

(defn on-destroy []
    (println "Shutting down webapp"))

(defn test1-handler [req]
  {:body "test1"})

(defn test2-handler [req]
  {:status 301 :headers {"Location" "http://github.com"}})

(defn route-handler [req]
  (condp = (:uri req)
    "/test1" (test1-handler req)
    "/test2" (test2-handler req)))
