## Sequence

### first, rest, cons


```clojure
(first [1 2 3 4])
; 1

(first {:a 100 :b 200})
; [:a 100]
```


```clojure
(rest [1 2 3 4])
; (2 3 4)

(rest {:a 100 :b 200})
; ([:b 200])
```



```clojure
(cons 0 [1 2 3])
; (0 1 2 3)

(cons [:c 300] {:a 100 :b 200})
; ([:c 300] [:a 100] [:b 200])
```


```clojure
(seq [1 2 3 4])
; (1 2 3 4)

(seq {:a 100 :b 200})
; ([:a 100] [:b 200])
```

## Seq functions examples

### map

```clojure
(map str ["a" "b" "c"] ["A" "B" "C"])
; ("aA" "bB" "cC")
```


```clojure
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))

(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [1 2 3])
; (6 3 2)
```

keywords can be used as functions:
```clojure
(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "IronMan" :real "Tony Stark"}])

(map :real identities)
; ("Bruce Wayne" "Peter Parker" "Tony Stark")
(map :alias identities)
; ("Batman" "Spider-Man" "IronMan")
```

### reduce

assoc
```clojure
(assoc {} :max (inc 50))
; {:max 51}
(assoc {} :max (+ 50 100))
; {:max 150}

(assoc (assoc {} :max (inc 30)) :min (inc 20))
; {:max 31, :min 21}
```

```clojure
(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 30 :min 10})
; {:max 31, :min 11}
```

```clojure
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:a 4.5
         :b 3})
; {:a 4.5}
```

## take, drop, take-while, drop-while

```clojure
(take 3 [1 2 3 4 5 6 7 8 9])
; (1 2 3)

(drop 3 [1 2 3 4 5 6 7 8 9])
; (4 5 6 7 8 9)
```

predicate function - a function whose return value is evaluated for truth or falsity.

__take-while__

```clojure
(def sw_info
  [{:vlan 10 :int 1 :mode "access"}
   {:vlan 10 :int 2 :mode "access"}
   {:vlan 20 :int 3 :mode "access"}
   {:vlan 20 :int 4 :mode "access"}
   {:vlan 30 :int 4 :mode "access"}
   {:vlan 30 :int 5 :mode "access"}
   {:vlan 30 :int 10 :mode "trunk"}])

(take-while #(< (:vlan %) 30) sw_info)
;  ({:vlan 10, :int 1, :mode "access"}
;   {:vlan 10, :int 2, :mode "access"}
;   {:vlan 20, :int 3, :mode "access"}
;   {:vlan 20, :int 4, :mode "access"})
```


__drop-while__

```clojure
(def sw_info
  [{:vlan 10 :int 1 :mode "access"}
   {:vlan 10 :int 2 :mode "access"}
   {:vlan 20 :int 3 :mode "access"}
   {:vlan 20 :int 4 :mode "access"}
   {:vlan 30 :int 4 :mode "access"}
   {:vlan 30 :int 5 :mode "access"}
   {:vlan 30 :int 10 :mode "trunk"}])

(drop-while #(< (:vlan %) 30) sw_info)
;  ({:vlan 30, :int 4, :mode "access"}
;   {:vlan 30, :int 5, :mode "access"}
;   {:vlan 30, :int 10, :mode "trunk"})
```

## filter

```clojure
(def sw_info
  [{:vlan 10 :int 1 :mode "access"}
   {:vlan 10 :int 2 :mode "access"}
   {:vlan 20 :int 3 :mode "access"}
   {:vlan 20 :int 4 :mode "access"}
   {:vlan 30 :int 4 :mode "access"}
   {:vlan 30 :int 5 :mode "access"}
   {:vlan 30 :int 10 :mode "trunk"}
   {:vlan 20 :int 10 :mode "trunk"}
   {:vlan 10 :int 10 :mode "trunk"}])

(filter #(< (:vlan %) 30) sw_info)
; ({:vlan 10, :int 1, :mode "access"}
;  {:vlan 10, :int 2, :mode "access"}
;  {:vlan 20, :int 3, :mode "access"}
;  {:vlan 20, :int 4, :mode "access"}
;  {:vlan 20, :int 10, :mode "trunk"}
;  {:vlan 20, :1nt 10, :mode "trunk"}
;  {:vlan 10, :int 10, :mode "trunk"})
```

