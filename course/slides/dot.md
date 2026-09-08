# Record Node

```
Observer [
  label = {"Observer | a() \lb()"}
]
```

* `|` for separate compartments
* `\l` newline in the same compartment
* use `[ ]` for record type node

---
# Arrow Shape

* `[style="dashed"]` dashed arrow line
* `[arrowhead="o"]` hollow arrow head
* `[label="..."]` label of the edge

---
# Cluster

To cluster nodes together, put these nodes in the same `subgraph`, name the
subgraph `cluster<name>`.

```
subgraph cluster_a {
}
```

---
# Rank Direction

* `randir=BT` Bottom to top
* so that the source node is at the bottom
