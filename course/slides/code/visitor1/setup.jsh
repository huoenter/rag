Backpack bp = new Backpack(List.of(
  new Book("Design Patterns", 49.99),
  new Book("Comp Org", 109.99),
  new Backpack(List.of(
      new Book("Intro to Opera", 149.99)),
    8.99),
  new Book("SPC", 45.54)),
12.99);
