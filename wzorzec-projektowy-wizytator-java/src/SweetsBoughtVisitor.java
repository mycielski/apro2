public interface SweetsBoughtVisitor {

    int visit(Cake cake);

    int visit(Cookie cookie);
}