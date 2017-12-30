## Data structures

> All Clojure data structures are immutable

### Strings

```clojure
(def hello "Hello!")
```

Строки всегда в двойных кавычках.

Объединение строк делается с помощью функции str:
```clojure
(str "Hello, " "Nata")
;=> "Hello, Nata"
```


### Maps (hash maps)

В Clojure есть hash maps и sorted maps.

Создание hash map:
```clojure
{:a 1 :b 2}
;=> {:a 1, :b 2}
```

Или так:
```clojure
(hash-map :a 1 :b 2)
;=> {:b 2, :a 1}
```

Получить значение по ключу:
```clojure
(get {:a 1 :b 2} :b)
;=> 2
```

get:
* если ключа нет, возвращается nil.
* можно указывать другое значение для возврата

```clojure
(get {:a 1 :b 2} :c "No such key")
;=> "No such key"
```

Получать значение можно и так:
```clojure
({:a 1 :b 2} :b)
;=> 2
(:b {:a 1 :b 2})
;=> 2
```

В таком варианте можно указывать какое значение вернуть, если ключа нет:
```clojure
(:c {:a 1 :b 2} "No such key")
;=> "No such key"
({:a 1 :b 2} :c "No such key")
;=> "No such key"
```

get-in позволяет получать значения из вложенных map:
```clojure
(get-in {:a 0 :b {:c {:d "OK"}}} [:b :c :d])
;=> "OK"
```

### Vector
```clojure
[1 2 3]
;=> [1 2 3]
(vector 1 2 3)
;=> [1 2 3]
```

Получить элемент по номеру:
```clojure
(get [3 2 1] 0)
;=> 3
```

Добавить элемент в конец:
```clojure
(conj [1 2 3] 4)
;=> [1 2 3 4]
```

###List
```clojure
'(1 2 3)
;=> (1 2 3)
(list 1 2 3)
;=> (1 2 3)
```

Получить элемент по номеру:
```clojure
(nth '(1 2 3) 0)
;=> 1
```

Добавить элемент в начало:
```clojure
(conj '(1 2 3) 4)
;=> (4 1 2 3)
```


### Sets (Hash sets)
```clojure
#{1 2 3}
;=> #{1 3 2}
(hash-set 1 2 3)
;=> #{1 3 2}
```

```clojure
(conj #{1 2 3} 3)
;=> #{1 3 2}
```

```clojure
(set [1 2 2 3 3])
;=> #{1 3 2}
```

contains? возвращает true или false
```clojure
(contains? #{1 2} 1)
;=> true
```

Возвращает :a или nil если нет элемента:
```clojure
(:a #{:a :b :c})
;=> :a
```

get делает аналогично:
```clojure
(get #{1 2 3} 1)
;=> 1
```
