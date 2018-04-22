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


(defn on-init [& x]
    (println "Initialising web app"))

(defn on-destroy [& x]
    (println "Shutting down webapp"))


(defn route-handler [req]
  (condp = (:uri req)
    "/test1" (handlers/test1-handler req)
    "/test2" (handlers/test2-handler req)
    "/test3" (handlers/handler3 req)
    nil))
    ;;(handlers/emptyhandler req)))




;; Wrap the `route-handler` in a try catch
(defn wrapping-handler [req]
  (try
    (if-let [resp (route-handler req)]
      ;; Throw a response 404 of the uri of the req
      resp
        {:status 404 :body (str "CANNOT FOIND PAGE:" (:uri req))})
    ;; Catch on nil 
    (catch Throwable e
      {:status 500 :body (apply str (interpose "\n" (.getStackTrace e)))})))

;; Middleware
(defn simple-log-middleware [handler]
  (fn [{:keys [uri] :as req}]
    (println "Request path:" uri)
    (handler req)))
(def full-handler
  (simple-log-middleware wrapping-handler))
