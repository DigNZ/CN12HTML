package j2html.tags;

import java.io.IOException;

public abstract class DomContent {

    public abstract String render();

    public void render(Appendable writer) throws IOException {
        char[] chars = render().toCharArray();
        for (char c : chars) {
            writer.append(c);
        }
    }

    @Override
    public String toString() {
        return render();
    }

}
