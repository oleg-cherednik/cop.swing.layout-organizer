# Lyout Organizer
When I was working with sing technology some years ago, I had routine - to organize components with some particular direction. E.g. to organize components into one row I could use _FlowLayout_, but to organize it into one column - _GridBagLayout_ woth some constraints. It was again and again and I desided, that I would be great to have smth. like layout strategy, e.g. *layout strategy to organize components in one coulumn*, and just apply this strategy on the container and get result quickly.

Finally, I've created _LayoutOrganizer_:
```java
public interface LayoutOrganizer {
    void update(Container container);
}
```

In given repository, I've added two component organization strategies: _SingleColumnLayout_, _SingleRowLayout_.

To tell the truth, it was very useful in my work.
*[DEMO](https://github.com/oleg-cherednik/cop.swing.layout-organizer/blob/master/demo/cop.swing.layout-organizer-1.0.jar)*
