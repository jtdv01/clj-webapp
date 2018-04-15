(ns clj-webapp.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn example-handler [req]
  {:body "Hello!"})
