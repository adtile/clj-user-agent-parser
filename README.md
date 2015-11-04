# clj-user-agent-parser
Parses browser information from user agent header

## Usage

```clojure
(require '[user-agent-parser.parser :as ua])

;Note! You should download user agent file from http://user-agent-string.info/ and put it to classpath.
(def parser (ua/create "ua-data.ini"))

(ua/parse parser "Mozilla/5.0 (Linux; Android 4.3; Nexus 7 Build/JWR66Y) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.94 Safari/537.36")
```

