Expr<Integer> intExp =
  new Plus<>(
    new Const<>(new Integer(7)),
    new Prod<>(
      new Diff<>(
        new Const<>(new Integer(4)),
        new Const<>(new Integer(3))),
      new Const<>(new Integer(5))))

Expr<Set<Integer>> setExp =
  new Plus<>(
    new Const<>(Set.of(7,5)),
    new Prod<>(
      new Diff<>(
        new Const<>(Set.of(4)),
        new Const<>(Set.of(3))),
      new Const<>(Set.of(5))))