## some

```clojure
(some #(< (:vlan %) 30) sw_info)
true

(some #(> (:vlan %) 30) sw_info)
nil
```

## sort, sort-by

```clojure
(sort [5 3 12 1])
; (1 3 5 12)
```


```clojure
(sort-by count ["aaaaaaa" "b" "cccc"]
; ("b" "cccc" "aaaaaaa")
```

## concat

```clojure
(concat [1 4] [3 5 6])
; (1 4 3 5 6)

(concat {:a 10} {:b 30})
; ([:a 10] [:b 30])
```

## infinite seq

```clojure
(take 10 (repeat 3))
; (3 3 3 3 3 3 3 3 3 3)
```


```clojure
(take 7 (repeatedly (fn [] (rand-int 10))))
; (2 1 8 2 0 6 8)
```


```clojure
(defn odd-numbers
  ([] (odd-numbers 1))
  ([n] (cons n (lazy-seq (odd-numbers (+ n 2))))))

(take 10 (odd-numbers))
; (1 3 5 7 9 11 13 15 17 19)
```


## Collection abstraction
Seq - works on members. Collection - works on whole ds.


### empty?, count

```clojure
(empty? [])
; true

(empty? [1 2 3])
; false
```


```clojure
(count [1 2 3])
3
(count [])
0
```

```clojure
(every? odd? [1 2 3])
; false

(every? odd? [1 5 3])
; true

(every? #{1 2 3} [1 2 2 2 3])
; true

(every? #{1 2 3} [1 2 2 2 3 4])
; false
```

### into

```clojure
(map identity {:a 100 :b 200})
; ([:a 100] [:b 200])

(into {} (map identity {:a 100 :b 200}))
; {:a 100, :b 200}
```

```clojure
(map identity [:a :a :b])
; (:a :a :b)

(into #{} (map identity [:a :a :b]))
; #{:b :a}
```

```clojure
(into {:c 300} (map identity {:a 100 :b 200}))
; {:c 300, :a 100, :b 200}
```

### conj

```clojure
(conj [1 2 3] [4])
; [1 2 3 [4]]

(conj [1 2 3] 4)
; [1 2 3 4]

(conj [1 2 3] 4 5 6 7 8)
; [1 2 3 4 5 6 7 8]
```

## apply

```clojure
(max 1 2 3)
; 3

(max [1 2 3])
; [1 2 3]

(apply max [1 2 3])
; 3
```

```clojure
(conj [1 2 3] [4])
; [1 2 3 [4]]

(apply conj [1 2 3] [4])
; [1 2 3 4]
```

## partial

```clojure
(def sort_by_count (partial sort-by count))

(sort_by_count ["aaaaaaa" "b" "cccc"])
; ("b" "cccc" "aaaaaaa")
```

## complement
Из документации: "Takes a fn f and returns a fn that takes the same arguments as f, has the same effects, if any, and returns the opposite truth value."
```clojure
(def not-empty? (complement empty?))

(not-empty? [])
; false
(not-empty? [1 2 3])
; true
```

Разница между not и complement в том, что not работает со значениями (values) и возвращает значение true или flase,
а complement - работает с функциями и возвращает функцию.


## slurp

```clojure
(def filename "suspects.csv")
(slurp filename)
; "Edward Cullen,10\nBella Swan,0\nCharlie Swan,0\nJacob Black,3\nCarlisle Cullen,6\n"

(slurp "http://xgu.ru")
; "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n<html lang=\"ru\" dir=\"ltr\">\n<head>\n<title>Заглавная страница — Xgu.ru</title>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />
...
```

## comp

Composing functions:
```clojure
((comp inc *) 2 10)
; 21


((comp :1 :2) { :2 { :1 100 } :1 1000 })
;100

```

## memoize

```clojure
(defn sleepy-func
  [x]
  (Thread/sleep 1000)
  x)
(sleepy-func "Test")
; "Test"

(def memo-sleep (memoize sleepy-func))
(memo-sleep "Test")
; "Test"
(memo-sleep "Test")
;"Test"
```
