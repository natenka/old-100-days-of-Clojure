## Functions

higher-order functions - функции, которые используют другую функцию как аргумент, или возвращают функцию.

Языки программирования, в которых есть функции высшего порядка - поддерживают first-class functions.

```clojure
(map inc [10 11 12 13])
; (11 12 13 14)
```

```clojure
(defn mult_by_ten
  "This function multiplies given number by 10"
  [num]
  (* num 10))

(mult_by_ten 5)
;50
```

docstring:
```clojure
(doc mult_by_ten)
-------------------------
mult_by_ten
([num])
  This function multiplies given number by 10
nil
```

arity (арность) - количество аргументов функции.


### arity overloading

```clojure
(defn mult_by_ten
  ([num]
     (* num 10))
  ([num1 num2]
     (+ (* num1 10) (* num2 10))))

(mult_by_ten 5)
; 50

(mult_by_ten 5 6)
; 110
```


```clojure
(defn mult_by
  ([num mult]
     (* num mult))
  ([num]
     (mult_by num 10)))

(mult_by 5)
; 50
(mult_by 5 5)
; 25
```

### variable-arity

Аргумент переменной длины (должен идти последним)
```clojure
(defn sum_of_numbers
  [& numbers]
  (reduce + numbers))

(sum_of_numbers 1 2 3 4)
; 10
```

Все аргументы попадают в список (list).


### Destructuring

Destructuring vector
```clojure
(defn champions
  [[first-place second-place third-place & others]]
  (println (str "First place: " first-place))
  (println (str "Second place: " second-place))
  (println (str "Third place: " third-place))
  (println (str (clojure.string/join ", " others))))

(champions ["Gold" "Silver" "Bronze" "Other1" "Other2" "Other3"])
; First place: Gold
; Second place: Silver
; Third place: Bronze
; Other1, Other2, Other3
; nil
```

Destructuring map
```clojure
(defn fio
  [{name :firstname fam :secondname }]
  (println (str "Name: " name))
  (println (str "Fam: " fam)))

(fio {:firstname "Nata" :secondname "Samoylenko"})
; Name: Nata
; Fam: Samoylenko
; nil
```


```clojure
(defn fio
  [{:keys [firstname secondname] }]
  (println (str "Name: " firstname))
  (println (str "Fam: " secondname)))

(fio {:firstname "Nata" :secondname "Samoylenko"})
; Name: Nata
; Fam: Samoylenko
; nil
```


```clojure
(defn fio
  [{:keys [firstname secondname] :as info }]
  (println (str "Name: " firstname))
  (println (str "Fam: " secondname))

  (nil? info))

(fio {:firstname "Nata" :secondname "Samoylenko"})
; Name: Nata
; Fam: Samoylenko
; false
```

### Тело функции

В теле функции могут находиться разные формы. Clojure возвращает последнюю форму.

```clojure
(defn try_body
  []
  (+ 1 2)
  (+ 10 20)
  (+ 100 200))

(try_body)
; 300
```

## Анонимные функции

```clojure
(map (fn [num] (+ num 100))
     [1 2 3 4 5])

; (101 102 103 104 105)
```

```clojure
((fn [x] (* x 100)) 5)
; 500
```

Переменная может быть анонимной функцией (def, не defn):
```clojure
(def inc_by_100 (fn [num] (+ num 100)))

(inc_by_100 3)
; 103
```

Второй вариант создания анонимных функций (использует reader macros):
```clojure
(map #(+ % 100)
  [1 2 3 4 5])
; (101 102 103 104 105)
```

Знак ```%``` обозначает аргумент.


```clojure
(#(* % 100) 5)
; 500
```

```clojure
(def inc_by_100 #(+ % 100))
  (inc_by_100 3)
; 103
```

```clojure
;; Function call
(* 8 3)

;; Anonymous function
#(* % 3)
```

__Несколько аргументов во втором варианте создания анонимной функции__

```clojure
(def div_two_numbers #(/ %1 %2))

(div_two_numbers 10 2)
; 5
```

Символ %  эквивалентен %1.


__Аргумент переменной длинны в анонимной функции__

```clojure
(#(identity %&) 10 "Test string" :ok)
; (10 "Test string" :ok)
```

Функция identity возвращает аргументы, которые ей передали без изменений.
Так как аргументы переменной длинны собираются в список, identity вернула список.

__clojures__

```clojure
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; 10

```


special form:
- don’t always evaluate all of their operands.
- you can’t use them as arguments to functions.
