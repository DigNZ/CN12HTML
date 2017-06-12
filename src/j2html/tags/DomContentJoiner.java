package j2html.tags;

import com.codename1.util.StringUtil;

public class DomContentJoiner {

    public static UnescapedText join(String delimiter, boolean fixPeriodAndCommaSpacing, Object... stringOrDomObjects) {
        StringBuilder sb = new StringBuilder();
        for (Object o : stringOrDomObjects) {
            if (o instanceof String) {
                sb.append(((String) o).trim()).append(delimiter);
            } else if (o instanceof DomContent) {
                sb.append(((DomContent) o).render().trim()).append(delimiter);
            } else {
                throw new RuntimeException("You can only join DomContent and String objects");
            }
        }
        String joined = sb.toString().trim();
        if (fixPeriodAndCommaSpacing) {
            StringUtil.replaceAll(joined, "\\s\\.", ".");
            joined = StringUtil.replaceAll(joined, "\\s\\.", ".");
            joined = StringUtil.replaceAll(joined, "\\s\\,", ".");
        }
        return new UnescapedText(joined);
    }

}
