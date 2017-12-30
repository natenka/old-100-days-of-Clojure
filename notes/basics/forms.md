


### let

Документация [let](https://clojuredocs.org/clojure.core/let).

let связывает имена и значения.

```clojure
(let [x 3]
  x)
; 3
```

Важный момент - переменная x не существует за пределами let.

rest (&) в let:
```clojure
(let [[first_num & rest_nums] [1 2 3 4 5]]
  [first_num rest_nums])

; [1 (2 3 4 5)]
```


### take

```clojure
(take 1 [1 2 3 4 5 6])
; (1)

(take 2 [1 2 3 4 5 6])
; (1 2)

(take 10 [1 2 3 4 5 6])
; (1 2 3 4 5 6)
```

### let + take

```clojure
(let [numbers (take 2 [1 2 3 4 5 6])]
  numbers)
; (1 2)
```

### let scope and global scope

```clojure
(def x 100)

(let [x (inc x)] x)
; 101
```

x в (inc x) - относится соответствию, которое мы создали в (def x 100).


### into

```clojure
(into [] (set [1 2 3 3 4 4 2 1]))
; [1 4 3 2]
```

into - добавить элементы в вектор из set.


### loop

```clojure
(loop [step 0]
  (println (str "Current step: " step))
  (if (> step 4)
    (println "The end")
    (recur (inc step))))
; Current step: 0
; Current step: 1
; Current step: 2
; Current step: 3
; Current step: 4
; Current step: 5
; The end
; nil
```


