public class DynExample {
  public static void main(String[] args) {
    B b = new B();
    b.m1();

    C c = new C();
    c.m3();
  }
}

class A {
  public void m1() {
    this.m2();
  }

  public void m2() {
    System.out.println("A's m2");
  }
}

class B extends A {
  public void m2() {
    System.out.println("B's m2");
  }

  public void m3() {
    super.m2();
  }
}

class C extends B {
}
