(ns ring-tutorial.core
    (:use ring.adapter.jetty)
    (:use ring.middleware.reload)
    (:use ring.middleware.stacktrace)
    (:use clojure.pprint))


(defn handler [req]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (str "<h1>Request Echo</h1><pre>" 
     	        (with-out-str (pprint req)) "</pre>" )})

(def app
    (-> #'handler
        (wrap-reload #'handler '(ring-tutorial.core))
	(wrap-stacktrace)))

(defn boot []
    (run-jetty #'app {:port 8585}))


