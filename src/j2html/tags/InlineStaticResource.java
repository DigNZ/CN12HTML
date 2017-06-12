package j2html.tags;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import java.io.IOException;

import static j2html.TagCreator.*;

public class InlineStaticResource {

    public enum TargetFormat {CSS_MIN, CSS, JS_MIN, JS}

    public static ContainerTag get(String path, TargetFormat format) {
        String fileString = getFileAsString(path);
        switch (format) {
            case CSS_MIN : return style().with(rawHtml(fileString));
            case JS_MIN  : return script().with(rawHtml(fileString));
            case CSS     : return style().with(rawHtml(fileString));
            case JS      : return script().with(rawHtml(fileString));
            default      : throw new RuntimeException("Invalid target format");
        }
    }

    public static String getFileAsString(String path) {
        try {
            return readFileAsString(FileSystemStorage.getInstance().getAppHomePath() + path);
        } catch (Exception e1) {
            try {
                return readFileAsString(path);
            } catch (Exception e2) {
                throw new RuntimeException("Couldn't find file with path='" + path + "'");
            }
        }
    }

    /**
     * @author kjheimark <3
     */
    private static String readFileAsString(String path) throws IOException {        
        return Util.readToString(FileSystemStorage.getInstance().openInputStream(path));
    
    }

}

