## if

if
```clojure
(def a 5)
(def b 3)

(if (> a b)
  "a is bigger than b")
;=> "a is bigger than b"
```

if/else
```clojure
(def a 3)
(def b 5)

(if (> a b)
  "a is bigger than b"
  "b is bigger than a")
;=> "b is bigger than a"
```

Если выражение false и нет else:
```clojure
(def a 3)
(def b 5)

(if (> a b)
  "a is bigger than b")

;=> nil
```

В Clojure ответвление then и ответвление else могут содержать только одну форму (выражение).
Чтобы обойти это ограничение, можно использовать оператор __do__.

## do
```clojure
(def a 5)
(def b 3)

(if (> a b)
  (do (def a (+ 100 33))
      (println "Done")
      "a is bigger than b"))
;=> Done
;=> "a is bigger than b"

a
;=> 133
```


## when

Оператор __when__ работает как комбинация if и do. Но без else ответвления:
```clojure
(def a 5)
(def b 3)

(when (> a b)
  (def a (+ 100 33))
  (println "Done")
   "a is bigger than b")
;=> Done
;=> "a is bigger than b"

a
;=> 133
```

## nil, true, false

В clojure есть значения true и false.

nil означает no value.

В Clojure есть функция __nil?__, с помощью которой можно проверить равно ли значение nil:
```clojure
(nil? 0)
;=> false

(nil? nil)
;=> true
```

Например, когда в выражении if нет ветки else, а выражение в if дало false:
```clojure

(def a 3)
(def b 5)

(nil? (if (> a b)
      "a is bigger than b"))
;=> true
```

В Clojure:
* false:
 * false
 * nil
* true:
 * всё остальное

## Операторы сравнения
```clojure
(> 5 3)
;=> true
(< 5 3)
;=> false
(= 5 3)
;=> false
(= 5 5)
;=> true
```

### and, or

and возвращает первое ложное значение или, если нет ложных значений, последнее значение true.
```clojure
(and 5 3 1)
;=> 1
(and 5 3 1 nil false)
;=> nil
```

or возвращает или первое значение true или последнее значение.
```clojure
(or false nil 5 10)
;=> 5
(or (> 5 3) (= 1 2))
;=> true
(or (> 1 3) (= 1 2))
;=> false
```
