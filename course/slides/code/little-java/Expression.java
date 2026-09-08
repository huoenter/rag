import java.util.Set;

public class Expression {}

interface Expr<A> { A accept(ExprVisitor<A> ask); }

class Plus<A> implements Expr<A> {
  Expr<A> l; Expr<A> r;
  public Plus(Expr<A> ll, Expr<A> rr) { l = ll; r = rr; }
  public A accept(ExprVisitor<A> ask) { return ask.forPlus(l, r); }
}

class Diff<A> implements Expr<A> {
  Expr<A> l; Expr<A> r;
  public Diff(Expr<A> ll, Expr<A> rr) { l = ll; r = rr; }
  public A accept(ExprVisitor<A> ask) { return ask.forDiff(l, r); }
}

class Prod<A> implements Expr<A> {
  Expr<A> l; Expr<A> r;
  public Prod(Expr<A> ll, Expr<A> rr) { l = ll; r = rr; }
  public A accept(ExprVisitor<A> ask) { return ask.forProd(l, r); }
}

class Const<A> implements Expr<A> {
  A a;
  public Const(A aa) { a = aa; }
  public A accept(ExprVisitor<A> ask) { return ask.forConst(a); }
}

interface ExprVisitor<A> {
  A forPlus(Expr<A> l, Expr<A> r);
  A forDiff(Expr<A> l, Expr<A> r);
  A forProd(Expr<A> l, Expr<A> r);
  A forConst(A a);
}

class IntEval implements ExprVisitor<Integer> {
  public Integer forPlus(Expr<Integer> l, Expr<Integer> r) {
    return plus(l.accept(this), r.accept(this));
  }

  public Integer forDiff(Expr<Integer> l, Expr<Integer> r) {
    return diff(l.accept(this), r.accept(this));
  }

  public Integer forProd(Expr<Integer> l, Expr<Integer> r) {
    return prod(l.accept(this), r.accept(this));
  }

  public Integer forConst(Integer a) { return a; }

  Integer plus(Integer a, Integer b) { return a.intValue() + b.intValue(); }
  Integer diff(Integer a, Integer b) { return a.intValue() - b.intValue(); }
  Integer prod(Integer a, Integer b) { return a.intValue() * b.intValue(); }
}

class SetEval<A> implements ExprVisitor<Set<A>> {
  public Set<A> forPlus(Expr<Set<A>> l, Expr<Set<A>> r) {
    return plus(l.accept(this), r.accept(this));
  }

  public Set<A> forDiff(Expr<Set<A>> l, Expr<Set<A>> r) {
    return diff(l.accept(this), r.accept(this));
  }

  public Set<A> forProd(Expr<Set<A>> l, Expr<Set<A>> r) {
    return prod(l.accept(this), r.accept(this));
  }

  public Set<A> forConst(Set<A> a) { return a; }

  Set<A> plus(Set<A> a, Set<A> b) {
    Set<A> ans = new HashSet<>();
    ans.addAll(a);
    ans.addAll(b);
    return ans; }

  Set<A> diff(Set<A> a, Set<A> b) {
    Set<A> ans = new HashSet<>();
    ans.addAll(a);
    ans.removeAll(b);
    return ans; }

  Set<A> prod(Set<A> a, Set<A> b) {
    Set<A> ans = new HashSet<>();
    ans.addAll(a);
    ans.retainAll(b);
    return ans; }
}
