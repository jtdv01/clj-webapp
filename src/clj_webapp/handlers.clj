(ns clj-webapp.handlers)

(defn test1-handler [req]
  {:body "test1"})

(defn test2-handler [req]
  {:status 301 :headers {"Location" "http://github.com"}})

(defn handler3 [req]
  {:body "this is handler3"})

