(ns clj-webapp.core
  (:require [clj-webapp.handlers :as handlers]))

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


(defn route-handler [req]
  (condp = (:uri req)
    "/test1" (handlers/test1-handler req)
    "/test2" (handlers/test2-handler req)
    "/test3" (handlers/handler3 req)
    (handlers/emptyhandler req)))

;; Wrap the `route-handler` in a try catch
(defn wrapping-handler [req]
  (try
    (if-let [resp (route-handler req)]
      resp
        {:status 404 :body (str "CANNOT FOIND PAGE:" (:uri req))})
    (catch Throwable e
    {:status 500 :body (apply str (interpose "\n" (.getStackTrace e)))})))
