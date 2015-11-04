(ns user-agent-parser.parser
  (:require [clojure.string :as string]
            [clojure.java.io :refer [input-stream resource]])
  (:import [cz.mallat.uasparser UASparser MultithreadedUASparser]))

(defn- string->keyword [^String string-key]
  (-> (string/join "-" (-> string-key
                           (.replaceAll "([A-Z]+)([A-Z][a-z])" "$1-$2")
                           (.replaceAll "([a-z\\d])([A-Z])" "$1-$2")
                           (string/split  #" ")))
      (string/lower-case)
      (keyword)))

(defn create [^String path]
  (MultithreadedUASparser. (-> path resource input-stream)))

(defn parse [^UASparser parser ^String user-agent-string]
  (let [user-agent (. parser parse user-agent-string)]
    {:has-info? (. user-agent hasDeviceInfo)
     :robot? (. user-agent isRobot)
     :browser-family (. user-agent getUaFamily)
     :browser-name (. user-agent getUaName)
     :device-type (. user-agent getDeviceType)
     :type (. user-agent getType)
     :os-family (. user-agent getOsFamily)
     :os-name (. user-agent getOsName)}))
