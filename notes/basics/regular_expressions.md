# RE

Literal notation:
```clojure
#"regex"
```

### re-find

```clojure
(re-find #"^left-" "left-hand")
; "left-"

(re-find #"^left-" "cleft-test")
; nil
```

### clojure.string/replace + regex
```clojure
(defn replace_int_name
  [interface]
  (clojure.string/replace interface #"^Fast" "Gigabit"))

(replace_int_name "FastEthernet0/1")
; "GigabitEthernet0/1"
```


```clojure
(defn replace_int_name_in_config
  [config]
     (loop [remain_config config
           final_config []]
       (if (empty? remain_config)
       final_config
          (let [[cfg_line & remain] remain_config]
             (recur remain
                  (conj final_config
                      (clojure.string/replace cfg_line #"Fast" "Gigabit")))))))

(replace_int_name_in_config cfg)
; ["interface GigabitEthernet0/1" "ip address 10.0.1.1 255.255.255.0" "no shutdown" "interface GigabitEthernet0/2" "ip address 10.0.2.1 255.255.255.0" "no shutdown" "line con 0" "exec-timeout 0"]
```


### reduce

```clojure
(defn replace_int_name_in_config_reduce
  [config]
  (reduce (fn [final_config cfg_line]
     (conj final_config (clojure.string/replace cfg_line #"Fast" "Gigabit")))
     []
     config))

(def config ["interface FastEthernet0/1" "ip address 10.0.1.1 255.255.255.0" "no shutdown" "interface FastEthernet0/2" "ip address 10.0.2.1 255.255.255.0" "no shutdown" "line con 0" "exec-timeout 0"])

(replace_int_name_in_config_reduce config)
; ["interface GigabitEthernet0/1" "ip address 10.0.1.1 255.255.255.0" "no shutdown" "interface GigabitEthernet0/2" "ip address 10.0.2.1 255.255.255.0" "no shutdown" "line con 0" "exec-timeout 0"]
```

```clojure
(def anonymus_replace
  (fn [final_config cfg_line]
     (conj final_config
        (clojure.string/replace cfg_line #"Fast" "Gigabit"))))

(defn replace_int_name_in_config_reduce
  [config]
  (reduce anonymus_replace [] config))

```
